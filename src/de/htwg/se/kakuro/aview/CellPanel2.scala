package de.htwg.se.kakuro.aview
import java.awt.geom.Line2D
import java.awt.{ AlphaComposite, BasicStroke, FlowLayout, GridLayout, RenderingHints, Stroke }
import javafx.scene.shape.Line
import javax.swing.SwingConstants

import scala.swing._
import scala.swing.event._
import de.htwg.se.kakuro.controller.controllerComponent.{ CellChanged, ControllerInterface }
import de.htwg.se.kakuro.model.fieldComponent.CellInterface
import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.Cell

import scala.collection.immutable

class CellPanel2(row: Int, col: Int, controller: ControllerInterface) extends BorderPanel { //(Orientation.Vertical) {

  val cellSize: Int = 60
  val blackCellColor = new Color(80, 80, 80)
  val whiteCellColor = new Color(252, 252, 252)
  val highlightedCellColor = new Color(200, 255, 200)

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
    text = row.toString
    //contents += new Label("_")
    xLayoutAlignment = 0.5
    yLayoutAlignment = 0.5
    font = new Font("Verdana", 1, 30)
    horizontalAlignment = Alignment.Center
    verticalAlignment = Alignment.Center
    preferredSize = new Dimension(cellSize, cellSize)
  }

  def rlabel: Label = new Label() {
    text = myCell.rightSum.toString
    //contents += new Label("_")
    xLayoutAlignment = 0.2
    yLayoutAlignment = 0.8
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
    xLayoutAlignment = 0.8
    yLayoutAlignment = 0.2
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

  class whiteCell extends FlowPanel {
    //def whiteCell: BoxPanel = new BoxPanel(Orientation.Vertical) {
    contents += wlabel
    //contents += new Label(row.toString, SwingConstants.CENTER)
    border = Swing.BeveledBorder(Swing.Raised)
    //border.
    xLayoutAlignment = 0.5
    yLayoutAlignment = 0.5
    //xAlignment = Alignment.Center
    //yAlignment = Alignment.Center

    preferredSize = new Dimension(cellSize, cellSize)
    //minimumSize = new Dimension(cellSize, cellSize)
    listenTo(controller)
    background = java.awt.Color.WHITE
    foreground = java.awt.Color.BLACK

    override def paintComponent(g: swing.Graphics2D) {
      super.paintComponent(g)
      //g.setStroke(new BasicStroke(3))
      //g.drawLine(0, 0, this.size.width, this.size.height)
    }
  }

  if (myCell.isWhite)
    //contents += new whiteCell
    add(new whiteCell, BorderPanel.Position.Center)
  else if (myCell.isBlack)
    //contents += new blackCell
    add(new blackCell, BorderPanel.Position.Center)
  else
    //contents += new plainCell
    add(new plainCell, BorderPanel.Position.Center)

  //add(whiteCell, BorderPanel.Position.Center)
  background = java.awt.Color.darkGray

  def redraw(): Unit = {
    //contents.clear()
    wlabel.text = cellText(row, col)
    //setPaint(whiteCell)
    //add(whiteCell, BorderPanel.Position.Center)
    if (myCell.isWhite)
      //contents += new whiteCell
      add(new whiteCell, BorderPanel.Position.Center)
    else if (myCell.isBlack)
      //contents += new blackCell
      add(new blackCell, BorderPanel.Position.Center)
    else
      //contents += new plainCell
      add(new plainCell, BorderPanel.Position.Center)

    repaint
  }

  def setPaint(p: Panel): Unit = {
    p.background = if (!controller.isWhite(row, col)) java.awt.Color.BLACK
    else java.awt.Color.ORANGE
    p.foreground = if (!controller.isWhite(row, col)) java.awt.Color.WHITE
    else java.awt.Color.BLACK
  }

  //else if (controller.isHighlighted(row, col)) highlightedCellColor
}
/*


BoxPanel(Orientation.Vertical) {


 */ 