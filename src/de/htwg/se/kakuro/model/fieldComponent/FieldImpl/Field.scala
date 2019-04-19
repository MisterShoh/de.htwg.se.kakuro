package de.htwg.se.kakuro.model.fieldComponent.FieldImpl

import de.htwg.se.kakuro.model.fieldComponent.{ CellInterface, FieldInterface, SumInterface }
import org.apache.logging.log4j.{ LogManager, Logger }

case class Field(grid: Matrix[Cell], sums: Set[SumInterface]) extends FieldInterface {

  val logger: Logger = LogManager.getLogger(this.getClass.getName)
  def this(grid: Matrix[Cell]) = this(grid, Set.empty[SumInterface])
  def this(width: Int, height: Int) = this(new Matrix[Cell](width, height, new Cell()))
  def this(size: Int) = this(new Matrix[Cell](size, new Cell()))

  //val sums: Set[Sum] = Set.empty[Sum]

  def putSum(s: SumInterface): Field = {
    //val newSum = sums + s
    copy(grid = grid, sums = sums + s)
  }

  override def set(row: Int, col: Int, value: Int): Field = {
    logger.debug("field.set(row: Int, col: Int, value: Int)" + "value - " + value)
    copy(grid.replaceCell(row, col, new Cell(value)))
  }

  override def set(row: Int, col: Int): Field =
    copy(grid.replaceCell(row, col, new Cell()))

  override def set(row: Int, col: Int, rightVal: Int, downVal: Int): Field = {
    copy(grid.replaceCell(row, col, new Cell(rightVal, downVal)))
  }

  override def reset(row: Int, col: Int): Field =
    copy(grid.replaceCell(row, col, new Cell(0)))

  def cell(row: Int, col: Int): Cell = grid.cell(row, col)

  //override def createNewField(size: Int): FieldInterface = (new FieldCreator).createNewField(size)

  override def valid: Boolean = sums.forall(_.isValid)
  override def solved: Boolean = sums.forall(_.isSolved)

  //override def size: Int = grid.size
  override def width: Int = grid.width

  override def height: Int = grid.height

  override def isBlack(row: Int, col: Int): Boolean = cell(row, col).isBlack

  override def isWhite(row: Int, col: Int): Boolean = cell(row, col).isWhite

  override def isNone(row: Int, col: Int): Boolean = !isWhite(row, col) && !isBlack(row, col)
}