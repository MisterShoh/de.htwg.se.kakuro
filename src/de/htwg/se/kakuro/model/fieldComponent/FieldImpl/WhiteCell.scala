package de.htwg.se.kakuro.model.fieldComponent.FieldImpl

import de.htwg.se.kakuro.model.fieldComponent.WhiteCellInterface

case class WhiteCell (value:Int =0, showCandidates:Boolean = false)extends WhiteCellInterface{

  //override def value: Int = value
  //override def showCandidates: Boolean = false

  override def isSet: Boolean = value > 0

  override def isWhite = true

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

  override def toStringDown: String = Console.REVERSED + "**" + Console.RESET

}
