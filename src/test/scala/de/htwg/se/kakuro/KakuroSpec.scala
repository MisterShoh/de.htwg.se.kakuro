import com.google.inject.Injector
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{ Matchers, WordSpec }
import de.htwg.se.kakuro.Kakuro
import de.htwg.se.kakuro.controller.controllerComponent.controllerImpl.Controller
import org.apache.logging.log4j.Logger
@RunWith(classOf[JUnitRunner])
class KakuroSpec extends WordSpec with Matchers {
  var kakuro = Kakuro
  "A Kakuro" when {
    "created" should {
      "have" in {
        kakuro.logger.isInstanceOf[Logger] should be(true)
        kakuro.injector.isInstanceOf[Injector] should be(true)
        kakuro.controller.isInstanceOf[Controller] should be(true)
        kakuro.input should be("")
      }
      "create a field" in {
        kakuro.controller.cell(1, 2).isWhite should be(true)
      }
      //TODO
      "handle Input" in {
        kakuro.input = "s 1 2 3"
        kakuro.controller.cell(1, 2).value should be(0)
      }
    }
  }
}