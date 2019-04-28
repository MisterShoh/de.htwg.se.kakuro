package de.htwg.se.kakuro.aview

import akka.actor.ActorSystem
import akka.http.javadsl.server.directives.RouteDirectives
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ ContentTypes, HttpEntity }
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{ Route, StandardRoute }
import akka.stream.ActorMaterializer
import de.htwg.se.kakuro.controller.controllerComponent.controllerImpl.Controller
import de.htwg.se.kakuro.controller.controllerComponent.ControllerInterface

class HttpServer(controller: ControllerInterface, tui: Tui) {

  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()
  // needed for the future flatMap/onComplete in the end
  implicit val executionContext = system.dispatcher

  val route: Route = get {
    pathSingleSlash {
      complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, "<h1>HTWG Kakuro</h1>"))
    }
    path("kakuro") {
      gridtoHtml
    } ~ path("kakuro" / "new") {
      controller.initField
      gridtoHtml
    } ~ path("kakuro" / "undo") {
      controller.undo
      gridtoHtml
    } ~ path("kakuro" / "redo") {
      controller.redo
      gridtoHtml
    } ~ path("kakuro" / "save") {
      controller.save
      gridtoHtml
    } ~ path("kakuro" / "load") {
      controller.load
      gridtoHtml
    } ~ path("kakuro" / "set" / Segment) { command =>
      {
        processInputLine(command)
        gridtoHtml
      }
    } ~ path("kakuro" / "clear" / Segment) { command =>
      {
        processInputLine(command)
        gridtoHtml
      }
    }
  }

  def gridtoHtml: StandardRoute = {
    complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>HTWG Kakuro</h1>" + toHtml))
  }

  val bindingFuture = Http().bindAndHandle(route, "localhost", 8090)

  def unbind = {
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }

  def processInputLine(input: String): Unit = {
    input.toList.filter(c => c != ' ').map(c => c.toString.toInt) match {
      case row :: column :: value :: Nil => controller.set(row, column, value)
      case row :: column :: Nil => controller.clear(row, column)
      case _ =>
    }
  }

  def toHtml: String = "<p  style=\"font-family:'Lucida Console', monospace\"> " + tui.toString().replace("\n", "<br>").replace("  ", " _") + "</p>"
}