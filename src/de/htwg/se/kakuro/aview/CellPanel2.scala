package de.htwg.se.kakuro.aview
import java.awt.geom.Line2D
import java.awt.{ AlphaComposite, BasicStroke, FlowLayout, GridLayout, RenderingHints, Stroke }
import javafx.scene.shape.Line
import javax.swing.SwingConstants

import scala.swing._
import scala.swing.event._
import de.htwg.se.kakuro.controller.controllerComponent.{ CellChanged, ControllerInterface, SelectorChanged }
import de.htwg.se.kakuro.model.fieldComponent.CellInterface
import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.Cell

import scala.collection.immutable

class CellPanel2(row: Int, col: Int, controller: ControllerInterface, val isSelected: Boolean = false) extends BorderPanel { //(Orientation.Vertical) {

  /*
  var c: Constraints = new Constraints()
  c.fill = GridBagPanel.Fill.Both
  c.weightx = 1.0
  c.weighty = 1.0
  c.grid = (0, 0)
  */

  val cellSize: Int = 60

  xLayoutAlignment = 0.5
  yLayoutAlignment = 0.5

  //def myCell = new Cell(6)
  def myCell: CellInterface = controller.cell(row, col)

  def cellText(row: Int, col: Int): String = if (controller.cell(row, col).isWhite) controller.cell(row, col).value.toString else "B"

  /*
  def cellText(row: Int, col: Int) = {
    if (controller.isSet(row, col)) " " + controller.cell(row, col).value.toString else " "
  }
  //add(WhiteCell)
    minimumSize = new Dimension(cellSize, cellSize)
    border = Swing.BeveledBorder(Swing.Raised)
    listenTo(mouse.clicks)
    listenTo(controller)
  }
  */
  def wlabel: Label = new Label() {
    text = size.width.toString
    //contents += new Label("_")
    //xLayoutAlignment = 0.5
    //yLayoutAlignment = 0.5
    font = new Font("Verdana", 1, 25)
    horizontalAlignment = Alignment.Center
    verticalAlignment = Alignment.Center
    preferredSize = new Dimension(cellSize, cellSize)
  }

  def rlabel: Label = new Label() {
    text = myCell.rightSum.toString
    //contents += new Label("_")
    //xLayoutAlignment = 0.2
    //yLayoutAlignment = 0.8
    font = new Font("Verdana", 1, 20)
    horizontalTextPosition = Alignment.Center
    verticalTextPosition = Alignment.Center
    if (myCell.hasRight)
      foreground = java.awt.Color.WHITE
    else
      foreground = java.awt.Color.BLACK
    //preferredSize = new Dimension(cellSize, cellSize)
  }

  def dlabel: Label = new Label() {
    text = myCell.downSum.toString
    //contents += new Label("_")
    //xLayoutAlignment = 0.8
    //yLayoutAlignment = 0.2
    font = new Font("Verdana", 1, 20)
    horizontalTextPosition = Alignment.Center
    verticalTextPosition = Alignment.Center
    if (myCell.hasDown)
      foreground = java.awt.Color.WHITE
    else
      foreground = java.awt.Color.BLACK
  }

  class plainCell extends FlowPanel {
    //def plainCell: BoxPanel = new BoxPanel(Orientation.Vertical) {
    background = java.awt.Color.BLACK
    foreground = java.awt.Color.BLACK

    preferredSize = new Dimension(cellSize, cellSize)
  }

  class blackCell extends GridPanel(2, 2) {
    //def blackCell: BoxPanel = new BoxPanel(Orientation.Vertical) {
    background = java.awt.Color.BLACK
    val empty = new FlowPanel {
      visible = false
      //background = java.awt.Color.BLACK
    }
    contents += empty
    contents += rlabel
    contents += dlabel
    //foreground = java.awt.Color.WHITE
    preferredSize = new Dimension(cellSize, cellSize)

    override def paintComponent(g: swing.Graphics2D) {
      super.paintComponent(g)

      //val color = g.getColor
      //val stroke = g.getStroke
      g.setColor(java.awt.Color.WHITE)
      g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
      g.setStroke(new BasicStroke(2))
      g.drawLine(0, 0, this.size.width, this.size.height)
      //g.setStroke(stroke)
      //g.setColor(color)
    }
  }

  class whiteCell extends GridBagPanel {

    var c: Constraints = new Constraints()
    c.fill = GridBagPanel.Fill.Both
    c.weightx = 1.0
    c.weighty = 1.0
    c.grid = (0, 0)
    border = Swing.LineBorder(java.awt.Color.BLACK, 1)
    //def whiteCell: BoxPanel = new BoxPanel(Orientation.Vertical) {
    //contents += wlabel
    add(wlabel, c)

    preferredSize = new Dimension(cellSize, cellSize)
    //minimumSize = new Dimension(cellSize, cellSize)
    listenTo(controller)
    background = java.awt.Color.WHITE
    foreground = java.awt.Color.BLACK

    //border = Swing.LineBorder(java.awt.Color.ORANGE, 2)

    listenTo(mouse.clicks)
    listenTo(controller)

    reactions += {
      case e: CellChanged => {
        wlabel.text = cellText(row, col)
        repaint
      }

      case e: SelectorChanged => {
        if (controller.isSelected(row, col))
          border = Swing.LineBorder(java.awt.Color.ORANGE, 2)
        else
          border = Swing.LineBorder(java.awt.Color.BLACK, 1)
        repaint
      }
      case MouseClicked(src, pt, mod, clicks, pops) => {
        //controller.showCandidates(row, col)
        controller.selectCell(row, col)
        repaint
      }
    }

    override def paintComponent(g: swing.Graphics2D) {
      super.paintComponent(g)
      //g.setStroke(new BasicStroke(3))
      //g.drawLine(0, 0, this.size.width, this.size.height)
    }
  }

  if (myCell.isWhite) {
    val top = new FlowPanel {
      visible = false
      preferredSize = new Dimension(0, 0)
      //background = java.awt.Color.BLACK
    }
    //contents += new whiteCell
    add(new whiteCell, BorderPanel.Position.Center)
    //add(new whiteCell, c)
    add(top, BorderPanel.Position.North)
    add(top, BorderPanel.Position.South)
  } else if (myCell.isBlack) {
    //contents += new blackCell
    //add(new blackCell, c)
    add(new blackCell, BorderPanel.Position.Center)
  } else {
    //contents += new plainCell
    //add(new plainCell, c)
    add(new plainCell, BorderPanel.Position.Center)
  }
  background = java.awt.Color.darkGray

  def redraw(): Unit = {
    //contents.clear()
    wlabel.text = cellText(row, col)
    //setPaint(whiteCell)
    //add(whiteCell, BorderPanel.Position.Center)

    if (myCell.isWhite) {
      //contents += new whiteCell
      var wcell = new whiteCell
      //wcell.background = java.awt.Color.ORANGE
      add(wcell, BorderPanel.Position.Center)
      //add(new whiteCell, c)
    } else if (myCell.isBlack)
      //contents += new blackCell
      add(new blackCell, BorderPanel.Position.Center)
    //add(new whiteCell, c)
    else
      //contents += new plainCell
      add(new plainCell, BorderPanel.Position.Center)
    //add(new whiteCell, c)

    //repaint
    visible = true
  }

  //else if (controller.isHighlighted(row, col)) highlightedCellColor
}
