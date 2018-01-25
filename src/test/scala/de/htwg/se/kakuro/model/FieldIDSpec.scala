import de.htwg.se.kakuro.model.fieldComponent.FieldInterface
import org.junit.runner.RunWith
import org.scalatest.{ Matchers, WordSpec }
import org.scalatest.junit.JUnitRunner
import de.htwg.se.kakuro.model.fieldComponent.fieldDIComponent.Field
@RunWith(classOf[JUnitRunner])
class FieldIDSpec extends WordSpec with Matchers {
  "A Injected Field" when {

    "created" in {
      var field = new Field(8)
      field.height should be(8)
      field.createNewField
      field.cell(1, 1).isSet should be(true)
      field.cell(1, 1).value should be(0)
      field.isInstanceOf[FieldInterface] should be(true)
    }
  }
}

