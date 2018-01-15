package de.htwg.se.kakuro.model
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{ Matchers, WordSpec }

@RunWith(classOf[JUnitRunner])
class WhiteCellSpec extends WordSpec with Matchers {

  "A Cell" when {
    "not set to any value " should {
      val emptyCell = Cell(0, 0)
      "have value 0" in {
        emptyCell.whiteCellValue should be(0)
      }

      "not be set" in {
        emptyCell.isSet should be(false)
      }

    }
    "set to a specific value" should {
      val nonEmptyCell = Cell(0, 0)
      nonEmptyCell.whiteCellValue = 5
      "return that value" in {
        nonEmptyCell.whiteCellValue should be(5)
      }

      "be set" in {
        nonEmptyCell.isSet should be(true)
      }
    }
  }

}