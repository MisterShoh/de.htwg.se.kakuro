import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.{ Cell, FieldCreator }
import de.htwg.se.kakuro.model.fieldComponent.FieldInterface
import org.junit.runner.RunWith
import org.scalatest.{ Matchers, WordSpec }
import org.scalatest.junit.JUnitRunner
@RunWith(classOf[JUnitRunner])
class FieldSpec extends WordSpec with Matchers {

  "A created Field" when {

    var creator = new FieldCreator
    var field = creator.makeField(8)
    var filledfield = creator.fill(field)

    "set should have a String representation" in {
      filledfield.toString.isEmpty should be(false)
    }
    "set should be able to set empty cells" in {
      filledfield = filledfield.set(1, 3)
      filledfield.isWhite(1, 3) && filledfield.isBlack(1, 3) should be(false)
      filledfield.isNone(0, 4) should be(true)
    }
    "set should be able to set WhiteCells with Value 5" in {
      filledfield = filledfield.set(1, 3, 5)
      filledfield.cell(1, 3).value should be(5)
      filledfield.isWhite(1, 3) should be(true)
    }
    "set should be able to set BlackCells" in {
      filledfield = filledfield.set(2, 4, 20, 30)
      filledfield.cell(2, 4).isBlack should be(true)
      filledfield.isBlack(2, 4) should be(true)
    }
    "set should be able to reset WhiteCell values" in {
      filledfield = filledfield.reset(1, 3)
      filledfield.cell(1, 3).value should be(0)
    }
    "set should return a cell" in {
      val cell = filledfield.cell(1, 3)
      cell.isInstanceOf[Cell] should be(true)
    }
    "set should have a width" in {
      filledfield.width should be(8)
    }
    "set should have a height" in {
      filledfield.height should be(8)
    }
    "set should be able to check if cell is black or white" in {
      filledfield.cell(1, 3).isWhite && filledfield.cell(2, 4).isBlack should be(true)
    }
    "createNewField" in {
      val newField = (new FieldCreator).createNewField(8)
      newField.isInstanceOf[FieldInterface] should be(true)
      newField.height should be(8)
    }
  }
}
