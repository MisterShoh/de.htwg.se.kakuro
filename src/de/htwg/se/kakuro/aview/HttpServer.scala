package de.htwg.se.kakuro.aview

import scala.io.Source
import scala.concurrent.Future
import scala.util.{ Failure, Success }
import java.io.IOException
import java.net.{ URL, HttpURLConnection }
import java.util.ArrayList
import akka.actor.ActorSystem
import akka.http.javadsl.server.directives.RouteDirectives
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
//import akka.http.scaladsl.model.{ ContentTypes, HttpEntity }
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{ Route, StandardRoute }
import akka.util.ByteString
import akka.stream.ActorMaterializer
import org.apache.http.client._
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.impl.client.HttpClients
import org.apache.http.NameValuePair
import org.apache.http.message.BasicNameValuePair
import org.apache.http.client.entity.UrlEncodedFormEntity
import de.htwg.se.kakuro.controller.controllerComponent.controllerImpl.Controller
import de.htwg.se.kakuro.controller.controllerComponent.ControllerInterface
import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.{ Field, FieldCreator } //FieldCreator unused
import de.htwg.se.kakuro.model.fieldComponent.{ CellInterface, FieldInterface }
import de.htwg.se.kakuro.model.fileIOComponent.FileIOInterface //unused
import play.api.libs.json._

class HttpServer(controller: ControllerInterface, tui: Tui) {

  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()
  // needed for the future flatMap/onComplete in the end
  implicit val executionContext = system.dispatcher

  val route: Route = get {
    pathSingleSlash {
      complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, "<h1>HTWG Kakuro</h1>"))
    } ~ path("kakuro") {
      //      //gridtoHtml
      //      var jsonO = gridToJson(controller.getField)
      //      complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, jsonO.toString()))

      try {
        val content = getReqApache("http://localhost:8080/api")
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, content))
        //println(content)
      } catch {
        case ioe: java.io.IOException => complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "Fehler aufgetreten")) // handle this
        case ste: java.net.SocketTimeoutException => complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "Fehler aufgetreten")) // handle this
      }

      /*//Akka
        getReqAkka("http://localhost:8080/api")
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "Success"))*/

    } ~ path("test") {
      val field = Json.obj(
        "task" -> "set",
        "row" -> 1,
        "col" -> 1,
        "value" -> 0
      )

      postReq("http://localhost:8080/api/test", field)
      complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "Alles gut!!!"))
    } ~ path("kakuro" / "new") {

      val content = getReqApache("http://localhost:8080/api")
      complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, content))

      /*//Akka
      getReq("http://localhost:8080/api")
      complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "Success"))*/

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

  def gridToJson(grid: FieldInterface): JsObject = {
    Json.obj(
      "grid" -> Json.obj(
        "width" -> JsNumber(grid.width),
        "height" -> JsNumber(grid.height),
        "cells" -> Json.toJson(
          for {
            row <- 0 until grid.width
            col <- 0 until grid.height
          } yield {
            Json.obj(
              "row" -> row,
              "col" -> col,
              "cell" -> Json.toJson(grid.cell(row, col))
            )
          }
        )
      )
    )
  }

  implicit val cellWrites: Writes[CellInterface] {
    def writes(cell: CellInterface): JsObject
  } = new Writes[CellInterface] {

    def writes(cell: CellInterface): JsObject = Json.obj(
      "value" -> cell.value,
      "right" -> cell.rightSum,
      "down" -> cell.downSum,
      "type" -> { if (cell.isWhite) 1 else if (cell.isBlack) 2 else 0 }
    )
  }

  def jsonToGrid(jsonField: JsObject): Option[FieldInterface] = {

    var gridOption: Option[FieldInterface] = None
    val source: String = jsonField.toString
    val json: JsValue = Json.parse(source)
    val width: Int = (json \ "grid" \ "width").get.toString.toInt
    val height: Int = (json \ "grid" \ "height").get.toString.toInt

    gridOption = Some(new Field(width, height))

    gridOption match {
      case Some(grid) => {
        var _grid = grid
        for (index <- 0 until width * height) {
          val row = (json \\ "row")(index).as[Int]
          val col = (json \\ "col")(index).as[Int]
          val cell = (json \\ "cell")(index)
          val cellType = (cell \ "type").as[Int]
          val value = (cell \ "value").as[Int]
          val right = (cell \ "right").as[Int]
          val down = (cell \ "down").as[Int]
          cellType match {
            case 0 => _grid = _grid.set(row, col)
            case 1 => _grid = _grid.set(row, col, value)
            case 2 => _grid = _grid.set(row, col, right, down)
          }
        }
        gridOption = Some(_grid)
      }
      case None =>
    }
    gridOption
  }

  def toHtml: String = "<p  style=\"font-family:'Lucida Console', monospace\"> " + tui.toString().replace("\n", "<br>").replace("  ", " _") + "</p>"

  @throws(classOf[java.io.IOException])
  @throws(classOf[java.net.SocketTimeoutException])
  def getReqApache(url: String, connectTimeout: Int = 5000, readTimeout: Int = 5000, requestMethod: String = "GET") =
    {
      val connection = (new URL(url)).openConnection.asInstanceOf[HttpURLConnection]
      connection.setConnectTimeout(connectTimeout)
      connection.setReadTimeout(readTimeout)
      connection.setRequestMethod(requestMethod)
      val inputStream = connection.getInputStream
      val content = Source.fromInputStream(inputStream).mkString
      if (inputStream != null) inputStream.close
      content
    }

  def getReqAkka(url: String) =
    {
      val post = HttpRequest(uri = url)

      val responseFuture: Future[HttpResponse] = Http().singleRequest(post)
      responseFuture
        .onComplete {
          case Success(res) => {
            println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT")
            val HttpResponse(statusCodes, headers, entity, _) = res
            println(entity)
            println("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ")
            entity.dataBytes.runFold(ByteString(""))(_ ++ _).foreach(body => println(body.utf8String))
            println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT")
          }
          case Failure(_) => sys.error("something wrong")
        }
    }

  def postReq(url: String, jSonObj: JsObject) =
    {

      val post = HttpRequest(
        method = HttpMethods.POST,
        uri = url,
        entity = HttpEntity(ContentTypes.`application/json`, jSonObj.toString())
      )

      val responseFuture: Future[HttpResponse] = Http().singleRequest(post)
      responseFuture
        .onComplete {
          case Success(res) => {
            println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT")
            println(res)
            println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT")
          }
          case Failure(_) => sys.error("something wrong")
        }
    }
}

