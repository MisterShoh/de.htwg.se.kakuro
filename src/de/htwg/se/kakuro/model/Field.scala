package de.htwg.se.kakuro.model
import de.htwg.se.kakuro.model.Cell

case class Field(height: Int, width: Int) {

  var field = Array.ofDim[Cell](height, width)

  override def toString(): String = {
    var result: String = " 0  1  2  3  4  5  6  7\n"
    result += "+--+--+--+--+--+--+--+--+\n"
    var flength = field.length - 1
    for (j <- 0 until height) {
      result += stringRow(j)
    }
    return result
  }

  def initCell(row: Int, col: Int): Unit = {
    field(row)(col) = Cell(row, col)
    field(row)(col).whiteCell = true
  }

  def initCell(row: Int, col: Int, value: Int): Unit = {
    field(row)(col) = Cell(row, col)
    field(row)(col).whiteCell = true
    field(row)(col).whiteCellValue = value;
  }

  def initCell(row: Int, col: Int, right: Int, down: Int): Unit = {
    field(row)(col) = Cell(row, col)
    field(row)(col).valueDown = down
    field(row)(col).valueRight = right
    field(row)(col).whiteCell = false
  }
  def cell(row: Int, col: Int): Cell = {
    field(row)(col)
  }
  def stringRow(row: Int): String = {
    var result: String = "|"
    for (i <- field(row).indices) {
      result += field(row)(i).toStringRight
      result += "|"
    }
    result += " " + row + "\n|"
    for (i <- field(row).indices) {
      result += field(row)(i).toStringDown
      result += "|"
    }
    result += "\n+"
    for (i <- field(row).indices) {
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