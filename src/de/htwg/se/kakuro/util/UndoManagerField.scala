package de.htwg.se.kakuro.util
import org.apache.logging.log4j.LogManager
import de.htwg.se.kakuro.model.Field

class UndoManagerField {
  private var undoStack: List[Field] = Nil
  private var redoStack: List[Field] = Nil
  val logger = LogManager.getLogger(this.getClass.getName)

  def doStep(field: Field): Boolean = {
    undoStack = field :: undoStack
    logger.debug("doStep() undoStack:" + undoStack.head.toString)
    true
  }

}
