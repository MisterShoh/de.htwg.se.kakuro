package de.htwg.se.kakuro.aview

import javax.swing.UIManager
import javax.swing.plaf.nimbus.NimbusLookAndFeel
import de.htwg.se.kakuro.controller.controllerComponent.{ CellChanged, ControllerInterface }
import de.htwg.se.kakuro.aview.CellPanel2._
import scala.swing._
import scala.swing.event._

class CellClicked(val row: Int, val column: Int) extends Event

class SwingGui2(controller: ControllerInterface) extends MainFrame {
  UIManager.setLookAndFeel(new NimbusLookAndFeel)
  listenTo(controller)
  title = "Kakuro"
  var whiteTextSize: Int = 25
  var blackTextSize: Int = 20
  var buttonTextSize: Int = 18
  var cells: Array[Array[CellPanel2]] = Array.ofDim[CellPanel2](controller.width, controller.height)
  var buttons: Array[Button] = new Array[Button](10)
  def buttonbar: GridBagPanel = new GridBagPanel {
    var c: Constraints = new Constraints()
    c.fill = GridBagPanel.Fill.Horizontal
    c.weightx = 1.0
    c.weighty = 0.0

    for { index <- 0 to 9 } {
      val button = Button(if (index == 0) "_" else index.toString) {
        if (controller.hasSelect) { controller.set(index) }
        else {
          statusline.text = "No Cell selected - " + this.size.toString
          repaint
          visible = true
        }
      }
      button.preferredSize_=(new Dimension(45, 45))
      button.font = new Font("Verdana", 0, buttonTextSize)
      button.border = Swing.EmptyBorder
      buttons(index) = button
      add(button, c)
      listenTo(button)
    }
  }

  def fieldview: GridBagPanel = new GridBagPanel() {
    var c: Constraints = new Constraints()

    c.fill = GridBagPanel.Fill.Both
    c.weightx = 1.0
    c.weighty = 1.0

    background = java.awt.Color.BLACK
    for {
      row <- 0 until controller.height
      col <- 0 until controller.width
    } {
      val cellview = new CellPanel2(col, row, controller)
      c.grid = (row, col)
      cells(row)(col) = cellview
      add(cellview, c)
      listenTo(cellview)
    }

  }
  reactions += {
    case event: CellChanged => redraw()
    case event: UIElementResized =>
      resizeText()
      repaint
      visible = true
  }

  val statusline = new TextField(controller.statusText, 20)
  listenTo(statusline)

  contents = new BorderPanel {
    add(buttonbar, BorderPanel.Position.North)
    add(fieldview, BorderPanel.Position.Center)
    add(statusline, BorderPanel.Position.South)
  }

  menuBar = new MenuBar {
    contents += new Menu("File") {
      mnemonic = Key.F
      contents += new MenuItem(Action("Quit") { sys.exit(0) })
    }
    contents += new Menu("Edit") {
      mnemonic = Key.E
      contents += new MenuItem(Action("Undo") { controller.undo() })
      contents += new MenuItem(Action("Redo") { controller.redo() })
      contents += new MenuItem(Action("Save") { controller.save() })
      contents += new MenuItem(Action("Load") { controller.load() })
    }
    contents += new Menu("Check") {
      mnemonic = Key.C
      contents += new MenuItem(Action("Check") {
        if (!controller.check()) {
          for (cell <- cells) {
            for (c <- cell) {
              if (c.myCell.isWhite) {
                c.contents(1).background = java.awt.Color.RED
              }
            }
          }
        }
      })
      contents += new MenuItem(Action("Continue") {
        for (cell <- cells) {
          for (c <- cell) {
            if (c.myCell.isWhite) {
              c.contents(1).background = java.awt.Color.WHITE
            }
          }
        }
      })
    }
  }

  visible = true
  centerOnScreen()

  repaint

  def resizeText(): Unit = {
    buttonTextSize = this.size.width / 25
    buttons.foreach(_.font = new Font("Verdana", 0, buttonTextSize))
    buttons.foreach(_.preferredSize = new Dimension(this.size.width / 12, this.size.width / 12))
    cells.flatten.foreach(_.resizeText())
  }

  def redraw(): Unit = {
    cells.flatten.foreach(_.redraw())
    repaint
    visible = true
  }

}