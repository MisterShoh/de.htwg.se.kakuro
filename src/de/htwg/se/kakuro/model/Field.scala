package de.htwg.se.kakuro.model
import de.htwg.se.kakuro.model.Cell

case class Field(height: Int, width: Int) {

  var field = Array.ofDim[Cell](height, width)

  override def toString(): String = {
    var result: String = "+--+--+--+--+--+--+--+--+\n"
    var flength = field.length - 1
    for (j <- 0 to height - 1) {
      result += stringRow(j)
    }
    return result
  }

  def stringRow(row: Int): String = {

    var result: String = "|"
    for (i <- 0 to field(row).length - 1) {
      result += field(row)(i).toStringRight
      result += "|"
    }
    result += "\n|"
    for (i <- 0 to field(row).length - 1) {
      result += field(row)(i).toStringDown
      result += "|"
    }
    result += "\n+"
    for (i <- 0 to field(row).length - 1) {
      result += "--+"
    }
    result += "\n"
    return result
  }
  def initField(): Unit = {
    for (i <- 0 to field.length - 1) {
      for (j <- 0 to field(i).length - 1) {
        field(i)(j) = new Cell(i, j)
        field(i)(j).whiteCell = true
      }
    }
  }

}