
import java.awt.GridLayout
import javax.swing.JPanel

import de.htwg.se.kakuro.controller.controllerComponent.controllerImpl.Controller
import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.Field
import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.LogManager

import scala.swing.Swing.LineBorder
import scala.swing._

//class Gui(controller: Controller) extends Frame {
class Gui() extends Frame {
  title = "Kakuro"
  preferredSize = new Dimension(500, 500)

  var label =
    new Label {
      text = "1,2"
      font = new Font("Verdana", 1, 36)
    }

  def borderLayout = new BorderPanel

  def gridPanel = new GridPanel(8,8) {
    border = LineBorder(java.awt.Color.BLACK, 2)
    background = java.awt.Color.BLACK

  }


  contents = gridPanel




}


val controller = new Controller(new Field(8))
val gui = new Gui()
gui.centerOnScreen()
gui.visible = true







/*



var cell = new BoxPanel(Orientation.Vertical) {
      contents += label
      preferredSize = new Dimension(50,50)
    }
    var cell2 = new BoxPanel(Orientation.Vertical) {
      contents += label
      preferredSize = new Dimension(50,50)
    }
    var cell3 = new BoxPanel(Orientation.Vertical) {
      contents += label
      preferredSize = new Dimension(50,50)
    }
    var cell4 = new BoxPanel(Orientation.Vertical) {
      contents += label
      preferredSize = new Dimension(50,50)
    }
    val statusline = new TextField("StatusText", 20)
    contents += cell
    contents += cell2
    contents += cell3
    contents += cell4
    contents += statusline



contents = new FlowPanel {
    contents += new MenuBar {
      contents += new Menu("Exit")
    }
    contents += new Label {
      text = "Here is the contents!"
    }
    contents += new Button {
      text = "Juhu"
    }


  }

 */