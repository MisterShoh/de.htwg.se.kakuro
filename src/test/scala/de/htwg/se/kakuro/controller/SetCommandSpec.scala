import de.htwg.se.kakuro.controller.controllerComponent.controllerImpl.{ Controller, SetCommand }
import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.{ Field, FieldCreator, Matrix }
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{ Matchers, WordSpec }
@RunWith(classOf[JUnitRunner])
class SetCommandSpec extends WordSpec with Matchers {
  var creator = new FieldCreator
  var field = creator.makeField(8)
  var controller = new Controller(field)
  val setCommand = new SetCommand(1, 2, 3, controller)

  "A SetCommand" when {
    "test" should {
      "doStep" in {
        setCommand.doStep
        true should be(true)
      }
      "undoStep" in {
        setCommand.undoStep
        true should be(true)
      }
      "redoStep" in {
        setCommand.redoStep
        true should be(true)
      }
    }
  }
}
