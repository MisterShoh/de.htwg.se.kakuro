package de.htwg.se.kakuro.model.fieldComponent.FieldImpl

import de.htwg.se.kakuro.model.fieldComponent.FullCellInterface

case class SuperCell(
  override val isWhite: Boolean = false,
    override val isBlack: Boolean = false,
    override val value: Int,
    override val rightSum: Int,
    override val downSum: Int
) extends FullCellInterface {

  def this() = this(false, false, 0, 0, 0)
  def this(value: Int) = this(true, false, value, 0, 0)
  def this(rightSum: Int, downSum: Int) = this(false, true, 0, rightSum, downSum)

  override def toStringRight: String = {
    if (isWhite) {
      if (value == 0) {
        Console.REVERSED + "  " + Console.RESET
      } else {
        if (value < 10) {
          Console.REVERSED + " " + value.toString + Console.RESET
        } else {
          Console.REVERSED + value + Console.RESET
        }
      }
    } else if (isBlack) {
      if (rightSum <= 0) {
        "##"
      } else {
        if (rightSum < 10) {
          " " + rightSum
        } else {
          rightSum.toString
        }
      }
    } else {
      "##"
    }
  }

  override def toStringDown: String = {
    if (isWhite) {
      ""
    } else if (isBlack) {
      if (downSum <= 0) {
        "##"
      } else {
        if (downSum < 10) {
          " " + downSum
        } else {
          downSum.toString
        }
      }
    } else {
      "##"
    }
  }

  override def isSet: Boolean = if (isWhite) value > 0 else true

  override def showCandidates: Boolean = false

  //override def value: Int = if(white) content else -1

  //override def isWhite: Boolean = white
}
