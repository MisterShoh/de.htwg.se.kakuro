package de.htwg.se.kakuro

import de.htwg.se.kakuro.aview.Tui
import de.htwg.se.kakuro.controller.controllerComponent.controllerImpl.Controller
import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.Field
import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.LogManager

object Kakuro {
  def main(args: Array[String]): Unit = {
    val logger: Logger = LogManager.getLogger(this.getClass.getName)

    val defaultsize = 8
    val controller = new Controller(new Field(defaultsize))
    controller.initField()
    val tui = new Tui(controller)

    //println(args.length.toString)
    //println(args(0))

    //if (args.head == "gui") {
    val gui = new SwingGui2(controller)
    gui.visible
    //}

    var input: String = ""
    do {
      tui.printTui()
      input = scala.io.StdIn.readLine()
      if (input != "exit" && input != "") tui.handleInput(input.toString)
      logger.debug("kakuro() input: " + input)
    } while (input != "exit" && input != "q")
  }
}