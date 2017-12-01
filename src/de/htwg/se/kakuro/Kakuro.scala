package de.htwg.se.kakuro

import de.htwg.se.kakuro.model.Player
import de.htwg.se.kakuro.model.Field
import de.htwg.se.kakuro.model.Cell
import de.htwg.se.kakuro.controller.Controller
import de.htwg.se.kakuro.aview.Tui

object Kakuro {
  def main(args: Array[String]): Unit = {
    var field = Field(8, 8)
    val controller = new Controller(field)
    //controller.createSampleField()
    field = controller.initField()
    println(field)
    //val tui = new Tui(controller.field)

  }
}