package de.htwg.se.kakuro.util

import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.LogManager

class UndoManager {
  private var undoStack: List[Command] = Nil
  private var redoStack: List[Command] = Nil
  val logger = LogManager.getLogger(this.getClass.getName)

  def doStep(command: Command) = {
    undoStack = command :: undoStack
    command.doStep
    logger.debug("doStep() undoStack:" + undoStack.head.toString)
  }
  def undoStep = {
    logger.debug("undoStep()")
    undoStack match {
      case Nil =>
      case head :: stack => {
        head.undoStep
        undoStack = stack
        redoStack = head :: redoStack
      }
    }
    logger.debug("undoStep() undoStack.length():" + undoStack.length)
  }
  def redoStep = {
    logger.debug("redoStep()")
    redoStack match {
      case Nil =>
      case head :: stack => {
        head.redoStep
        redoStack = stack
        undoStack = head :: undoStack
      }
    }
  }
}
