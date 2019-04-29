package de.htwg.se.kakuro.controller.controllerComponent.checkImpl

import de.htwg.se.kakuro.controller.controllerComponent.controllerImpl.Controller
import akka.actor.Actor

class Checker extends Actor {

  import Checker._

  def receive: Receive = {
    case Check(controller) => {
      check(controller) match {
        case true => controller.setGameResult()
        case false =>
      }
    }
    case _ => sender ! Failed
  }

  def check(contr: Controller): Boolean = {
    //Zeilen
    contr.field.cell(1, 1).value + contr.field.cell(1, 2).value + contr.field.cell(1, 3).value == 16 &&
      contr.field.cell(1, 5).value + contr.field.cell(1, 6).value + contr.field.cell(1, 7).value == 24 &&
      contr.field.cell(2, 1).value + contr.field.cell(2, 2).value == 17 &&
      contr.field.cell(2, 4).value + contr.field.cell(2, 5).value + contr.field.cell(2, 6).value + contr.field.cell(2, 7).value == 29 &&
      contr.field.cell(3, 1).value + contr.field.cell(3, 2).value + contr.field.cell(3, 3).value + contr.field.cell(3, 4).value + contr.field.cell(3, 5).value == 35 &&
      contr.field.cell(4, 2).value + contr.field.cell(4, 3).value == 7 &&
      contr.field.cell(4, 5).value + contr.field.cell(4, 6).value == 8 &&
      contr.field.cell(5, 3).value + contr.field.cell(5, 4).value + contr.field.cell(5, 5).value + contr.field.cell(5, 6).value + contr.field.cell(5, 7).value == 16 &&
      contr.field.cell(6, 1).value + contr.field.cell(6, 2).value + contr.field.cell(6, 3).value + contr.field.cell(6, 4).value == 21 &&
      contr.field.cell(6, 6).value + contr.field.cell(6, 7).value == 5 &&
      contr.field.cell(7, 1).value + contr.field.cell(7, 2).value + contr.field.cell(7, 3).value == 6 &&
      contr.field.cell(7, 6).value + contr.field.cell(7, 7).value == 3 &&
      //Spalten
      contr.field.cell(1, 1).value + contr.field.cell(2, 1).value + contr.field.cell(3, 1).value == 23 &&
      contr.field.cell(1, 2).value + contr.field.cell(2, 2).value + contr.field.cell(3, 2).value + contr.field.cell(4, 2).value == 30 &&
      contr.field.cell(1, 5).value + contr.field.cell(2, 5).value + contr.field.cell(3, 5).value + contr.field.cell(4, 5).value + contr.field.cell(4, 5).value == 27 &&
      contr.field.cell(1, 6).value + contr.field.cell(2, 6).value == 12 &&
      contr.field.cell(1, 7).value + contr.field.cell(2, 7).value == 16 &&
      contr.field.cell(3, 3).value + contr.field.cell(4, 3).value + contr.field.cell(5, 3).value + contr.field.cell(6, 3).value + contr.field.cell(7, 3).value == 15 &&
      contr.field.cell(4, 6).value + contr.field.cell(5, 6).value + contr.field.cell(6, 6).value + contr.field.cell(7, 7).value == 12 &&
      contr.field.cell(5, 4).value + contr.field.cell(6, 4).value == 7 &&
      contr.field.cell(5, 7).value + contr.field.cell(6, 7).value + contr.field.cell(7, 7).value == 7 &&
      contr.field.cell(6, 1).value + contr.field.cell(7, 1).value == 11 &&
      contr.field.cell(6, 2).value + contr.field.cell(7, 2).value == 10
  }
}

object Checker {

  case class Check(controller: Controller)

  //case object Done

  case object Failed

}

