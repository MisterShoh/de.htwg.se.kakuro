package de.htwg.se.kakuro.aview
import de.htwg.se.kakuro.controller.controllerComponent.controllerImpl.Controller
import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.LogManager

import scala.swing._

class Gui(controller: Controller) extends MainFrame {
  title = "GUI Program #1"
  preferredSize = new Dimension(320, 240)
  contents = new Label("Here is the contents!")
}
