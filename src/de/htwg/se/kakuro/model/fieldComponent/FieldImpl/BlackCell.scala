package de.htwg.se.kakuro.model.fieldComponent.FieldImpl

import de.htwg.se.kakuro.model.fieldComponent.BlackCellInterface

case class BlackCell(rightSum: Int = 0, downSum: Int = 0) extends BlackCellInterface {
  //override def rightSum: Int = ???

  //override def downSum: Int = ???

  override def isWhite: Boolean = false

  override def toStringRight: String = {
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

  override def toStringDown: String = {
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

  override def sumCount: Int = {
    var c: Int = 0
    if (rightSum > 0) c += 1
    if (downSum > 0) c += 1
    c
  }
}