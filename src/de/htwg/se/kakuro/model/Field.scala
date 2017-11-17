package de.htwg.se.kakuro.model
import de.htwg.se.kakuro.model.Cell

case class Field(height: Int, width: Int) {

  var field = Array.ofDim[Cell](height, width)

  def printField(): Unit = {
    for (i <- 0 to field.length - 1) {
      for (j <- 0 to field(i).length - 1) {
        print(i + "/" + j + ":")
        print(field(i)(j))
        print(" ")
      }
      println()
    }
  }

  def initField(): Unit = {
    for (i <- 0 to field.length - 1) {
      for (j <- 0 to field(i).length - 1) {
        field(i)(j) = new Cell(i)
      }
    }
  }

}