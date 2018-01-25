package de.htwg.se.kakuro.model.fieldComponent.FieldImpl
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{ Matchers, WordSpec }

@RunWith(classOf[JUnitRunner])
class FieldCreatorSpec extends WordSpec with Matchers {
  "A FieldCreator" when {
    "create an empty field" should {
      var creator = new FieldCreator
      var field = creator.makeField(8)
      "have size 8" in {
        field.height should be(8)
      }
    }
    "create an filled field" should {
      var creator = new FieldCreator
      var field = creator.makeField(8)
      var filledfield = creator.fill(field)
      "have size 8" in {
        filledfield.height should be(8)
      }
      "have defaultCells who are not Black or White" in {
        filledfield.cell(1, 3).isWhite should be(false)
        filledfield.cell(1, 3).isBlack should be(false)
        filledfield.cell(1, 3).isSet should be(true)
      }
      "have BlackCells at expected position" in {
        filledfield.cell(0, 1).isBlack should be(true)
      }
      "have WhiteCells at expceted position" in {
        filledfield = filledfield.set(1, 3, 0)
        filledfield.cell(1, 3).isWhite should be(true)
      }
    }
  }
}
