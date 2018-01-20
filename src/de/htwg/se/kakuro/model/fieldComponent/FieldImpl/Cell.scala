package de.htwg.se.kakuro.model.fieldComponent.FieldImpl

import de.htwg.se.kakuro.model.fieldComponent.CellInterface

case class Cell(
    override val isWhite: Boolean = false,
    override val isBlack: Boolean = false,
    override val value: Int,
    override val rightSum: Int,
    override val downSum: Int
) extends CellInterface {

  def this() = this(false, false, 0, 0, 0)
  def this(value: Int) = this(true, false, value, 0, 0)
  def this(rightSum: Int, downSum: Int) = this(false, true, 0, rightSum, downSum)
  val whiteStrings = List("  ", " 1", " 2", " 3", " 4", " 5", " 6", " 7", " 8", " 9")
  //override def toStringRight: String =  if(value == 0) "##" else if (value < 10) " "+ value else value.toString

  override def hasRight: Boolean = rightSum > 0
  override def hasDown: Boolean = downSum > 0

  override def toStringRight: String = {
    if (isWhite) {
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
      "**"
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

  //override def toString: String = toStringRight+"\n"+toStringDown

  override def isSet: Boolean = if (isWhite) value > 0 else true

  //override def showCandidates: Boolean = false

  //override def value: Int = if(white) content else -1

  //override def isWhite: Boolean = white
}
