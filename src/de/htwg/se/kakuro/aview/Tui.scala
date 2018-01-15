package de.htwg.se.kakuro.aview

import de.htwg.se.kakuro.model.{ Cell, Field }
import de.htwg.se.kakuro.controller.Controller
import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.LogManager
import scala.swing.Reactor
// https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/
class Tui(controller: Controller) extends Reactor {
  val logger = LogManager.getLogger(this.getClass.getName)
  listenTo(controller)
  /*
	reset,create,solve,undo,redo
	check
	*/
  def handleInput(input: String): Boolean = {
    var firstChar = input.charAt(0)
    var retVal = false
    firstChar = firstChar.toChar
    firstChar match {
      case 's' => retVal = setIn(input)
      case 'd' => retVal = deleteIn(input)
    }
    return retVal
  }
  def isDigit(x: String) = x forall Character.isDigit

  def setIn(input: String): Boolean = {
    var values = input.split(" ")
    var check = false
    if (values.length == 4) {
      var testinput = values(1) + values(2) + values(3)
      if (isDigit(testinput)) {
        var row = values(1).toInt
        var col = values(2).toInt
        var value = values(3).toInt
        check = controller.set(row, col, value)
        check
      } else false;
    } else false
    false
  }
  def deleteIn(input: String): Boolean = {
    var values = input.split(" ")
    var checkdelete = false
    if (values.length == 3) {
      var test = values(1) + values(2)
      if (isDigit(test)) {
        var row = values(1).toInt
        var col = values(2).toInt
        checkdelete = controller.delete(row, col)
        logger.debug("deleteIn() row: " + row + " col: " + col + " return value delete():" + checkdelete +
          "length: " + values.length)
        checkdelete
      } else false
    }
    checkdelete
  }
}

/* 

case d =>
        var values = input.split(" ")
        if (values.length != 3) return false
        var testinput = values(1) + values(2)
        println(testinput)
        if (isDigit(testinput)) {
          var row = values(1).toInt
          var col = values(2).toInt
          var check = controller.delete(row, col)
          return check
        } else return false; 
        */ 