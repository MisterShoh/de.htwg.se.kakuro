package de.htwg.se.kakuro.controller

import de.htwg.se.kakuro.model.{ Cell, Field, FieldCreator }
import de.htwg.se.kakuro.util.Observable
import de.htwg.se.kakuro.util.UndoManager

import scala.swing.Publisher
import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.LogManager

class Controller(var field: Field) extends Publisher {
  val logger = LogManager.getLogger(this.getClass.getName)

  private val undoManager = new UndoManager
  //var gameStatus: GameStatus = IDLE

  /*
  def undo: Unit = {
    undoManager.undoStep
    gameStatus = UNDO
    publish(new CellChanged)
  }

  def redo: Unit = {
    undoManager.redoStep
    gameStatus = REDO
    publish(new CellChanged)
  }
  */

  def initField(): Field = {
    var samplefield = new FieldCreator()
    field = samplefield.createEmptyGrid(8)
    field = samplefield.createSampleField(field)
    field
  }

  def set(row: Int, col: Int, value: Int): Boolean = {
    var wCell = field.cell(row, col).whiteCell
    logger.debug("set() row: " + row.toString() + " col: " + col.toString()
      + " value:" + value.toString() + " whiteCell: " + wCell)
    if (wCell
      && isValidInput(row)
      && isValidInput(col)
      && isValidInput(value)) {
      field.cell(row, col).whiteCellValue = value
      true
    } else return false
    false
  }

  def delete(row: Int, col: Int): Boolean = {
    var wCell = field.cell(row, col).whiteCell
    logger.debug("delete() row: " + row.toString() + " col: " + col.toString() + " whiteCell: " + wCell)
    if (wCell
      && isValidInput(row)
      && isValidInput(col)) {
      field.cell(row, col).whiteCellValue = 0
      true
    } else return false
    false
  }

  def isValidInput(input: Int): Boolean = {
    logger.debug("isValidInput, input: " + input.toString())
    if (input >= 1 && input <= 9) true
    else false
  }

}
