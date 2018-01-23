import de.htwg.se.kakuro.aview.{ CellPanel2, SwingGui2 }
import de.htwg.se.kakuro.controller.controllerComponent.controllerImpl.Controller
import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.FieldCreator
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{ Matchers, WordSpec }

import scala.swing.Button

@RunWith(classOf[JUnitRunner])
class GuiSpec extends WordSpec with Matchers {
  "A Gui" when {
    "created" should {
      var creator = new FieldCreator
      var field = creator.makeField(8)
      var controller = new Controller(field)
      var gui = new SwingGui2(controller)
      gui.visible = false
      "be a tui" in {
        gui.isInstanceOf[SwingGui2] should be(true)
      }
      "have a title" in {
        gui.title should be("Kakuro")
      }
      "cells" in {
        gui.cells.isInstanceOf[Array[Array[CellPanel2]]] should be(true)
      }
      "buttons" in {
        gui.buttons.isInstanceOf[Array[Button]] should be(true)
      }
    }
  }
}