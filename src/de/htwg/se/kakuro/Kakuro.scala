package de.htwg.se.kakuro

import java.awt.Dimension

import com.google.inject.Guice
import de.htwg.se.kakuro.aview.{ SwingGui2, Tui }
import de.htwg.se.kakuro.controller.controllerComponent.ControllerInterface
import org.apache.logging.log4j.{ LogManager, Logger }

object Kakuro {
  val logger: Logger = LogManager.getLogger(this.getClass.getName)
  val injector = Guice.createInjector(new KakuroModule)
  val controller = injector.getInstance(classOf[ControllerInterface])
  val tui = new Tui(controller)
  var input: String = ""
  controller.initField()

  val gui = new SwingGui2(controller)
  gui.size = new Dimension(580, 680)
  gui.visible = true

  def main(args: Array[String]): Unit = {
    do {
      tui.printTui()
      input = scala.io.StdIn.readLine()
      /*if (input == "gui") {
        val gui = new SwingGui2(controller)
        gui.size = new Dimension(580, 680)
        gui.visible = true
      }*/
      if (input != "exit" && input != "") tui.handleInput(input.toString)
      logger.debug("kakuro() input: " + input)
    } while (input != "exit" && input != "q")
  }
}