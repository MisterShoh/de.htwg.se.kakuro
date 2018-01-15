package de.htwg.se.kakuro.model.fieldComponent

trait FieldInterface {

  def cell(row: Int, col: Int): CellInterface
  def set(row: Int, col: Int, value: Int): FieldInterface
  def reset(row: Int, col: Int): FieldInterface
  def createNewGrid(size: Int): FieldInterface
  def valid: Boolean
  def solved: Boolean

  def size: Int

  //def isHighlighted(row: Int, col: Int): Boolean
  def available(row: Int, col: Int): Set[Int]
}

trait CellInterface {
  def isWhite: Boolean
  def toStringRight: String
  def toStringDown: String
}

trait BlackCellInterface extends CellInterface {
  def rightSum: Int
  def downSum: Int
}

trait WhiteCellInterface extends CellInterface {
  def value: Int
  def showCandidates: Boolean
  def isSet: Boolean

}

trait SumInterface {
  def isHorizontal: Boolean
  def isVertical: Boolean = !isHorizontal
  def blackCell : BlackCellInterface
  def members: Set[WhiteCellInterface]
}
