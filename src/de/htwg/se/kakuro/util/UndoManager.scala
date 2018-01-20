package de.htwg.se.kakuro.util

import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.LogManager
import scala.collection.mutable.Stack

class UndoManager {
  val logger = LogManager.getLogger(this.getClass.getName)
  var undoStack = new Stack[Command]
  var redoStack = new Stack[Command]

  /*
  def addField(field: Field) = {
    undoStack.push(field)
    println("*************************")
    println(undoStack.mkString)
    println("*************************")
    logger.debug("addField() undoStack:" + undoStack.length)
  }
  */

  def undoStep: Command = {
    var command = undoStack.pop
    redoStack.push(command)
    logger.debug("undoField() undoStack.length():" + undoStack.length)
    command
  }
  def redoStep: Command = {
    var field = redoStack.pop
    undoStack.push(field)
    logger.debug("undoStep() undoStack.length():" + undoStack.length)
    field
  }

  def UndoStep() = {

  }

  def RedoStep() = {

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
