package de.htwg.se.kakuro.model
import de.htwg.se.kakuro.model.Cell
case class Field(height: Int, width: Int) {

  val field = Array.ofDim[Cell](height, width)

  //printField
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