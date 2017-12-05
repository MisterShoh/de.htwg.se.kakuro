package de.htwg.se.kakuro

import de.htwg.se.kakuro.model.Player
import de.htwg.se.kakuro.model.Field
import de.htwg.se.kakuro.model.Cell
import de.htwg.se.kakuro.controller.Controller

object Kakuro {
  def main(args: Array[String]): Unit = {
    //var field = new Field(8, 8)
    //field.initField()
    //println(field)

    var field = Field(8, 8)
    val controller = new Controller(field)
    controller.createSampleField()

    //
    // println("$$$$$$$$$$$$$$$$$$$$$$$$")

    println(controller.field)
    /*
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
    */

  }
}