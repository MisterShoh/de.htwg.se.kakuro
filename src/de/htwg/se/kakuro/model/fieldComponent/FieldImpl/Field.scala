package de.htwg.se.kakuro.model.fieldComponent.FieldImpl

import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.Cell
import de.htwg.se.kakuro.model.fieldComponent.{ CellInterface, FieldInterface }
import org.apache.logging.log4j.LogManager

case class Field(grid: Matrix[Cell]) extends FieldInterface {

  val logger = LogManager.getLogger(this.getClass.getName)

  //var grid = Array.ofDim[Cell](height, width)
  def this(size: Int) = this(new Matrix[Cell](size, Cell()))

  //var blackCells: Array[BlackCell]
  var sums: Array[Sum] = null

  override def set(row: Int, col: Int, value: Int): FieldInterface =
    copy(grid.replaceCell(row, col, WhiteCell(value).asInstanceOf[Cell]))

  override def set(row: Int, col: Int): FieldInterface =
    copy(grid.replaceCell(row, col, Cell()))

  override def set(row: Int, col: Int, rightSum: Int, downSum: Int): FieldInterface =
    copy(grid.replaceCell(row, col, BlackCell(rightSum, downSum).asInstanceOf[Cell]))

  override def reset(row: Int, col: Int): FieldInterface = copy(grid.replaceCell(row, col, WhiteCell(0).asInstanceOf[Cell]))

  override def toString: String = {
    var result: String = " 0  1  2  3  4  5  6  7\n"
    result += "+--+--+--+--+--+--+--+--+\n"
    //var flength = grid.length - 1
    for (j <- 0 until grid.size) {
      result += stringRow(j)
    }
    return result
  }

  def stringRow(row: Int): String = {
    var result: String = "|"
    for (col <- 0 until size) {
      result += grid.cell(row, col).toStringRight
      //result += grid(row).toStringRight
      result += "|"
    }
    result += " " + row + "\n|"
    for (col <- 0 until size) {
      result += grid.cell(row, col).toStringDown
      //result += grid(row)(i).toStringDown
      result += "|"
    }
    result += "\n+"
    for (i <- 0 until size) {
      result += "--+"
    }
    result += "\n"
    return result
  }

  /*
  def populateSums(): Vector[Sum] ={
    val x : Int = grid.foldLeft(_ + _.)

    sums = Array.ofDim[Sum](grid.foldLeft(_.asInstanceOf[BlackCell])

  }
  */

  override def cell(row: Int, col: Int): CellInterface = grid.cell(row, col)

  override def createNewGrid(size: Int): FieldInterface = (new FieldCreator).createNewField(size)

  override def valid: Boolean = sums.forall(_.isValid)

  override def solved: Boolean = sums.forall(_.isSolved)

  override def size: Int = grid.size

  override def available(row: Int, col: Int): Set[Int] = Set(1, 2, 3, 4, 5, 6, 7, 8, 9)
}
