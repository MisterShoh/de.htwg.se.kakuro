package de.htwg.se.kakuro.model.fieldComponent.FieldImpl

import de.htwg.se.kakuro.model.fieldComponent.{ BlackCellInterface, SumInterface }

case class BlackCell(rightVal: Int = 0, downVal: Int = 0, rightSum: SumInterface, downSum: SumInterface) extends BlackCellInterface {

  //def this(rightVal: Int, downVal: Int, row: Int, col: Int) = this(rightVal, downVal, new Sum(), new Sum(), row, col)

  override def isWhite: Boolean = false
  override def isBlack: Boolean = true
  override def isSet: Boolean = true

  override def showCandidates: Boolean = false

  override def toStringRight: String = {
    if (rightVal <= 0) {
      "##"
    } else {
      if (rightVal < 10) {
        " " + rightVal
      } else {
        rightVal.toString
      }
    }
  }
  override def copy: BlackCell = {
    BlackCell(this.rightVal, this.downVal, this.rightSum, this.downSum)
  }
  override def toStringDown: String = {
    if (downVal <= 0) {
      "##"
    } else {
      if (downVal < 10) {
        " " + downVal
      } else {
        downVal.toString
      }
    }
  }

  override def sumCount: Int = {
    var c: Int = 0
    if (rightVal > 0) c += 1
    if (downVal > 0) c += 1
    c
  }
}

