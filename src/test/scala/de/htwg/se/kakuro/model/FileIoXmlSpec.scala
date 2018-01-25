import de.htwg.se.kakuro.controller.controllerComponent.controllerImpl.Controller
import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.{ Cell, FieldCreator }
import de.htwg.se.kakuro.model.fieldComponent.FieldInterface
import de.htwg.se.kakuro.model.fileIOComponent.FileIOInterface
import org.junit.runner.RunWith
import org.scalatest.{ Matchers, WordSpec }
import org.scalatest.junit.JUnitRunner
import de.htwg.se.kakuro.model.fileIOComponent.fileIOXmlImpl.FileIO
@RunWith(classOf[JUnitRunner])
class FileIoXmlSpec extends WordSpec with Matchers {
  val fileIo: FileIOInterface = new FileIO()
  var field = (new FieldCreator).createNewField(8)
  var creator = new FieldCreator
  var filledfield = creator.fill(field)
  var controller = new Controller(field)
  "A created FileIoJson" when {

    "save and reload field" in {
      filledfield = filledfield.set(1, 2, 9)
      filledfield.cell(1, 2).value should be(9)
      fileIo.save(filledfield)
      /*fileIo.load */
      filledfield.cell(1, 2).value should be(9)
    }
    "fewofwe" in {
      fileIo.isInstanceOf[FileIO] should be(true)
    }
  }
}
