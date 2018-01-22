import de.htwg.se.kakuro.controller.controllerComponent.controllerImpl.Controller
import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.{ FieldCreator, Matrix }
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{ Matchers, WordSpec }

@RunWith(classOf[JUnitRunner])
class ControllerSpec extends WordSpec with Matchers {
  "A controller" when {
    "empty " should {
      var creator = new FieldCreator
      var field = creator.makeField(8)
      var controller = new Controller(field)
      "be created by using a field" in {
        controller.isInstanceOf[Controller] should be(true)
      }
      "be able to und/redo steps" in {
        controller.undo()
        controller.redo()
      }
      "be able to set" in {
        controller.field = controller.field.set(1, 3, 5)
        controller.field.cell(1, 3).value should be(5)
        controller.field.cell(1, 3).isWhite should be(true)
      }
    }

  }
}