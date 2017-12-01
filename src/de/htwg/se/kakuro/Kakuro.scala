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
    println(field)
    val tui = new Tui(field)
    while (break) {
      val input = scala.io.StdIn.readLine()
      println("Did you type this ? " + input)

      if (input == "exit") break = false; println("Exit")
    }
  }
}