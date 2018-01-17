package de.htwg.se.kakuro.util

import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.LogManager
import de.htwg.se.kakuro.model.Field

class UndoManagerField {
  var undoStack: List[Field] = Nil
  var redoStack: List[Field] = Nil
  var logger = LogManager.getLogger("UndoManagerField")

  def doStepF(field: Field): Boolean = {
    undoStack = field :: undoStack
    logger.debug("doStepF() undoStack:" + undoStack.head)
    println("undoStack length" + undoStack.length)
    true
  }
  def undoStepF(): Field = {
    var head = undoStack(undoStack.length - 1)
    redoStack = head :: redoStack
    logger.debug("undoStepF() undoStack:" + undoStack.head)
    println("*******************")
    println(head.toString)
    return head
  }
}
