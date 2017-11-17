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

    field.initField()
    field.printField()
    println("+--+--+--+--+--+--+--+--+")
    println("|##|##|##|##|##|##|##|##|")
    println("|##|23|30|##|##|27|12|16|")
    println("+--+vv+vv+--+--+vv+vv+vv+")
    println("|16>  |  |##|24>  |  |  |")
    println("|##|**|**|##|17|**|**|**|")
    println("+--+--+--+--+vv+--+--+--+")
    println("|17>  |  |29>  |  |  |  |")
    println("|##|**|**|15|**|**|**|**|")
    println("+--+--+--+vv+--+--+--+--+")
    println("|35>  |  |  |  |  |##|##|")
    println("|##|**|**|**|**|**|12|##|")
    println("+--+--+--+--+--+--+vv+--+")
    println("|##| 7>  |  | 8>  |  |##|")
    println("|##|##|**|**| 7|**|**| 7|")
    println("+--+--+--+--+vv+--+--+vv+")
    println("|##|##|16>  |  |  |  |  |")
    println("|##|11|10|**|**|**|**|**|")
    println("+--+vv+vv+--+--+--+--+--+")
    println("|21>  |  |  |  | 5>  |  |")
    println("|##|**|**|**|**|##|**|**|")
    println("+--+--+--+--+--+--+--+--+")
    println("| 6>  |  |  |##| 3>  |  |")
    println("|##|**|**|**|##|##|**|**|")
    println("+--+--+--+--+--+--+--+--+")
    /*print(field)
    var cell = new Cell(111111)
    print(cell.getValue)
    var fieldOfCells: Array[Array[Cell]] = Array(Array(new Cell(111111), new Cell(21), new Cell(6756754)))
    print(fieldOfCells)
    */
  }
}