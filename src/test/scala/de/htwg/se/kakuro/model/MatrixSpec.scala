import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.Cell
import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.Matrix
import org.junit.runner.RunWith
import org.scalatest.{ Matchers, WordSpec }
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MatrixSpec extends WordSpec with Matchers {
  "A Matrix is a tailor-made immutable data type that contains a two-dimentional Vector of Cells. A Matrix" when {
    "empty " should {
      "be created by using a dimention and a sample cell" in {
        val matrix = new Matrix[Cell](2, Cell(true, false, 5, 0, 0))
        matrix.isInstanceOf[Matrix[Cell]] should be(true)
      }
      "for test purposes only be created with a Vector of Vectors" in {
        val testMatrix = Matrix(Vector(Vector(Cell(true, false, 5, 0, 0))))
        testMatrix.isInstanceOf[Matrix[Cell]] should be(true)
      }

    }
    "filled" should {
      val matrix = new Matrix[Cell](2, Cell(true, false, 5, 0, 0))
      "give access to its cells" in {
        matrix.cell(0, 0) should be(Cell(true, false, 5, 0, 0))
      }
      "replace cells and return a new data structure" in {
        val returnedMatrix = matrix.replaceCell(0, 0, Cell(true, false, 5, 0, 0))
        matrix.cell(0, 0) should be(Cell(true, false, 5, 0, 0))
        returnedMatrix.cell(0, 0) should be(Cell(true, false, 5, 0, 0))
      }
      "be filled using fill operation" in {
        val returnedMatrix = matrix.fill(Cell(true, false, 5, 0, 0))
        returnedMatrix.cell(0, 0) should be(Cell(true, false, 5, 0, 0))
      }
    }
  }

}
