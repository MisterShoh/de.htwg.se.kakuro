package de.htwg.se.kakuro.model.fieldComponent.FieldImpl

import de.htwg.se.kakuro.model.fieldComponent.{ SumInterface, WhiteCellInterface }

case class WhiteCell(value: Int = 0, showCandidates: Boolean = false) extends WhiteCellInterface {

  /*
  def this(value: Int) = {
    this(0, 0)
    this.value = value
  }
  */

  override def isSet: Boolean = value > 0

  override def isWhite: Boolean = true

  override def toStringRight: String = {
    if (value == 0) {
      Console.REVERSED + "  " + Console.RESET
    } else {
      if (value < 10) {
        Console.REVERSED + " " + value.toString + Console.RESET
      } else {
        Console.REVERSED + value + Console.RESET
      }
    }
  }
  override def copy: WhiteCell = {
    WhiteCell(this.value, this.showCandidates)
  }
  override def toStringDown: String = Console.REVERSED + "□□" + Console.RESET

  //override def hSum: SumInterface = ???

  //override def vSum: SumInterface = ???
}
