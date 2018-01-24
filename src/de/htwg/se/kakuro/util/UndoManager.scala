package de.htwg.se.kakuro.util

import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.LogManager

class UndoManager {
  var undoStack: List[Command] = Nil
  var redoStack: List[Command] = Nil
  val logger: Logger = LogManager.getLogger(this.getClass.getName)

  def doStep(command: Command) = {
    logger.debug("doStep()")
    undoStack = command :: undoStack
    command.doStep
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

