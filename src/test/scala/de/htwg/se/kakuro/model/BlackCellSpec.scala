package de.htwg.se.kakuro.model.fieldComponent.FieldImpl
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{ Matchers, WordSpec }
import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.BlackCell

@RunWith(classOf[JUnitRunner])
class BlackCellSpec extends WordSpec with Matchers {

  "A BlackCell" when {
    "default " should {
      val cell = new SuperCell()
      "be set" in {
        cell.isSet should be(true)
      }
      "be not set" in {
        cell.isBlack should be(false)
        cell.isWhite should be(false)
      }
    }
  }

}
