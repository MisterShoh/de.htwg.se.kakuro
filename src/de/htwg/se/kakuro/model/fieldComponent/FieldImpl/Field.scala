package de.htwg.se.kakuro.model.fieldComponent.FieldImpl

import com.google.inject.Inject
import de.htwg.se.kakuro.model.fieldComponent.{ CellInterface, FieldInterface, SumInterface }
import org.apache.logging.log4j.{ LogManager, Logger }

case class Field(grid: Matrix[Cell]) extends FieldInterface {

  val logger: Logger = LogManager.getLogger(this.getClass.getName)
  def this(width: Int, height: Int) = this(new Matrix[Cell](width, height, new Cell()))
  def this(size: Int) = this(new Matrix[Cell](size, new Cell()))

  override def set(row: Int, col: Int, value: Int): FieldInterface = {
    logger.debug("field.set(row: Int, col: Int, value: Int)" + "value - " + value)
    copy(grid.replaceCell(row, col, new Cell(value)))
  }

  override def set(row: Int, col: Int): FieldInterface =
    copy(grid.replaceCell(row, col, new Cell()))

  override def set(row: Int, col: Int, rightVal: Int, downVal: Int): FieldInterface = {
    copy(grid.replaceCell(row, col, new Cell(rightVal, downVal)))
  }

  override def reset(row: Int, col: Int): FieldInterface =
    copy(grid.replaceCell(row, col, new Cell(0)))

  override def toString: String = {
    logger.debug("field.toString()")
    var result: String = "\n 0  1  2  3  4  5  6  7\n"
    result += "+--+--+--+--+--+--+--+--+\n"
    for (j <- 0 until width) {
      result += stringRow(j)
    }
    result
  }

  def stringRow(row: Int): String = {
    logger.debug("field.stringRow()")
    var result: String = "|"
    for (col <- 0 until width) {
      result += grid.cell(row, col).toStringRight
      result += "|"
    }
    result += " " + row + "\n|"
    for (col <- 0 until width) {
      result += grid.cell(row, col).toStringDown
      result += "|"
    }
    result += "\n+"
    for (i <- 0 until width) {
      result += "--+"
    }
    result += "\n"
    result
  }
  def cell(row: Int, col: Int): CellInterface = grid.cell(row, col)

  override def createNewField(size: Int): FieldInterface = (new FieldCreator).createNewField(size)

  override def width: Int = grid.width

  override def height: Int = grid.height

  override def isBlack(row: Int, col: Int): Boolean = cell(row, col).isBlack

  override def isWhite(row: Int, col: Int): Boolean = cell(row, col).isWhite

  override def isNone(row: Int, col: Int): Boolean = !isWhite(row, col) && !isBlack(row, col)
}
