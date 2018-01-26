package de.htwg.se.kakuro.aview
import java.awt.{ BasicStroke, RenderingHints }
import scala.swing._
import scala.swing.event._
import de.htwg.se.kakuro.controller.controllerComponent.{ CellChanged, ControllerInterface, SelectorChanged }
import de.htwg.se.kakuro.model.fieldComponent.CellInterface

class CellPanel2(row: Int, col: Int, controller: ControllerInterface, val isSelected: Boolean = false) extends BorderPanel { //(Orientation.Vertical) {

  var whiteTextSize = 25
  var blackTextSize = 20
  val cellSize: Int = 60

  xLayoutAlignment = 0.5
  yLayoutAlignment = 0.5

  def myCell: CellInterface = controller.cell(row, col)

  def cellText(row: Int, col: Int): String = if (controller.cell(row, col).isWhite) controller.cell(row, col).value.toString else "B"

  def wlabel: Label = new Label() {
    text = if (myCell.isSet) myCell.value.toString else " "
    font = new Font("Verdana", 1, whiteTextSize) //25
    horizontalAlignment = Alignment.Center
    verticalAlignment = Alignment.Center
    preferredSize = new Dimension(cellSize, cellSize)
  }

  def rlabel: Label = new Label() {
    text = myCell.rightSum.toString
    font = new Font("Verdana", 1, blackTextSize)
    horizontalTextPosition = Alignment.Center
    verticalTextPosition = Alignment.Center
    if (myCell.hasRight)
      foreground = java.awt.Color.WHITE
    else
      foreground = java.awt.Color.BLACK
  }

  def dlabel: Label = new Label() {
    text = myCell.downSum.toString
    font = new Font("Verdana", 1, blackTextSize)
    horizontalTextPosition = Alignment.Center
    verticalTextPosition = Alignment.Center
    if (myCell.hasDown)
      foreground = java.awt.Color.WHITE
    else
      foreground = java.awt.Color.BLACK
  }

  class plainCell extends FlowPanel {
    background = java.awt.Color.BLACK
    foreground = java.awt.Color.BLACK

    preferredSize = new Dimension(cellSize, cellSize)
  }

  class blackCell extends GridPanel(2, 2) {
    background = java.awt.Color.BLACK
    val empty: FlowPanel = new FlowPanel {
      visible = false
    }
    contents += empty
    contents += rlabel
    contents += dlabel
    preferredSize = new Dimension(cellSize, cellSize)

    override def paintComponent(g: swing.Graphics2D) {
      super.paintComponent(g)
      g.setColor(java.awt.Color.WHITE)
      g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
      g.setStroke(new BasicStroke(2))
      g.drawLine(0, 0, this.size.width, this.size.height)
    }
  }

  class whiteCell extends GridBagPanel {

    var c: Constraints = new Constraints()
    c.fill = GridBagPanel.Fill.Both
    c.weightx = 1.0
    c.weighty = 1.0
    c.grid = (0, 0)
    border = Swing.LineBorder(java.awt.Color.BLACK, 1)
    add(wlabel, c)
    preferredSize = new Dimension(cellSize, cellSize)
    listenTo(controller)
    background = java.awt.Color.WHITE
    foreground = java.awt.Color.BLACK
    listenTo(mouse.clicks)
    listenTo(controller)
    listenTo(wlabel)
    reactions += {
      case e: CellChanged =>
        wlabel.text = cellText(row, col)
        repaint
      case e: SelectorChanged =>
        if (controller.isSelected(row, col))
          border = Swing.LineBorder(java.awt.Color.ORANGE, 2)
        else
          border = Swing.LineBorder(java.awt.Color.BLACK, 1)
        repaint
      case MouseClicked(src, pt, mod, clicks, pops) =>
        controller.selectCell(row, col)
        repaint
    }
    override def paintComponent(g: swing.Graphics2D) {
      super.paintComponent(g)
    }
  }
  if (myCell.isWhite) {
    val top = new FlowPanel {
      visible = false
      preferredSize = new Dimension(0, 0)
    }
    add(new whiteCell, BorderPanel.Position.Center)
    add(top, BorderPanel.Position.North)
    add(top, BorderPanel.Position.South)
  } else if (myCell.isBlack) {
    add(new blackCell, BorderPanel.Position.Center)
  } else {
    add(new plainCell, BorderPanel.Position.Center)
  }
  background = java.awt.Color.darkGray
  def redraw(): Unit = {
    wlabel.text = cellText(row, col)
    if (myCell.isWhite) {
      var wcell = new whiteCell
      add(wcell, BorderPanel.Position.Center)
    } else if (myCell.isBlack)
      add(new blackCell, BorderPanel.Position.Center)
    else
      add(new plainCell, BorderPanel.Position.Center)
    visible = true
  }
  def resizeText(): Unit = {
    whiteTextSize = (this.size.width / 2.6).toInt
    blackTextSize = (this.size.width / 2.9).toInt
    redraw()
  }
}
