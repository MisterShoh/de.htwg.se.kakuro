package de.htwg.se.kakuro.model.fieldComponent.FieldImpl
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{ Matchers, WordSpec }

@RunWith(classOf[JUnitRunner])
class CellSpec extends WordSpec with Matchers {

  "A Cell" when {
    "not set to any value " should {
      val emptyCell = Cell(true, false, 0, 0, 0)
      "have value 0" in {
        emptyCell.value should be(0)
      }

      "not be set" in {
        emptyCell.isSet should be(false)
      }

    }
    "set to a specific value" should {
      var nonEmptyCell = Cell(true, false, 5, 0, 0)
      "return that value" in {
        nonEmptyCell.value should be(5)
      }
      "be set" in {
        nonEmptyCell.isSet should be(true)
      }
      "return true from isWhite" in {
        nonEmptyCell.isWhite should be(true)
      }
      "return false from isBlack" in {
        nonEmptyCell.isBlack should be(false)
      }
      "toStringRight" in {
        nonEmptyCell.toStringRight.isInstanceOf[String] should be(true)
      }
      "toStringDown" in {
        nonEmptyCell.toStringDown.isInstanceOf[String] should be(true)
      }

    }
    "set to BlackCell with rightSum and downSum" should {
      var blackCell = Cell(false, true, 0, 12, 10)
      "return rightSum" in {
        blackCell.rightSum should be(12)
      }
      "return downSum" in {
        blackCell.downSum should be(10)
      }
      "return true from isSet" in {
        blackCell.isSet should be(true)
      }
      "return false from isWhite 2" in {
        blackCell.isWhite should be(false)
      }
      "return true from isBlack 2" in {
        blackCell.isBlack should be(true)
      }
      "return true from hasRight" in {
        blackCell.hasRight should be(true)
      }
      "return true from hasDown" in {
        blackCell.hasDown should be(true)
      }
    }
  }

}
