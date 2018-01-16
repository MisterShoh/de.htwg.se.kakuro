package de.htwg.se.kakuro.model.fieldComponent.FieldImpl

import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.LogManager

class Sum(black: BlackCell, members: Set[WhiteCell], isHorizontal: Boolean = true) {
  val logger: Logger = LogManager.getLogger(this.getClass.getName)
  def sumValue: Int = if (isHorizontal) black.rightSum else black.downSum
  def current: Int = members.foldLeft(0)(_ + _.value)

  def isValid: Boolean = {
    sumValue >= current
  }

  def isSolved: Boolean = {
    sumValue == current
  }

  /*
  def getValue(): Int = {
    sumValue
  }
  def getCurrent(): Int = {
    members.foldLeft(0)(_ + _.whiteCellValue)
  }
  */

}
