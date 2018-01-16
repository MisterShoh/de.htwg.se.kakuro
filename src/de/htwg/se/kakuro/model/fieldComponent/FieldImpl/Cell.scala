package de.htwg.se.kakuro.model.fieldComponent.FieldImpl

import de.htwg.se.kakuro.model.fieldComponent.CellInterface
import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.LogManager

case class Cell(isWhite: Boolean = false) extends CellInterface {
  val logger = LogManager.getLogger(this.getClass.getName)

  def isBlack: Boolean = !isWhite

  /*
  def this(value: Int) = {
    this(0, 0)
    this.value = value
  }
  */

  //def getPosition(): (Int, Int) = {
  //  (rowVal, colVal)
  //}

  def toStringRight(): String = {
    "##"
  }

  def toStringDown(): String = {
    "##"
  }
}
