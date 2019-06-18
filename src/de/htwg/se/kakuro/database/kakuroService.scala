package de.htwg.se.kakuro.database

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import scala.io.StdIn
import scala.concurrent.Future
import play.api.libs.json._
import akka.http.scaladsl.model.StatusCodes.MovedPermanently
import akka.http.scaladsl.server.Directives
import spray.json._

case class Aktion(task: String, row: Int, col: Int, value: Int)

class kakuroService extends Directives with SprayJsonSupport {

  import DefaultJsonProtocol._

  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()
  // needed for the future flatMap/onComplete in the end
  implicit val executionContext = system.dispatcher

  implicit val templateFormat = jsonFormat4(Aktion)

  val dbs = new databaseService()

  val route = {
    pathSingleSlash {
      get {
        complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, "<h1>HTWG Kakuro</h1>"))
      }
    } ~ path("api") {
      get {
        val field = Json.obj(
          "status" -> "success",
          "Username" -> "Shohrukh1992",
          "Firstname" -> "Shohrukh",
          "Surname" -> "Koyirov",
          "Birthplace" -> "Uzbekistan",
          "Birthdate" -> "05.01.1992",
          "Email" -> "herr_shoh@mail.ru",
          "Passwort" -> "************"
        )
        complete(HttpEntity(ContentTypes.`application/json`, field.toString))
      }
    } ~ path("api" / "kakuro" / "fields") {
      get {
        val field = dbs.getField()
        complete(HttpEntity(ContentTypes.`application/json`, field.toString()))
      }
    } ~ path("api" / "test") {
      post {
        entity(as[Aktion]) { // unmarshaller applied
          aktion: Aktion =>
            println(aktion)
            complete {
              aktion // marshaller applied
            }
        }
      }
    }
  }

  // When running in docker do not use localhost because of the nating!
  val bindingFuture = Http().bindAndHandle(route, "0.0.0.0", 8080)

  println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
  StdIn.readLine() // let it run until user presses return
  bindingFuture
    .flatMap(_.unbind()) // trigger unbinding from the port
    .onComplete(_ => system.terminate()) // and shutdown when done
}
