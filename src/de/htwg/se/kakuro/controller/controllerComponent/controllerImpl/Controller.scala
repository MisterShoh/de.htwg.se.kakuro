package de.htwg.se.kakuro.controller.controllerComponent.controllerImpl

import controller.controllerComponent.GameStatus._
import de.htwg.se.kakuro.controller.controllerComponent.{ CandidatesChanged, CellChanged, ControllerInterface, SelectorChanged }
import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.{ Field, FieldCreator }
import de.htwg.se.kakuro.model.fieldComponent.{ CellInterface, FieldInterface }
import de.htwg.se.kakuro.util.UndoManager
import org.apache.logging.log4j.{ LogManager, Logger }

import scala.swing.Publisher

class Controller(var field: FieldInterface) extends ControllerInterface with Publisher {
  val logger: Logger = LogManager.getLogger(this.getClass.getName)

  private val undoManager = new UndoManager
  var gameStatus: GameStatus = IDLE
  var selection: (Int, Int) = (-1, -1)
  var showAllCandidates: Boolean = false

  def undo(): Unit = {
    undoManager.undoStep
    gameStatus = UNDO
    publish(new CellChanged)
  }

  def redo(): Unit = {
    undoManager.redoStep
    gameStatus = REDO
    publish(new CellChanged)
  }

  def initField(): FieldInterface = {
    var generator = new FieldCreator()
    field = generator.fill(field)
    //field = samplefield.createNewField(8)
    field.set(1, 2, 7)
    field
  }
  def available(row: Int, col: Int): Set[Int] = Set(0, 0)
  def isShowCandidates(row: Int, col: Int): Boolean = false
  def isWhite(row: Int, col: Int): Boolean = cell(row, col).isWhite
  /*
  def set(row: Int, col: Int, value: Int): Boolean = {
    logger.debug("row: " + row.toString + " col: " + col.toString + " value:" + value.toString)
    if (field.cell(row, col).isInstanceOf[WhiteCellInterface]
      && isValidInput(row)
      && isValidInput(col)
      && isValidInput(value)) {
      //field.cell(row, col).asInstanceOf[WhiteCellInterface].value = value
      true
    } else { false }
  }*/

  override def set(row: Int, col: Int): Unit = {
    undoManager.doStep(new SetCommand(row, col, 0, this))
    logger.debug("row: " + row.toString + " col: " + col.toString + "set to simple Cell")
    field.set(row, col)
    publish(new CellChanged)
  }

  override def set(row: Int, col: Int, value: Int): Unit = {
    if (value == 0) undoManager.doStep(new ResetCommand(row, col, this))
    else {
      undoManager.doStep(new SetCommand(row, col, value, this))
      field.set(row, col, value)
    }
    logger.debug("row: " + row.toString + " col: " + col.toString + "type: white value: " + value.toString)
    publish(new CellChanged)
  }

  override def set(value: Int): Unit = {
    undoManager.doStep(new SetCommand(selection._1, selection._2, value, this))
    logger.debug("row: " + selection._1.toString + " col: " + selection._2.toString + "type: white value: " + value.toString)
    field.set(selection._1, selection._2, value)
    publish(new CellChanged)
  }

  override def set(row: Int, col: Int, rightSum: Int, downSum: Int): Unit = {
    logger.debug("row: " + row.toString + " col: " + col.toString + "type: black" +
      " rightSum: " + rightSum.toString + " downSum: " + downSum.toString)
    field.set(row, col, rightSum, downSum)
    publish(new CellChanged)
  }

  override def clear(row: Int, col: Int): Unit = {
    logger.debug("row: " + row.toString + " col: " + col.toString + " reset")
    undoManager.doStep(new ResetCommand(row, col, this))
    field.reset(row, col)
  }

  def isValidInput(input: Int): Boolean = {
    logger.debug("isValidInput, input: " + input.toString)
    if (input >= 1 && input <= 9) true
    else { false }
  }

  override def width: Int = field.width
  override def height: Int = field.height

  override def createEmptyGrid(size: Int): Unit = {
    field = new Field(8)
    publish(new CellChanged)
  }

  //def cell(row: Int, col: Int) = field.cell(row, col)

  //override def isSet(row: Int, col: Int): Boolean = true //cell(row, col).isSet
  override def isSet(row: Int, col: Int): Boolean = cell(row, col).isSet
  def showCandidates(row: Int, col: Int): Unit = {
    field = field.setShowCandidates(row, col)
    gameStatus = CANDIDATES
    publish(new CandidatesChanged)
  }

  override def fieldToString: String = field.toString

  //override def isShowCandidates(row: Int, col: Int): Boolean = ???

  /*
  override def toggleShowAllCandidates: Unit = {
    showAllCandidates = !showAllCandidates
    gameStatus = CANDIDATES
    publish(new CellChanged)
  }
  */
  //override def showAllCandidates: Boolean = ???

  //override def available(row: Int, col: Int): Set[Int] = ???

  //override def statusText: String = ???
  override def cell(row: Int, col: Int): CellInterface = field.cell(row, col)

  override def isSelected(row: Int, col: Int): Boolean = selection == (row, col)

  override def selectCell(row: Int, col: Int): Unit = {
    if (isSelected(row, col)) { selection = (-1, -1) }
    else { selection = (row, col) }
    gameStatus = SELECTED
    publish(new SelectorChanged)
  }

  override def getSelected: (Int, Int) = selection

  override def hasSelect: Boolean = selection != (-1, -1)
}
