package de.htwg.se.kakuro.aview
import java.awt.GridLayout

import scala.swing._
import scala.swing.event._
import de.htwg.se.kakuro.controller.controllerComponent.{ CellChanged, ControllerInterface }
import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.Cell

import scala.collection.immutable

class CellPanel2(row: Int, col: Int, controller: ControllerInterface) extends FlowPanel {

  val cellSize: Int = 51
  hGap = 1
  vGap = 1
  val blackCellColor = new Color(80, 80, 80)
  val whiteCellColor = new Color(252, 252, 252)
  val highlightedCellColor = new Color(200, 255, 200)

  //def myCell = new Cell(6)
  controller.cell(row, col)

  def cellText(row: Int, col: Int) = if (controller.cell(row, col).isWhite) controller.cell(row, col).value.toString else "B"

  /*
  contents = new GridPanel(3,3) {




    //def myCell = controller.cell(row, col)


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
  var label = new Label() {
    text = row.toString
    //contents += new Label("_")
    font = new Font("Verdana", 1, 30)
    //horizontalTextPosition = Alignment.Center
    //verticalTextPosition = Alignment.Center
    xAlignment = Alignment.Center
    yAlignment = Alignment.Center
  }

  val whiteCell = new FlowPanel {
    contents += label
    preferredSize = new Dimension(cellSize, cellSize)
    //minimumSize = new Dimension(cellSize, cellSize)
    listenTo(controller)
    //background = java.awt.Color.WHITE
    //foreground = java.awt.Color.BLACK
  }

  //contents += label
  contents += whiteCell
  background = java.awt.Color.darkGray

  def redraw(): Unit = {
    contents.clear()
    label.text = cellText(row, col)
    setPaint(whiteCell)
    contents += whiteCell

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