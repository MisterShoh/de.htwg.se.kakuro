package de.htwg.se.kakuro.aview.gui

import de.htwg.se.kakuro.aview.{ CellPanel, CellPanel2 }
import de.htwg.se.kakuro.controller.controllerComponent.{ CandidatesChanged, CellChanged, ControllerInterface, GridSizeChanged }

import scala.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event._

//class CellClicked(val row: Int, val column: Int) extends Event

class SwingGui2(controller: ControllerInterface) extends Frame {
  listenTo(controller)

  /*
  def fieldview = new GridPanel(controller.height, controller.width) = {
    for {
      row <- 0 until controller.width
      col <- 0 until controller.height
    } {
      val cellview = new CellPanel2(row, col, controller)
    }
  }
*/
  title = "Kakuro"
  contents = new BorderPanel {
    add(new CellPanel2(0, 0, controller), BorderPanel.Position.Center)
    //add(fieldview, BorderPanel.Position.Center)
    background = java.awt.Color.BLACK
    foreground = java.awt.Color.orange
  }
  minimumSize = new Dimension(200, 100)
  visible = true
  centerOnScreen()
}