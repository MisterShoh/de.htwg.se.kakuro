package de.htwg.se.kakuro.aview

import de.htwg.se.kakuro.aview.{ CellPanel, CellPanel2 }
import de.htwg.se.kakuro.controller.controllerComponent.{ CandidatesChanged, CellChanged, ControllerInterface, GridSizeChanged }

import scala.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event._

//class CellClicked(val row: Int, val column: Int) extends Event

class SwingGui2(controller: ControllerInterface) extends Frame {
  listenTo(controller)
  title = "Kakuro"
  var cells = Array.ofDim[CellPanel2](controller.width, controller.height)

  def fieldview: GridPanel = new GridPanel(controller.height, controller.width) {

    for {
      row <- 0 until controller.width
      col <- 0 until controller.height
    } {
      val cellview = new CellPanel2(row, col, controller)
      contents += cellview
    }

  }

  val statusline = new TextField("static Text", 20)

  contents = new BorderPanel {
    //add(new CellPanel2(0, 0, controller), BorderPanel.Position.Center)
    add(fieldview, BorderPanel.Position.Center)
    add(statusline, BorderPanel.Position.South)

    background = java.awt.Color.BLACK
    foreground = java.awt.Color.orange
  }
  minimumSize = new Dimension(200, 100)
  visible = true
  centerOnScreen()
  repaint
}