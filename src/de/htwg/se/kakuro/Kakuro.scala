package de.htwg.se.kakuro

import java.awt.Dimension

import com.google.inject.Guice
import de.htwg.se.kakuro.aview.{ HttpServer, SwingGui2, Tui }
import de.htwg.se.kakuro.controller.controllerComponent.ControllerInterface
import de.htwg.se.kakuro.microservices.{ SimpleCounter, SimpleCounterServer }
import de.htwg.se.kakuro.database.kakuroService
import org.apache.logging.log4j.{ LogManager, Logger }

object Kakuro {
  val logger: Logger = LogManager.getLogger(this.getClass.getName)
  val injector = Guice.createInjector(new KakuroModule)
  val controller = injector.getInstance(classOf[ControllerInterface])
  val tui = new Tui(controller)
  val webServer = new HttpServer(controller, tui)
  var input: String = ""
  val webserver = new SimpleCounterServer(new SimpleCounter)
  val kakuroserver = new kakuroService();
  println(s"Server online at http://localhost:8081/\nPress RETURN to stop...")
  controller.initField()
  def main(args: Array[String]): Unit = {
    do {
      tui.printTui()
      input = scala.io.StdIn.readLine()
      if (input == "gui") {
        val gui = new SwingGui2(controller)
        gui.size = new Dimension(580, 680)
        gui.visible = true
      }
      if (input != "exit" && input != "") tui.handleInput(input.toString)
      logger.debug("kakuro() input: " + input)
    } while (input != "exit" && input != "q")
    webServer.unbind
  }
}