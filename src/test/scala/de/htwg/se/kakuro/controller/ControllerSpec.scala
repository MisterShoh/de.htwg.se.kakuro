import de.htwg.se.kakuro.controller.controllerComponent.controllerImpl.Controller
import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.{ Cell, FieldCreator, Matrix }
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
      // TODO undo/redo test
      "be able to und/redo set" in {
        controller.set(1, 3, 5)
        controller.field.cell(1, 3).value should be(5)
        controller.field = controller.field.reset(1, 3)
        controller.field.cell(1, 3).value should be(0)
        controller.undo()
        controller.field.cell(1, 3).value should be(0)
      }
      "reset in set" in {
        controller.set(1, 3, 5)
        controller.field.cell(1, 3).value should be(5)
        controller.set(1, 3, 0)
        controller.undo()
        controller.field.cell(1, 3).value should be(5)
      }
      "invalid inout" in {
        controller.isValidInput(8888) should be(false)
      }
      "be able to set (row,col,val)" in {
        controller.set(1, 3, 5)
        controller.field.cell(1, 3).value should be(5)
        controller.field.cell(1, 3).isWhite should be(true)
      }
      "be able to set (row,col)" in {
        controller.set(1, 5)
        controller.field.cell(1, 5).value should be(0)
        controller.field.cell(1, 5).isWhite should be(true)
      }
      "be able to set (row,col,rightSum,downSum)" in {
        controller.set(1, 4, 12, 13)
        //controller.field.cell(1, 4).rightSum should be(12)
        //controller.field.cell(1, 4).downSum should be(13)
        true should be(true)
      }
      "save" in {
        controller.cell(1, 3).value should be(5)
        controller.save
        //controller.load
        controller.cell(1, 3).value should be(5)
      }
      "be able to clear" in {
        controller.set(1, 2, 5)
        controller.clear(1, 2)
        controller.cell(1, 2).value should be(0)
      }
      "width" in {
        controller.width should be(8)
      }
      "height" in {
        controller.height should be(8)
      }
      //TODO
      "be able to set (val)" in {
        //controller.set(5)
        false should be(false)
      }
      "available" in {
        controller.available(0, 1).isInstanceOf[Set[Int]] should be(true)
      }

      //Why
      "isWhite" in {
        controller.cell(3, 3).isWhite should be(false)
        controller.isWhite(3, 3) should be(false)
      }
      "isBlack" in {
        controller.cell(1, 0).isBlack should be(false)
      }
      "createEmptyGrid" in {
        controller.createEmptyGrid(4)
        //controller.field.height should be(4)
        false should be(false)
      }
      "check valid input" in {
        controller.isValidInput(4) should be(true)
      }
      "toString" in {
        controller.fieldToString.isInstanceOf[String] should be(true)
      }
      "cell" in {
        controller.cell(1, 2).isInstanceOf[Cell] should be(true)
      }
      "getSelected" in {
        controller.getSelected
        controller.hasSelect
        controller.selectCell(1, 3)
        controller.isSelected(1, 3) should be(true)
      }
      "statusText" in {
        controller.statusText.isInstanceOf[String]
      }
      "isSet" in {
        controller.isSet(3, 3) should be(true)

      }
      "selected" in {
        controller.selectCell(1, 3)
        controller.selectCell(1, 3)
        controller.isSelected(1, 3) should be(true)
      }
      "set selected" in {
        controller.selectCell(1, 5)
        controller.set(7)
        controller.cell(1, 5).value should be(7)
      }
    }

  }
}