package de.htwg.se.kakuro.model.fieldComponent.FieldImpl

import de.htwg.se.kakuro.model.fieldComponent.CellInterface
import org.apache.logging.log4j.{ LogManager, Logger }

case class Cell(
    override val isWhite: Boolean = false,
    override val isBlack: Boolean = false,
    override val value: Int,
    override val rightSum: Int,
    override val downSum: Int
) extends CellInterface {
  val logger: Logger = LogManager.getLogger(this.getClass.getName)
  def this() = this(false, false, 0, 0, 0)
  def this(value: Int) = this(true, false, value, 0, 0)
  def this(rightSum: Int, downSum: Int) = this(false, true, 0, rightSum, downSum)
  val whiteStrings = List("  ", " 1", " 2", " 3", " 4", " 5", " 6", " 7", " 8", " 9")

  override def hasRight: Boolean = rightSum > 0
  override def hasDown: Boolean = downSum > 0

  override def toStringRight: String = {
    if (isWhite) {
      logger.debug("toStringRight - " + value)
      whiteStrings(value)
    } else {
      if (rightSum <= 0) {
        "##"
      } else {
        if (rightSum < 10) {
          " " + rightSum
        } else {
          rightSum.toString
        }
      }
    }
  }

  override def toStringDown: String = {
    if (isWhite) {
      "\"\""
    } else {
      if (downSum <= 0) {
        "##"
      } else {
        if (downSum < 10) {
          " " + downSum
        } else {
          downSum.toString
        }
      }
    }
  }

  override def toString: String = {
    if (isWhite)
      "W " + value
    else if (isBlack)
      "B >" + rightSum + ", v" + downSum
    else
      "_"
  }

  override def isSet: Boolean = if (isWhite) value > 0 else true

}
