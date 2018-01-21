package de.htwg.se.kakuro.aview

import java.awt.{ GridBagConstraints, GridBagLayout }
import de.htwg.se.kakuro.aview.CellPanel2
import de.htwg.se.kakuro.controller.controllerComponent.{ CandidatesChanged, CellChanged, ControllerInterface }

import scala.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event._

class CellClicked(val row: Int, val column: Int) extends Event

class SwingGui2(controller: ControllerInterface) extends Frame {
  listenTo(controller)
  title = "Kakuro"
  var cells = Array.ofDim[CellPanel2](controller.width, controller.height)

  def buttonbar: GridBagPanel = new GridBagPanel {
    var c: Constraints = new Constraints()

    c.fill = GridBagPanel.Fill.Horizontal
    c.weightx = 1.0
    c.weighty = 0.0

    for { index <- 0 to 9 } {
      val button = Button(if (index == 0) "" else index.toString) {
        //controller.highlight(index)
      }
      button.preferredSize_=(new Dimension(45, 45))
      button.font = new Font("Verdana", 0, 18)
      //button.pain
      //contents += button
      add(button, c)
      listenTo(button)
    }
  }

  def fieldview: GridBagPanel = new GridBagPanel() {
    //var gblayout = new GridBagLayout()
    //fieldview.layout = gblayout
    var c: Constraints = new Constraints()

    c.fill = GridBagPanel.Fill.Both
    c.weightx = 1.0
    c.weighty = 1.0

    background = java.awt.Color.BLACK
    //fieldview.
    for {
      row <- 0 until controller.height
      col <- 0 until controller.width
    } {
      val cellview = new CellPanel2(col, row, controller)
      //contents += cellview
      c.grid = (row, col)
      cells(row)(col) = cellview
      add(cellview, c)
      listenTo(cellview)
    }

  }
  reactions += {
    case event: CellChanged => redraw()
    case event: CandidatesChanged => redraw()
    case event: UIElementResized => {
      statusline.text = "Size: " + this.size.toString
      repaint
      visible = true
    }
  }

  val statusline = new TextField("static Text", 20)
  listenTo(statusline)

  contents = new BorderPanel {
    //add(new CellPanel2(0, 0, controller), BorderPanel.Position.Center)
    add(buttonbar, BorderPanel.Position.North)
    add(fieldview, BorderPanel.Position.Center)
    add(statusline, BorderPanel.Position.South)
  }
  minimumSize = new Dimension(200, 100)
  visible = true
  centerOnScreen()

  repaint

  def redraw(): Unit = {
    for {
      row <- 0 until controller.height
      column <- 0 until controller.width
    } cells(row)(column).redraw()
    //publish(UIElementResized(fieldview))
    repaint
    visible = true
  }

}