package de.htwg.se.kakuro.aview

import de.htwg.se.kakuro.controller.controllerComponent.controllerImpl.Controller
import de.htwg.se.kakuro.controller.controllerComponent.{ CandidatesChanged, CellChanged }
import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.LogManager

import scala.swing.Reactor
// https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/

class Tui(controller: Controller) extends Reactor {
  val logger: Logger = LogManager.getLogger(this.getClass.getName)
  listenTo(controller)
  //def size: Int = controller.width

  /*
	reset,create,solve,undo,redo
	check
	*/
  def handleInput(input: String): Unit = {
    input.charAt(0) match {
      case 's' => setIn(input)
      case 'd' => deleteIn(input)
      case 'u' => controller.undo()
      case _ => handle2(input)
    }
  }

  def handle2(input: String): Unit = {
    input match {
      case "q" =>
      case "n" => controller.createEmptyGrid(0)
      case "z" => controller.undo()
      case "y" => controller.redo()
      case _ => input.toList.filter(c => c != ' ').map(c => c.toString.toInt) match {
        case row :: col :: value :: Nil => controller.set(row, col, value)
        case row :: col :: Nil => controller.showCandidates(row, col)
        //case index::Nil => controller.highlight(index)
        case _ => println("didn't understand input: please try again")
      }
    }
  }

  reactions += {
    case event: CellChanged => printTui()
    case event: CandidatesChanged => printCandidates
  }
  def isNumber(x: String): Boolean = x forall Character.isDigit

  def setIn(input: String): Unit = {
    var values = input.split(" ")
    var check = false
    if (values.length == 4) {
      var testinput = values(1) + values(2) + values(3)
      if (isNumber(testinput)) {
        var row = values(1).toInt
        var col = values(2).toInt
        var value = values(3).toInt
        controller.set(row, col, value)
        logger.debug("setIn() row: " + row + " col: " + col + " rt set():" +
          "length: " + values.length)
      }
    }
  }

  def deleteIn(input: String): Unit = {
    var values = input.split(" ")
    if (values.length == 3) {
      var test = values(1) + values(2)
      if (isNumber(test)) {
        var row = values(1).toInt
        var col = values(2).toInt
        controller.clear(row, col)
        logger.debug("deleteIn() row: " + row + " col: " + col + " rt delete():" +
          " length: " + values.length)
      }
    }

  }

  def printTui(): Unit = {
    logger.info("\n" + controller.fieldToString)
    logger.info("Wert setzen: s row col value")
    logger.info("Wert löschen: d row col")
    logger.info("Undo: u")
  }

  def printCandidates(): Unit = {
    println("Candidates: ")
    for (row <- 0 until controller.width; col <- 0 until controller.height) {
      if (controller.isShowCandidates(row, col)) println("(" + row + "," + col + "):" + controller.available(row, col).toList.sorted)
    }
  }
}

