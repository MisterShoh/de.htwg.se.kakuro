package de.htwg.se.kakuro

import de.htwg.se.kakuro.model.Player
import de.htwg.se.kakuro.model.Field
import de.htwg.se.kakuro.model.Cell
import de.htwg.se.kakuro.controller.Controller
import de.htwg.se.kakuro.aview.Tui

object Kakuro {
  def main(args: Array[String]): Unit = {
    var break = true;
    var field = Field(8, 8)
    val controller = new Controller(field)
    field = controller.initField()
    val tui = new Tui(field)
    while (break) {
      println(field)
      println("Wert setzen:s col,row,value")
      var input = scala.io.StdIn.readLine()
      if (input.startsWith("s")) {
        var values = input.split(",")
        var row = values(0).split(" ")(1).toInt
        var col = values(1).toInt
        var value = values(2).toInt
        var check = controller.set(row, col, value)
        println(check)
      }
      if (input == "exit") break = false;
    }
  }
}