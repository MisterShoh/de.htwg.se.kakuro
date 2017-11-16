package de.htwg.se.kakuro

import de.htwg.se.kakuro.model.Player
import de.htwg.se.kakuro.model.Field
import de.htwg.se.kakuro.model.Cell

object Kakuro {
  def main(args: Array[String]): Unit = {
    //Create Array manually, maybe only solution
    var field2: Array[Array[Int]] = Array(Array(1, 2, 3), Array(1, 2, 3), Array(1, 2, 3), Array(1, 2, 3))
    //create Array with ofDim()
    //Cant pass objects
    var field = Field(9, 9)
    field.printField()
    //print(field)
    var cell = new Cell(111111)
    print(cell.getValue)
    var fieldOfCells: Array[Array[Cell]] = Array(Array(new Cell(111111), new Cell(21), new Cell(6756754)))
    print(fieldOfCells)
  }
}