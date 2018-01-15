package de.htwg.se.kakuro.model.fieldComponent.FieldImpl

import de.htwg.se.kakuro.model.fieldComponent.CellInterface
import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.LogManager

case class Cell (row: Int, colValue: Int) extends CellInterface{
  val logger = LogManager.getLogger(this.getClass.getName)
  var whiteCell: Boolean = false
  var rowVal: Int = row
  var colVal: Int = colValue
  var valueRight: Int = 0
  var valueDown: Int = 0
  var whiteCellValue: Int = 0

  def isSet: Boolean = whiteCellValue != 0

  def isWhite: Boolean = whiteCell
  def isBlack: Boolean = !whiteCell

  def this(value: Int) = {
    this(0, 0)
    this.whiteCellValue = value
  }

  def set(value: Int): Cell = {
    whiteCellValue = value
    this
  }

  def getPosition(): (Int, Int) = {
    (rowVal, colVal)
  }

  def toStringRight(): String = {

    if (whiteCell) {
      if (whiteCellValue == 0) {
        Console.REVERSED + "  " + Console.RESET
      } else {
        if (whiteCellValue < 10) {
          Console.REVERSED + " " + whiteCellValue.toString + Console.RESET
        } else {
          Console.REVERSED + whiteCellValue + Console.RESET
        }
      }
    } else {
      if (valueRight <= 0) {
        "##"
      } else {
        if (valueRight < 10) {
          " " + valueRight
        } else {
          valueRight.toString
        }
      }
    }
  }

  def toStringDown(): String = {
    if (whiteCell) {
      Console.REVERSED + "**" + Console.RESET
    } else {
      if (valueDown <= 0) {
        "##"
      } else {
        if (valueDown < 10) {
          " " + valueDown
        } else {
          valueDown.toString
        }
      }
    }
  }
}