package de.htwg.se.kakuro.model
import de.htwg.se.kakuro.model.Cell

case class Field(height: Int, width: Int) {

  var field = Array.ofDim[Cell](height, width)

  def printField(): Unit = {
    for (rows <- field) {
      for (col <- rows) {
        print(col)
        print(" ")
      }
      println()
    }
  }

}