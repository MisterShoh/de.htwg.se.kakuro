package de.htwg.se.kakuro.model.fieldComponent.FieldImpl

import de.htwg.se.kakuro.model.fieldComponent.Cell
import de.htwg.se.kakuro.model.fieldComponent.{CellInterface, FieldInterface}
import de.htwg.se.model.fieldComponent.FieldImpl.Matrix
import org.apache.logging.log4j.LogManager

case class Field(height: Int, width: Int) extends FieldInterface {

  val logger = LogManager.getLogger(this.getClass.getName)
  var grid = Array.ofDim[Cell](height, width)

  override def toString(): String = {
    var result: String = " 0  1  2  3  4  5  6  7\n"
    result += "+--+--+--+--+--+--+--+--+\n"
    var flength = grid.length - 1
    for (j <- 0 until height) {
      result += stringRow(j)
    }
    return result
  }

  def stringRow(row: Int): String = {
    var result: String = "|"
    for (i <- grid(row).indices) {
      result += grid(row)(i).toStringRight
      result += "|"
    }
    result += " " + row + "\n|"
    for (i <- grid(row).indices) {
      result += grid(row)(i).toStringDown
      result += "|"
    }
    result += "\n+"
    for (i <- grid(row).indices) {
      result += "--+"
    }
    result += "\n"
    return result
  }


}
