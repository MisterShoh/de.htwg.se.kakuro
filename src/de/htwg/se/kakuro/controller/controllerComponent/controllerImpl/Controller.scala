package de.htwg.se.kakuro.controller.controllerComponent.controllerImpl

import akka.actor.{ ActorSystem, Props, Actor }
import com.google.inject.{ Guice, Inject }
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.kakuro.KakuroModule
import de.htwg.se.kakuro.controller.controllerComponent.GameStatus._
import de.htwg.se.kakuro.controller.controllerComponent.{ CellChanged, ControllerInterface, GameStatus, SelectorChanged }
import de.htwg.se.kakuro.controller.controllerComponent.checkImpl.Checker
import de.htwg.se.kakuro.controller.controllerComponent.checkImpl.Checker._
import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.{ Field, FieldCreator }
import de.htwg.se.kakuro.model.fieldComponent.{ CellInterface, FieldInterface }
import de.htwg.se.kakuro.model.fileIOComponent.FileIOInterface
import de.htwg.se.kakuro.util.UndoManager
import org.apache.logging.log4j.{ LogManager, Logger }

import scala.swing.Publisher

class Controller @Inject() () extends ControllerInterface with Publisher {
  var generator = new FieldCreator()
  var field: FieldInterface = generator.createNewField(8)
  val logger: Logger = LogManager.getLogger(this.getClass.getName)
  val injector = Guice.createInjector(new KakuroModule)
  val fileIo = injector.instance[FileIOInterface]
  private val undoManager = new UndoManager
  var gameStatus: GameStatus = IDLE
  def statusText: String = GameStatus.message(gameStatus)
  var selection: (Int, Int) = (-1, -1)
  var showAllCandidates: Boolean = false
  var gameResult = false

  val system = ActorSystem("MySystem")
  val actor = system.actorOf(Props[Checker], "CheckerActor")

  def setField(field: FieldInterface): Unit = {
    this.field = field
  }

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
    fileIo.save(field)
    gameStatus = SAVED
    publish(new CellChanged)
  }

  def load: Unit = {
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

  def check: Boolean = {
    actor ! Checker.Check(this)
    return gameResult
  }

  def setGameResult(): Unit = {
    gameResult = true
  }

  def initField(): Unit = { // Diese Funktion wird in Model geschoben
    var generator = new FieldCreator()
    field = generator.createNewField(8)
    //field = generator.fill(field)
    //field = generator.generateSums(field)
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

  override def fieldToString: String = field.toString
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

  override def getField: FieldInterface = field
}
