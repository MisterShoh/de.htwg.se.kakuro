package de.htwg.se.kakuro.util

import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.LogManager
import de.htwg.se.kakuro.model.Field
import scala.collection.mutable.Stack

class UndoManager {
  val logger = LogManager.getLogger(this.getClass.getName)
  var undoStack = new Stack[Field]
  var redoStack = new Stack[Field]

  def addField(field: Field) = {
    undoStack.push(field)
    println("*************************")
    println(undoStack.mkString)
    println("*************************")
    logger.debug("addField() undoStack:" + undoStack.length)
  }

  def undoField = {
    var field = undoStack.pop
    redoStack.push(field)
    logger.debug("undoField() undoStack.length():" + undoStack.length)
    field
  }
  def redoField = {
    var field = redoStack.pop
    undoStack.push(field)
    logger.debug("undoStep() undoStack.length():" + undoStack.length)
    field
  }
  /*
  def doStep(command: Command) = {
    undoStack = command :: undoStack
    valueStack = command.getSetValue :: valueStack
    logger.debug("doStep() undoStack:" + undoStack.head)
  }
  def undoStep = {
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
  */
}
