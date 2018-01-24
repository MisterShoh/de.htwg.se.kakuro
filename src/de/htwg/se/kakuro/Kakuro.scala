package de.htwg.se.kakuro

import com.google.inject.Guice
import de.htwg.se.kakuro.aview.{ SwingGui2, Tui }
import de.htwg.se.kakuro.controller.controllerComponent.ControllerInterface
import de.htwg.se.kakuro.controller.controllerComponent.controllerImpl.Controller
import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.Field
import org.apache.logging.log4j.{ LogManager, Logger }

object Kakuro {
  //val injector = Guice.createInjector(new KakuroModule)
  //val controller = injector.getInstance(classOf[ControllerInterface])
  val defaultsize = 8
  val controller = new Controller(new Field(defaultsize))
  val tui = new Tui(controller)
  val gui = new SwingGui2(controller)
  //gui.visible
  controller.initField()
  def main(args: Array[String]): Unit = {

    val logger: Logger = LogManager.getLogger(this.getClass.getName)

    //println(args.length.toString)
    //println(args(0))

    //if (args.head == "gui") {
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