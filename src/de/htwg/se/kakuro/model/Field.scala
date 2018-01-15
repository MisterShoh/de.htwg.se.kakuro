package de.htwg.se.kakuro.model
import de.htwg.se.kakuro.model.Cell

case class Field(height: Int, width: Int) {

  val logger = LogManager.getLogger(this.getClass.getName)
  var grid = Array.ofDim[Cell](height, width)

  override def toString(): String = {
    var result: String = " 0  1  2  3  4  5  6  7\n"
    result += "+--+--+--+--+--+--+--+--+\n"
    var flength = grid.length - 1
    for (j <- 0 until height) {
      result += stringRow(j)
    }
    return result
  }

  def initCell(row: Int, col: Int): Unit = {
    grid(row)(col) = Cell(row, col)
    grid(row)(col).whiteCell = true
  }

  def initCell(row: Int, col: Int, value: Int): Unit = {
    grid(row)(col) = Cell(row, col)
    grid(row)(col).whiteCell = true
    grid(row)(col).whiteCellValue = value;
  }

  def initCell(row: Int, col: Int, right: Int, down: Int): Unit = {
    grid(row)(col) = Cell(row, col)
    grid(row)(col).valueDown = down
    grid(row)(col).valueRight = right
    grid(row)(col).whiteCell = false
  }
  def cell(row: Int, col: Int): Cell = {
    grid(row)(col)
  }

  def set(row: Int, col: Int, value: Int): Field = {
    return this
  }

  def stringRow(row: Int): String = {
    var result: String = "|"
    for (i <- grid(row).indices) {
      result += grid(row)(i).toStringRight
      result += "|"
    }
    result += " " + row + "\n|"
    for (i <- grid(row).indices) {
      result += grid(row)(i).toStringDown
      result += "|"
    }
    result += "\n+"
    for (i <- grid(row).indices) {
      result += "--+"
    }
    result += "\n"
    return result
  }

  def initField(): Unit = {
    for (i <- field.indices) {
      for (j <- field(i).indices) {
        field(i)(j) = Cell(i, j)
        field(i)(j).whiteCell = true
      }
    }
  }

}