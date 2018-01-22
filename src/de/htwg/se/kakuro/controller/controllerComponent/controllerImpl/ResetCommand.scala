package de.htwg.se.kakuro.controller.controllerComponent.controllerImpl

import de.htwg.se.kakuro.util.Command
import org.apache.logging.log4j.LogManager
import scala.collection.mutable

class ResetCommand(row: Int, col: Int, controller: Controller) extends Command {
  val logger = LogManager.getLogger(this.getClass.getName)
  val resetValueUndoStack = new mutable.Stack[Int]
  val resetValueRedoStack = new mutable.Stack[Int]

  override def doStep: Unit = {
    logger.debug("ResetCommand doStep()")
    resetValueUndoStack.push(controller.field.cell(row, col).value)
    controller.field = controller.field.reset(row, col)
  }

  override def undoStep: Unit = {
    logger.debug("ResetCommand undoStep()")
    var value = resetValueUndoStack.pop()
    resetValueRedoStack.push(value)
    controller.field = controller.field.set(row, col, value)
  }
  override def redoStep: Unit = {
    logger.debug("ResetCommand redoStep()")
    resetValueUndoStack.push(controller.field.cell(row, col).value)
    controller.field = controller.field.reset(row, col)
  }
}
