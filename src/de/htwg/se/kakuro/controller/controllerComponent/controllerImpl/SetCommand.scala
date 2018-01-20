package de.htwg.se.kakuro.controller.controllerComponent.controllerImpl

import de.htwg.se.kakuro.controller.controllerComponent.controllerImpl.Controller
import de.htwg.se.kakuro.util.Command
import org.apache.logging.log4j.LogManager
import de.htwg.se.kakuro.model.fieldComponent.FieldInterface
class SetCommand(row: Int, col: Int, value: Int, controller: Controller) extends Command {
  val logger = LogManager.getLogger(this.getClass.getName)

  override def doStep: Unit = {
    logger.debug("doStep()")
    controller.field = controller.field.set(row, col, value)
  }

  override def undoStep: Unit = {
    logger.debug("undoStep()")
    controller.field = controller.field.set(row, col, 0)
  }
  override def redoStep: Unit = {
    logger.debug("redoStep()")
    controller.field = controller.field.set(row, col, value)
  }
}
