package de.htwg.se.kakuro.model.fieldComponent.FieldImpl

import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.Matrix
import de.htwg.se.kakuro.model.fieldComponent.{ CellInterface, SumInterface, BlackCellInterface }
import de.htwg.se.kakuro.model.fieldComponent._
import org.apache.logging.log4j.LogManager

case class Field(grid: Matrix[Cell], sums: Set[SumInterface]) extends FieldInterface {

  val logger = LogManager.getLogger(this.getClass.getName)
  //var grid = Array.ofDim[Cell](height, width)
  //var s : Set[Int] = Set()
  def this(grid: Matrix[Cell]) = this(grid, Set.empty[SumInterface])
  def this(width: Int, height: Int) = this(new Matrix[Cell](width, height, Cell()))
  def this(size: Int) = this(new Matrix[Cell](size, Cell()))

  //var blackCells: Array[BlackCell]

  override def putSum(s: SumInterface): FieldInterface = {
    copy(grid, sums.+(s))
  }

  def getWhiteRow(row: Int, col: Int): Set[WhiteCell] = {
    grid.rows(row).drop(col).dropRight(grid.rows(row).indexWhere(p => p.isBlack, row) - width).toSet
      .asInstanceOf[Set[WhiteCell]]
  }

  def generateSums(): FieldInterface = {
    var _sums: List[Sum] = List()
    for (i <- 0 until width) {
      for (j <- 0 until height) {
        if (!grid.cell(j, i).isBlack) {
          var members = List[WhiteCell]()
          for (k <- i until width) {
            // Achtung toStringRight ist wahrscheinlich nicht der richtige String!
            //if (grid.cell(k, j).isWhite) members.+(grid.cell(k, j).asInstanceOf[WhiteCell])
          }
        }
      }
    }

    //var b : BlackCell
    //blackCells.foreach(c => if(c.downVal != 0) _sums.+new Sum())
    this
  }

  override def set(row: Int, col: Int, value: Int): FieldInterface =
    copy(grid.replaceCell(row, col, WhiteCell(value).asInstanceOf[Cell]))

  override def set(row: Int, col: Int): FieldInterface =
    copy(grid.replaceCell(row, col, Cell()))

  override def set(row: Int, col: Int, rightVal: Int, downVal: Int): FieldInterface = {
    // Achtung v, rightSum, downSum nur wegen den errors
    val v = Vector[WhiteCellInterface]
    val rightSum = Sum(10, v, true)
    val downSum = Sum(10, v, true)
    copy(grid.replaceCell(row, col, BlackCell(rightVal, downVal, rightSum, downSum).asInstanceOf[Cell]))
  }

  override def reset(row: Int, col: Int): FieldInterface =
    copy(grid.replaceCell(row, col, WhiteCell(0).asInstanceOf[Cell]))

  override def toString: String = {
    var result: String = " 0  1  2  3  4  5  6  7\n"
    result += "+--+--+--+--+--+--+--+--+\n"
    //var flength = grid.length - 1
    for (j <- 0 until width) {
      result += stringRow(j)
    }
    result
  }

  def stringRow(row: Int): String = {
    var result: String = "|"
    for (col <- 0 until width) {
      result += grid.cell(row, col).toStringRight
      //result += grid(row).toStringRight
      result += "|"
    }
    result += " " + row + "\n|"
    for (col <- 0 until width) {
      result += grid.cell(row, col).toStringDown
      //result += grid(row)(i).toStringDown
      result += "|"
    }
    result += "\n+"
    for (i <- 0 until width) {
      result += "--+"
    }
    result += "\n"
    result
  }

  /*
  def populateSums(): Vector[Sum] ={
    val x : Int = grid.foldLeft(_ + _.)

    sums = Array.ofDim[Sum](grid.foldLeft(_.asInstanceOf[BlackCell])

  }
  */
  override def blackCells(): Vector[BlackCellInterface] = { grid.toVector.filter(_.isBlack).asInstanceOf[Vector[BlackCellInterface]] }

  override def cell(row: Int, col: Int): CellInterface = grid.cell(row, col)

  override def createNewGrid(size: Int): FieldInterface = (new FieldCreator).createNewField(size)

  override def valid: Boolean = sums.forall(_.isValid)

  override def solved: Boolean = sums.forall(_.isSolved)

  //override def size: Int = grid.size
  override def width: Int = grid.width
  override def height: Int = grid.height

  //override def cells(): Matrix[CellInterface] = grid.asInstanceOf[Matrix[CellInterface]]

  override def available(row: Int, col: Int): Set[Int] = Set(1, 2, 3, 4, 5, 6, 7, 8, 9) //TODO fix ...
  override def setShowCandidates(row: Int, col: Int): Field = copy(grid.replaceCell(row, col, cell(row, col).copy(showCandidates = true)))
}
