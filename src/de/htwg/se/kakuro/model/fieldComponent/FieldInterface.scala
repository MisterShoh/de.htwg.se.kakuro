package de.htwg.se.kakuro.model.fieldComponent

import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.Matrix
import de.htwg.se.kakuro.model.fieldComponent.{ CellInterface, BlackCellInterface, SumInterface }

trait FieldInterface {

  //def cell(row: Int, col: Int): FullCellInterface
  def set(row: Int, col: Int): FieldInterface
  def set(row: Int, col: Int, value: Int): FieldInterface
  def set(row: Int, col: Int, rightSum: Int, downSum: Int): FieldInterface
  //def cells(): Matrix[CellInterface]
  //def blackCells(): Vector[BlackCellInterface]
  //def sums(): Vector[SumInterface]
  def sums(): Set[SumInterface]
  def putSum(s: SumInterface): FieldInterface

  //def cells(): Seq[CellInterface]
  def reset(row: Int, col: Int): FieldInterface
  def createNewGrid(size: Int): FieldInterface
  def showCandidates(row: Int, col: Int): Set[Int]
  def isShowCandidates(row: Int, col: Int): Boolean
  def setShowCandidates(row: Int, col: Int): FieldInterface
  def toggleShotAllCandidates(): Unit
  def unsetShowCandidates(row: Int, col: Int): FieldInterface
  def valid: Boolean
  def solved: Boolean

  def width: Int
  def height: Int

  //def isHighlighted(row: Int, col: Int): Boolean
  def available(row: Int, col: Int): Set[Int]
}

//Own File for everything

trait FullCellInterface {
  def isWhite: Boolean
  def isBlack: Boolean
  def toStringRight: String
  def toStringDown: String
  def isSet: Boolean
  def showCandidates: Boolean
  def rightSum: Int
  def downSum: Int
  def value: Int
  //def hSum: SumInterface
  //def vSum: SumInterface
}

trait CellInterface {
  def isWhite: Boolean
  def isBlack: Boolean
  def toStringRight: String
  def toStringDown: String
  def isSet: Boolean
  def showCandidates: Boolean
}

trait BlackCellInterface extends CellInterface {
  //def rightSum: SumInterface
  //def downSum: SumInterface
  def rightVal: Int
  def downVal: Int
  def sumCount: Int
}

trait WhiteCellInterface extends CellInterface {
  def value: Int
  //def hSum: SumInterface
  //def vSum: SumInterface
}

trait SumInterface {
  def isHorizontal: Boolean
  def isVertical: Boolean = !isHorizontal
  def isValid: Boolean
  def isSolved: Boolean
  def sumValue: Int
  def current: Int
  def getCandidates: Set[Int]
  def members: Seq[WhiteCellInterface]

}

trait AltSumInterface {
  def isHorizontal: Boolean
  def isVertical: Boolean = !isHorizontal
  def isValid: Boolean
  def isSolved: Boolean
  def blackCell: BlackCellInterface
  def members: Seq[WhiteCellInterface]
}

