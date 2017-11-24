package de.htwg.se.kakuro

import de.htwg.se.kakuro.model.Player
import de.htwg.se.kakuro.model.Field
import de.htwg.se.kakuro.model.Cell

object Kakuro {
  def main(args: Array[String]): Unit = {
    var field = Field(8, 8)
    field.initField()
    println(field)
  }
}