package de.htwg.se.kakuro.controller

import de.htwg.se.kakuro.controller.controllerComponent.controllerImpl.{ Controller, ResetCommand, SetCommand }
import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.{ Field, Matrix } //, FieldCreator
import de.htwg.se.kakuro.model.fieldComponent.FieldInterface
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{ Matchers, WordSpec }
@RunWith(classOf[JUnitRunner])
class ResetCommandSpec extends WordSpec with Matchers {
  //var creator = new FieldCreator
  //var field: FieldInterface = creator.makeField(8)
  var field = new Field(8)
  var controller = new Controller(field)
  controller.initField()
  controller.set(1, 2, 7)
  val setCommand = new ResetCommand(1, 2, controller)

  "A SetCommand" when {
    "test" should {
      "doStep" in {
        setCommand.doStep
        setCommand.resetValueUndoStack.length should be(1)
      }
      "undoStep" in {
        setCommand.undoStep
        setCommand.resetValueRedoStack.length should be(1)
      }
      "redoStep" in {
        setCommand.redoStep
        setCommand.resetValueUndoStack.length should be(1)
      }
    }
  }
}
