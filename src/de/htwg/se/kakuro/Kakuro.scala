package de.htwg.se.kakuro

import de.htwg.se.kakuro.model.Player
import de.htwg.se.kakuro.model.Field
import de.htwg.se.kakuro.model.Cell
import de.htwg.se.kakuro.controller.Controller
import de.htwg.se.kakuro.aview.Tui
import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.LogManager

object Kakuro {
  def main(args: Array[String]): Unit = {
    val logger = LogManager.getLogger(this.getClass.getName)
    var field = Field(8, 8)
    val controller = new Controller(field)
    field = controller.initField()
    val tui = new Tui(controller)
    var input: String = ""
    do {
      tui.print(field);
      input = scala.io.StdIn.readLine()
      var output = true
      if (input != "exit" && input != "") output = tui.handleInput(input.toString)
      logger.debug("kakuro() input: " + input + " output: " + output)
      if (output == false) println("Falsche Eingabe!")
    } while (input != "exit")
  }
}