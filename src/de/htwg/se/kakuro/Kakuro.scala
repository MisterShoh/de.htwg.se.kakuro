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
    var field = Field(8, 8)
    val controller = new Controller(field)
    field = controller.initField()
    val tui = new Tui(controller)
    var input: String = ""
    do {
      println(field)
      println("Wert setzen/ändern: s col row value")
      input = scala.io.StdIn.readLine()
      var output = tui.handleInput(input.toString)
      if (output == false) println("Falsche Eingabe!")
    } while (input != "exit")
  }
}