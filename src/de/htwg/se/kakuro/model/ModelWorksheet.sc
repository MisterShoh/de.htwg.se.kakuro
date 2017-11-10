import de.htwg.se.kakuro.model._

object ModelWorksheet {

	case class Cell(x:Int, y:Int)

	val cell1 = WhiteCell(4,5)
	cell1.x
	cell1.y

	case class Field(cells: Array[Cell])

	val field1 = Field(Array.ofDim[Cell](1))
	field1.cells(0)=cell1
	field1.cells(0).x
	field1.cells(0).y
}