import de.htwg.se.kakuro.model.fieldComponent.FieldInterface
import org.junit.runner.RunWith
import org.scalatest.{ Matchers, WordSpec }
import org.scalatest.junit.JUnitRunner
import de.htwg.se.kakuro.model.fieldComponent.fieldDIComponent.Field
import de.htwg.se.kakuro.controller.controllerComponent.controllerImpl.Controller
@RunWith(classOf[JUnitRunner])
class FieldIDSpec extends WordSpec with Matchers {
  "A Injected Field" when {

    "created" in {
      var field = new Field(8)
      var controller = new Controller(field)
      field.height should be(8)
      //field.createNewField
      controller.initField()
      field.cell(1, 1).isSet should be(true)
      field.cell(1, 1).value should be(0)
      field.isInstanceOf[FieldInterface] should be(true)
    }
  }
}

