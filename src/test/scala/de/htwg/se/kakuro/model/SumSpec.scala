import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{ Matchers, WordSpec }
import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.Sum
@RunWith(classOf[JUnitRunner])
class SumSpec extends WordSpec with Matchers {
  val sumDef = new Sum
  "A Sum is the sum of all values in a vector" when {
    "an default sum" should {
      val sumDef = new Sum
      "have zero members" in {
        sumDef.members.size should be(0)
      }
      "be set to valid" in {
        sumDef.isValid should be(true)
      }
      "be set true" in {
        sumDef.isSolved should be(true)
      }
      "have the value 0" in {
        sumDef.sumValue should be(0)
      }
      "current" in {
        sumDef.current should be(0)
      }
    }
  }

}
