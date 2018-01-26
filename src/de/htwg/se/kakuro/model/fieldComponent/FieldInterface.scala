package de.htwg.se.kakuro.model.fieldComponent

import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.Cell

trait FieldInterface {

  def cell(row: Int, col: Int): CellInterface
  def set(row: Int, col: Int): FieldInterface
  def set(row: Int, col: Int, value: Int): FieldInterface
  def set(row: Int, col: Int, rightSum: Int, downSum: Int): FieldInterface
  //def cells(): Matrix[CellInterface]
  //def blackCells(): Vector[CellInterface]
  def sums: Vector[SumInterface]
  //def generateSums: FieldInterface
  //def rows(row: Int): Vector[CellInterface]
  //def sums: Set[SumInterface]
  //def grid: Matrix[CellInterface]
  //def coords(cell: CellInterface): (Int, Int)
  //def putSum(s: SumInterface): FieldInterface
  def generateSums(): FieldInterface
  def isBlack(row: Int, col: Int): Boolean
  def isWhite(row: Int, col: Int): Boolean
  def isNone(row: Int, col: Int): Boolean
  //def cells(): Seq[CellInterface]
  def reset(row: Int, col: Int): FieldInterface
  //def createNewField(size: Int): FieldInterface
  //def showCandidates(row: Int, col: Int): Set[Int]
  //def isShowCandidates(row: Int, col: Int): Boolean
  //def setShowCandidates(row: Int, col: Int): FieldInterface
  //def toggleShotAllCandidates(): Unit
  //def unsetShowCandidates(row: Int, col: Int): FieldInterface
  def valid: Boolean
  def solved: Boolean

  def width: Int
  def height: Int

  //def isHighlighted(row: Int, col: Int): Boolean
  //def available(row: Int, col: Int): Set[Int]
}

//Own File for everything

trait CellInterface {
  def isWhite: Boolean
  def isBlack: Boolean
  def toStringRight: String
  def toStringDown: String
  def isSet: Boolean
  //def showCandidates: Boolean
  def rightSum: Int
  def downSum: Int
  def value: Int
  def hasRight: Boolean
  def hasDown: Boolean
  //def hSum: SumInterface
  //def vSum: SumInterface
}

trait SumInterface {
  //def isHorizontal: Boolean
  //def isVertical: Boolean = !isHorizontal
  def isValid: Boolean
  def isSolved: Boolean
  def sumValue: Int
  def current: Int
  def getCandidates: Set[Int]
  def members: Vector[Cell]

}
