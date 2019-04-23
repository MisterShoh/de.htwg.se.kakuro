package de.htwg.se.kakuro.controller.controllerComponent.controllerImpl

import com.google.inject.{ Guice, Inject }
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.kakuro.KakuroModule
import de.htwg.se.kakuro.controller.controllerComponent.GameStatus._
import de.htwg.se.kakuro.controller.controllerComponent.{ CellChanged, ControllerInterface, GameStatus, SelectorChanged }
import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.{ Field, Cell, Sum } //FieldCreator
import de.htwg.se.kakuro.model.fieldComponent.{ CellInterface, FieldInterface }
import de.htwg.se.kakuro.model.fileIOComponent.FileIOInterface
import de.htwg.se.kakuro.util.UndoManager
import org.apache.logging.log4j.{ LogManager, Logger }

import scala.swing.Publisher
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class Controller @Inject() (var field: FieldInterface) extends ControllerInterface with Publisher {
  val logger: Logger = LogManager.getLogger(this.getClass.getName)
  val injector = Guice.createInjector(new KakuroModule)
  val fileIo = injector.instance[FileIOInterface]
  private val undoManager = new UndoManager
  var gameStatus: GameStatus = IDLE
  def statusText: String = GameStatus.message(gameStatus)
  var selection: (Int, Int) = (-1, -1)
  var showAllCandidates: Boolean = false
  val standardSize = 8

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

  def save: Unit = {
    val future = Future {
      fileIo.save(field)
      gameStatus = SAVED
      publish(new CellChanged)
    }
  }

  def load(): Unit = {
    val gridOption = fileIo.load
    gridOption match {
      case None => {
        gameStatus = COULDNOTLOAD
      }
      case Some(_grid) => {
        field = _grid
        gameStatus = LOADED
      }
    }
    publish(new CellChanged)
  }

  def initField(): Unit = {
    field = new Field(standardSize)
    field = fill().generateSums()
  }

  def fill(): this.type = {

    field = field.set(0, 0).set(0, 1, 0, 23)
      .set(0, 2, 0, 30)
      .set(0, 3).set(0, 4).set(0, 5, 0, 27)
      .set(0, 6, 0, 12)
      .set(0, 7, 0, 16)
      .set(1, 0, 16, 0)
      .set(1, 1, 0).set(1, 2, 0)
      .set(1, 3).set(1, 4, 24, 17)
      .set(1, 5, 0).set(1, 6, 0)
      .set(1, 7, 0).set(2, 0, 17, 0)
      .set(2, 1, 0).set(2, 2, 0)
      .set(2, 3, 29, 15)
      .set(2, 4, 0).set(2, 5, 0)
      .set(2, 6, 0).set(2, 7, 0)
      .set(3, 0, 35, 0).set(3, 1, 0)
      .set(3, 2, 0).set(3, 3, 0)
      .set(3, 4, 0).set(3, 5, 0)
      .set(3, 6, 0, 12).set(3, 7).set(4, 0)
      .set(4, 1, 7, 0).set(4, 2, 0)
      .set(4, 3, 0).set(4, 4, 8, 7)
      .set(4, 5, 0).set(4, 6, 0)
      .set(4, 7, 0, 7).set(5, 0)
      .set(5, 1, 0, 11)
      .set(5, 2, 16, 10)
      .set(5, 3, 0).set(5, 4, 0)
      .set(5, 5, 0).set(5, 6, 0)
      .set(5, 7, 0).set(6, 0, 21, 0)
      .set(6, 1, 0).set(6, 2, 0)
      .set(6, 3, 0).set(6, 4, 0)
      .set(6, 5, 5, 0).set(6, 6, 0)
      .set(6, 7, 0).set(7, 0, 6, 0)
      .set(7, 1, 0).set(7, 2, 0)
      .set(7, 3, 0).set(7, 4).set(7, 5, 3, 0)
      .set(7, 6, 0).set(7, 7, 0)
    this
  }

  def generateSums(): Field = { //field: Field
    var _members: Set[Cell] = Set()
    var _field = field.asInstanceOf[Field]
    for {
      row <- (0 until _field.width).reverse
      col <- (0 until _field.height).reverse
    } {
      if (_field.cell(row, col).isWhite) {
        _members = _members.+(_field.cell(row, col))
      } else if (_field.cell(row, col).isBlack) {
        _field = _field.putSum(Sum(_field.cell(row, col).rightSum, _members))
        _members = Set()
      }
    }

    for {
      col <- (0 until _field.height).reverse
      row <- (0 until _field.width).reverse
    } {
      if (_field.cell(row, col).isWhite) {
        _members = _members.+(_field.cell(row, col))
      } else if (_field.cell(row, col).isBlack) {
        _field = _field.putSum(Sum(_field.cell(row, col).downSum, _members))
        _members = Set()
      }
    }
    _field
  }

  def available(row: Int, col: Int): Set[Int] = Set(0, 0)
  def isWhite(row: Int, col: Int): Boolean = cell(row, col).isWhite

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
    if (value == 0) undoManager.doStep(new ResetCommand(selection._1, selection._2, this))
    else {
      undoManager.doStep(new SetCommand(selection._1, selection._2, value, this))
      field.set(selection._1, selection._2, value)
    }
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
  override def isSet(row: Int, col: Int): Boolean = cell(row, col).isSet

  //override def fieldToString: String = field.toString
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

  override def isValid: Boolean = field.valid

  override def isSolved: Boolean = field.solved
}
