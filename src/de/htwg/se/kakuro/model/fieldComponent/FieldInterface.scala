package de.htwg.se.kakuro.model.fieldComponent

trait FieldInterface {

  def cell(row: Int, col: Int): CellInterface
  def set(row: Int, col: Int): FieldInterface
  def set(row: Int, col: Int, value: Int): FieldInterface
  def set(row: Int, col: Int, rightSum: Int, downSum: Int): FieldInterface
  def isBlack(row: Int, col: Int): Boolean
  def isWhite(row: Int, col: Int): Boolean
  def isNone(row: Int, col: Int): Boolean
  def reset(row: Int, col: Int): FieldInterface
  def createNewField(size: Int): FieldInterface

  def width: Int
  def height: Int

}

trait CellInterface {
  def isWhite: Boolean
  def isBlack: Boolean
  def toStringRight: String
  def toStringDown: String
  def isSet: Boolean
  def rightSum: Int
  def downSum: Int
  def value: Int
  def hasRight: Boolean
  def hasDown: Boolean
}

trait SumInterface {
  def isHorizontal: Boolean
  def isVertical: Boolean = !isHorizontal
  def isValid: Boolean
  def isSolved: Boolean
  def sumValue: Int
  def current: Int
  def getCandidates: Set[Int]
  def members: Seq[CellInterface]
}
