package de.htwg.se.kakuro.model
import de.htwg.se.kakuro.model.Cell

case class Field(height: Int, width: Int) {

  var field = Array.ofDim[Cell](height, width)

  def printField(): Unit = {
    var flength = field.length - 1
    //loop 1
    for (i <- 0 to flength) {
      if (i == 0) { // print first line
        println("+--+--+--+--+--+--+--+--+--+")
      }

      //loop 
      for (j <- 0 to field(i).length - 1) {
        if (j == 0) { // first char of line
          print("|")
        }
        if (j != 0 || j != field(i).length - 1) // everything in the middle
          print("## ")
        if (j == field(i).length - 1) { // last char of line
          print("|\n")
        }
      }
      if (i == flength) { // print last line
        println("+--+--+--+--+--+--+--+--+--+\n")
      }
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