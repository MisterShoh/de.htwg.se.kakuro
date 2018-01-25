package de.htwg.se.kakuro

import com.google.inject.Guice
import de.htwg.se.kakuro.aview.{ SwingGui2, Tui }
import de.htwg.se.kakuro.controller.controllerComponent.ControllerInterface
import org.apache.logging.log4j.{ LogManager, Logger }

object Kakuro {
  val logger: Logger = LogManager.getLogger(this.getClass.getName)
  val injector = Guice.createInjector(new KakuroModule)
  val controller = injector.getInstance(classOf[ControllerInterface])
  val tui = new Tui(controller)
  val gui = new SwingGui2(controller)
  gui.visible = true
  controller.initField()
  def main(args: Array[String]): Unit = {

    var input: String = ""
    do {
      tui.printTui()
      input = scala.io.StdIn.readLine()
      if (input != "exit" && input != "") tui.handleInput(input.toString)
      logger.debug("kakuro() input: " + input)
    } while (input != "exit" && input != "q")
  }
}