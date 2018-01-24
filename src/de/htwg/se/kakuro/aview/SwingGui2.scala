package de.htwg.se.kakuro.aview

import javax.swing.UIManager
import javax.swing.border.EmptyBorder
import javax.swing.plaf.nimbus.NimbusLookAndFeel

import de.htwg.se.kakuro.controller.controllerComponent.{ CellChanged, ControllerInterface }

import scala.swing._
import scala.swing.event._
//import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeelimport

class CellClicked(val row: Int, val column: Int) extends Event

class SwingGui2(controller: ControllerInterface) extends MainFrame {

  //javax.swing.{UIManager}object UI extends SimpleSwingApplication {   UIManager.setLookAndFeel(new NimbusLookAndFeel)  ...}

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
      //contents += button
      buttons(index) = button
      add(button, c)
      listenTo(button)
      /*
      reactions += {
        case event: SizeChanged =>
          buttonTextSize = this.size.width / 25
          //if (this.size.width < 460) {
          //buttonbar.contents.foreach(_.font = new Font("Verdana", 0, buttonTextSize))
          //}
          repaint
          visible = true
      }
      */
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
    //case event: CandidatesChanged => redraw()
    case event: UIElementResized =>
      statusline.text = "Size: " + this.size.toString
      resizeText()
      repaint
      visible = true
  }

  val statusline = new TextField("static Text", 20)
  listenTo(statusline)

  contents = new BorderPanel {
    //add(new CellPanel2(0, 0, controller), BorderPanel.Position.Center)
    add(buttonbar, BorderPanel.Position.North)
    add(fieldview, BorderPanel.Position.Center)
    add(statusline, BorderPanel.Position.South)
  }

  menuBar = new MenuBar {
    contents += new Menu("File") {
      mnemonic = Key.F
      //contents += new MenuItem(Action("Empty") { controller.createEmptyGrid })
      //contents += new MenuItem(Action("New") { controller.createNewGrid })
      //contents += new MenuItem(Action("Save") { controller.save })
      //contents += new MenuItem(Action("Load") { controller.load })
      contents += new MenuItem(Action("Quit") { sys.exit(0) })
    }
    contents += new Menu("Edit") {
      mnemonic = Key.E
      contents += new MenuItem(Action("Undo") { controller.undo() })
      contents += new MenuItem(Action("Redo") { controller.redo() })
    }
    /*
    contents += new Menu("Solve") {
      mnemonic = Key.S
      contents += new MenuItem(Action("Solve") { controller.solve })
    }
    contents += new Menu("Highlight") {
      mnemonic = Key.H
      for { index <- 0 to controller.gridSize } {
        contents += new MenuItem(Action(index.toString) { controller.highlight(index) })
      }
    }
    contents += new Menu("Options") {
      mnemonic = Key.O
      contents += new MenuItem(Action("Show all candidates") { controller.toggleShowAllCandidates })
      contents += new MenuItem(Action("Size 1*1") { controller.resize(1) })
      contents += new MenuItem(Action("Size 4*4") { controller.resize(4) })
      contents += new MenuItem(Action("Size 9*9") { controller.resize(9) })

    }
    */
  }

  //minimumSize = new Dimension(200, 100)
  visible = true
  centerOnScreen()

  repaint

  def resizeText(): Unit = {
    buttonTextSize = this.size.width / 25
    buttons.foreach(_.font = new Font("Verdana", 0, buttonTextSize))
    buttons.foreach(_.preferredSize = new Dimension(this.size.width / 12, this.size.width / 12))
    //cells.flatten.foreach(_.resizeText(this.size))
    cells.flatten.foreach(_.resizeText())
  }

  def redraw(): Unit = {
    /*
    for {
      row <- 0 until controller.height
      column <- 0 until controller.width
    } cells(row)(column).redraw()
    */
    cells.flatten.foreach(_.redraw())
    //publish(UIElementResized(fieldview))
    repaint
    visible = true
  }

}