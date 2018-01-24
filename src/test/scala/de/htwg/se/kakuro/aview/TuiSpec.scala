import de.htwg.se.kakuro.aview.Tui
import de.htwg.se.kakuro.controller.controllerComponent.controllerImpl.Controller
import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.FieldCreator
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{ Matchers, WordSpec }

@RunWith(classOf[JUnitRunner])
class TuiSpec extends WordSpec with Matchers {
  "A Tui" when {
    "created" should {
      var creator = new FieldCreator
      var field = creator.makeField(8)
      var controller = new Controller(field)
      var tui = new Tui(controller)
      "be a tui" in {
        tui.isInstanceOf[Tui] should be(true)
      }
      "be able to set input in field" in {
        tui.handleInput("s 1 2 9")
        controller.cell(1, 2).value should be(9)
      }
      "not be able to set input in field to 9999" in {
        tui.handleInput("s 1 2 9999")
        controller.cell(1, 2).value should be(9)
      }
      "be able to undo set" in {
        tui.handleInput("u")
        controller.cell(1, 2).value should be(0)
      }
      "be able to redo undo" in {
        tui.handleInput("r")
        controller.cell(1, 2).value should be(9)
      }
      "be able to reset values" in {
        tui.handleInput("d 1 2")
        controller.cell(1, 2).value should be(0)
      }
      "be abble to undo reset" in {
        tui.handleInput("u")
        controller.cell(1, 2).value should be(9)
      }
    }
  }
}