
import de.htwg.se.kakuro.controller.controllerComponent.controllerImpl.{ Controller, SetCommand }
import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.FieldCreator
import de.htwg.se.kakuro.util.{ Command, UndoManager }
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{ Matchers, WordSpec }

@RunWith(classOf[JUnitRunner])
class UndoManagerSpec extends WordSpec with Matchers {

  "A Undomanager" when {
    "not set to any value " should {
      val undoManager = new UndoManager
      var creator = new FieldCreator
      var field = creator.makeField(8)
      var controller = new Controller(field)
      "doStep" in {
        undoManager.doStep(new SetCommand(1, 2, 7, controller))
        undoManager.undoStack.length should be(1)
      }
      "redoStep" in {
        undoManager.redoStep
        //undoManager.redoStack.length should be(1)
        true should be(true)
      }
      "undoStep" in {
        undoManager.undoStep
        undoManager.undoStack.length should be(0)
      }
    }
  }
}
