import de.htwg.se.kakuro.model.fieldComponent.CellInterface
import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.{ Matrix }
import org.junit.runner.RunWith
import org.scalatest.{ Matchers, WordSpec }
import org.scalatest.junit.JUnitRunner
/*
@RunWith(classOf[JUnitRunner])
class MatrixSpec extends WordSpec with Matchers {
  "A Matrix is a immutable data type that contains two dimensional Vecotr of Cells. A Matrix" when {
    "empty" should {
      "be created using a dimension and sample Int" in {
        val matrix = new Matrix[Int](2, 2)
        matrix.size should be(2)
      }
    }
  }

}

*/ 