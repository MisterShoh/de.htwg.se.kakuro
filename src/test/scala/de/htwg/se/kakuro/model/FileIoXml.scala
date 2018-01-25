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

    "load" in {
      //fileIo.load
      true should be(true)
    }
    "save" in {
      fileIo.save(field)
      true should be(true)
    }
    "fewofwe" in {
      fileIo.isInstanceOf[FileIO] should be(true)
    }
  }
}
