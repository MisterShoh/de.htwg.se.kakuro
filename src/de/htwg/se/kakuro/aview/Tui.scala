package de.htwg.se.kakuro.aview

import de.htwg.se.kakuro.model.{ Cell, Field }
import de.htwg.se.kakuro.controller.Controller

import scala.swing.Reactor
// https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/
class Tui(controller: Controller) extends Reactor {

  listenTo(controller)
  /*
	reset,create,solve,undo,redo
	check
	*/
  def handleInput(input: String): Boolean = {
    var firstChar = input.charAt(0)
    firstChar = firstChar.toChar
    firstChar match {
      case s =>
        var values = input.split(" ")
        if (values.length != 4) return false
        var testinput = values(1) + values(2) + values(3)
        if (isDigit(testinput)) {
          var row = values(1).toInt
          var col = values(2).toInt
          var value = values(3).toInt
          var check = controller.set(row, col, value)
          return check
        } else return false;
    }
    return true
  }
  def isDigit(x: String) = x forall Character.isDigit
}