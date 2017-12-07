package de.htwg.se.kakuro

import de.htwg.se.kakuro.model.Player
import de.htwg.se.kakuro.model.Field
import de.htwg.se.kakuro.model.Cell
import de.htwg.se.kakuro.controller.Controller
import de.htwg.se.kakuro.aview.Tui
//Vince bashrc
// alias sbtinit='export TERM=xterm-color && cd $SE_PATH/de.htwg.se.kakuro/ && sbt'
object Kakuro {
  def main(args: Array[String]): Unit = {
    var break = true;
    var field = Field(8, 8)
    val controller = new Controller(field)
    field = controller.initField()
    val tui = new Tui(field)
    while (break) {
      println(field)
      println("Wert setzen/ändern: s col row value")
      var input = scala.io.StdIn.readLine()
      if (input == "exit") break = false;
      var output = tui.handleInput(input.toString, controller)
      if (output == false) println("Falsche Eingabe!")
    }
  }
}