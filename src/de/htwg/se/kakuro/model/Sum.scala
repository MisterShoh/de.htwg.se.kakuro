package de.htwg.se.kakuro.model

import scala.math.sqrt
import scala.util.Random
import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.LogManager
case class Sum(infoCell: Cell, members: Set[Cell], isHorizontal: Boolean) {
  val logger = LogManager.getLogger(this.getClass.getName)
  def sumValue: Int = if (isHorizontal) infoCell.valueRight else infoCell.valueDown

  def isValid(): Boolean = {
    sumValue > members.foldLeft(0)(_ + _.whiteCellValue)
  }

  def getSum(): Int = {
    sumValue
  }
  def getCurrentSum(): Int = {
    members.foldLeft(0)(_ + _.whiteCellValue)
  }
}
