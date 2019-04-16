package de.htwg.se.kakuro.controller

import de.htwg.se.kakuro.controller.controllerComponent.controllerImpl.{ Controller, SetCommand }
import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.{ Field, Matrix } //, FieldCreator
import de.htwg.se.kakuro.model.fieldComponent.FieldInterface
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{ Matchers, WordSpec }
@RunWith(classOf[JUnitRunner])
class SetCommandSpec extends WordSpec with Matchers {
  //var creator = new FieldCreator
  //var field: FieldInterface = creator.makeField(8)
  var field = new Field(8)
  var controller = new Controller(field)
  controller.initField()
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
