package de.htwg.se.kakuro.controller.controllerComponent

import de.htwg.se.kakuro.controller.controllerComponent.GameStatus.GameStatus
import de.htwg.se.kakuro.model.fieldComponent.{ CellInterface, FieldInterface }

import scala.swing.Publisher

trait ControllerInterface extends Publisher {

  def width: Int
  def height: Int
  def isValid: Boolean
  def isSolved: Boolean
  def createEmptyGrid(size: Int): Unit
  def undo(): Unit
  def redo(): Unit
  def save(): Unit
  def load(): Unit
  def check(): Boolean
  def setGameResult(): Unit
  def isSet(row: Int, col: Int): Boolean
  def initField(): Unit
  def cell(row: Int, col: Int): CellInterface
  def set(row: Int, col: Int, value: Int): Unit
  def set(row: Int, col: Int): Unit
  def set(row: Int, col: Int, rightSum: Int, downSum: Int): Unit
  def set(value: Int): Unit
  def clear(row: Int, col: Int): Unit
  def isWhite(row: Int, col: Int): Boolean
  def fieldToString: String
  def selectCell(row: Int, col: Int): Unit
  def isSelected(row: Int, col: Int): Boolean
  def getSelected: (Int, Int)
  def hasSelect: Boolean
  def available(row: Int, col: Int): Set[Int]
  def gameStatus: GameStatus
  def statusText: String
  def getField: FieldInterface
  def setField(field: FieldInterface): Unit
}

import scala.swing.event.Event

class CellChanged extends Event
class SelectorChanged extends Event