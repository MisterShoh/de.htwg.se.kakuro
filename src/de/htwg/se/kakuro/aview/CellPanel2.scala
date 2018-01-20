package de.htwg.se.kakuro.aview
import java.awt.GridLayout

import scala.swing._
import scala.swing.event._
import de.htwg.se.kakuro.controller.controllerComponent.{ CellChanged, ControllerInterface }
import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.Cell

import scala.collection.immutable

class CellPanel2(row: Int, col: Int, controller: ControllerInterface) extends FlowPanel {
  val cellSize: Int = 51
  /*
  contents = new GridPanel(3,3) {




    //def myCell = controller.cell(row, col)
    def myCell = new Cell(6)

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
    text = "6"
    //horizontalTextPosition = Alignment.Center
    //verticalTextPosition = Alignment.Center
    xAlignment = Alignment.Center
    yAlignment = Alignment.Center

    foreground = java.awt.Color.BLACK
  }

  val WhiteCell = new GridPanel(3, 3) {
    background = java.awt.Color.WHITE
    foreground = java.awt.Color.BLACK
    minimumSize = new Dimension(cellSize, cellSize)
  }
  contents += WhiteCell
}
/*


BoxPanel(Orientation.Vertical) {


 */ 