package de.htwg.se.kakuro.model.fieldComponent.FieldImpl
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{ Matchers, WordSpec }

@RunWith(classOf[JUnitRunner])
class CellSpec extends WordSpec with Matchers {

  "A Cell" when {
    "not set to any value " should {
      val emptyCell = WhiteCell(0, false)
      "have value 0" in {
        emptyCell.value should be(0)
      }

      "not be set" in {
        emptyCell.isSet should be(false)
      }

    }
    "set to a specific value" should {
      var nonEmptyCell = WhiteCell(5, false)
      "return that value" in {
        nonEmptyCell.value should be(5)
      }
      "be set" in {
        nonEmptyCell.isSet should be(true)
        nonEmptyCell.isWhite should be(true)
        nonEmptyCell.isBlack should be(false)
      }
    }
  }

}
