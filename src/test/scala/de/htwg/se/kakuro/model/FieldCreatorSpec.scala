package de.htwg.se.kakuro.model.fieldComponent.FieldImpl
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{ Matchers, WordSpec }
import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.FieldCreator

@RunWith(classOf[JUnitRunner])
class FieldCreatorSpec extends WordSpec with Matchers {
  "A FieldCreator" when {
    "create a field" should {
      val creator = new FieldCreator
      val field = creator.makeField(8)
      "have size 8" in {
        field.size should be(8)
      }
    }
  }
}
