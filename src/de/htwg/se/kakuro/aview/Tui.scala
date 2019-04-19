package de.htwg.se.kakuro.aview

import de.htwg.se.kakuro.controller.controllerComponent.{ CellChanged, ControllerInterface }
import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.LogManager
import scala.swing.Reactor

class Tui(controller: ControllerInterface) extends Reactor {
  val logger: Logger = LogManager.getLogger(this.getClass.getName)
  listenTo(controller)

  def handleInput(input: String): Unit = {
    input.charAt(0) match {
      case 's' => setIn(input)
      case 'd' => reset(input)
      case 'u' => controller.undo()
      case 'r' => controller.redo()
      case 'S' => controller.save()
      case 'l' => controller.load()
      case _ => logger.info("didn't understand input: please try again")
    }
  }

  reactions += {
    case event: CellChanged => {
      logger.debug("reactions Tui Cellchanged")
      printTui()
    }
  }
  def isNumber(x: String): Boolean = x forall Character.isDigit

  def setIn(input: String): Unit = {
    var values = input.split(" ")
    var check = false
    if (values.length == 3) {
      var test = values(1) + values(2)
      if (isNumber(test)) {
        var row = values(1).toInt
        var col = values(2).toInt
        if (isValid(col) && isValid(row)) {
          controller.set(row, col)
          logger.debug("deleteIn() row: " + row + " col: " + col + " rt delete():" +
            " length: " + values.length)
        } else logger.info("didn't understand input: please try again")
      }
    }
    if (values.length == 4) {
      var testinput = values(1) + values(2) + values(3)
      if (isNumber(testinput)) {
        var row = values(1).toInt
        var col = values(2).toInt
        var value = values(3).toInt
        if (isValid(value) && isValid(col) && isValid(row)) {
          controller.set(row, col, value)
          logger.debug("setIn() row: " + row + " col: " + col + " rt set():" +
            "length: " + values.length)
        } else logger.info("didn't understand input: please try again")

      }
    }
  }
  def reset(input: String): Unit = {
    var values = input.split(" ")
    var check = false
    if (values.length == 3) {
      var test = values(1) + values(2)
      if (isNumber(test)) {
        var row = values(1).toInt
        var col = values(2).toInt
        controller.clear(row, col)
        logger.debug("reset() row: " + row + " col: " + col + " length: " + values.length)
      }
    }
  }

  def printTui(): Unit = {
    logger.info("\n" + toString)
    logger.info("Open GUI: gui")
    logger.info("Wert setzen: s row col value")
    logger.info("Wert löschen: d row col")
    logger.info("Undo: u")
    logger.info("Redo: r")
    logger.info("Save: s")
    logger.info("Load: l")
  }
  def isValid(value: Int): Boolean = {
    if (value <= 9 && value >= 0) {
      return true
    }
    false
  }

  override def toString: String = {
    //logger.debug("field.toString()")
    var result: String = "\n 0  1  2  3  4  5  6  7\n"
    result += "+--+--+--+--+--+--+--+--+\n"
    //var flength = grid.length - 1
    for (j <- 0 until controller.width) {
      result += stringRow(j)
    };
    result // + "\n" + printDebug()
  }

  def stringRow(row: Int): String = {
    logger.debug("field.stringRow()")
    var result: String = "|"
    for (col <- 0 until controller.width) {
      result += controller.cell(row, col).toStringRight
      //result += grid(row).toStringRight
      result += "|"
    }
    result += " " + row + "\n|"
    for (col <- 0 until controller.width) {
      result += controller.cell(row, col).toStringDown
      //result += grid(row)(i).toStringDown
      result += "|"
    }
    result += "\n+"
    for (i <- 0 until controller.width) {
      result += "--+"
    }
    result += "\n"
    result
  }
}