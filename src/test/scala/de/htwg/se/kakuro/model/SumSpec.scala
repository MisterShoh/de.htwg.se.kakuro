import de.htwg.se.kakuro.model.fieldComponent.CellInterface
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{ Matchers, WordSpec }
import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.{ Cell, Sum }
@RunWith(classOf[JUnitRunner])
class SumSpec extends WordSpec with Matchers {
  var sumDef = new Sum
  "A Sum is the sum of all values in a vector" when {
    "an default sum" should {
      var sumDef = new Sum

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
        var sum = new Sum //(14, new Vector[CellInterface](new Cell(true, false, 7, 0, 0), new Cell(true, false, 7, 0, 0)))
        //sum.members = new Vector[Cell](new Cell(true, false, 7, 0, 0), new Cell(true, false, 7, 0, 0))
        sum.current.isInstanceOf[Int] should be(true)
      }
      "getCandidates" in {
        sumDef.getCandidates.isInstanceOf[Set[Int]] should be(true)
      }
    }
  }

}
