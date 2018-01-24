package de.htwg.se.kakuro.model.fileIOComponent.fileIOJsonImpl

import de.htwg.se.kakuro.model.fieldComponent.{ CellInterface, FieldInterface }
import de.htwg.se.kakuro.model.fileIOComponent.FileIOInterface
import com.google.inject.Guice
import com.google.inject.name.Names
import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.Field
import play.api.libs.json._

import scala.io.Source

class FileIO extends FileIOInterface {
  override def load: Option[FieldInterface] = {
    var gridOption: Option[FieldInterface] = None
    val source: String = Source.fromFile("grid.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    //val size = (json \ "grid" \ "size").get.toString.toInt
    val width: Int = (json \ "grid" \ "width").get.toString.toInt
    val height: Int = (json \ "grid" \ "height").get.toString.toInt

    /*
    val injector = Guice.createInjector(new SudokuModule)
    size match {
      case 1 => gridOption = Some(injector.instance[GridInterface](Names.named("tiny")))
      case 4 => gridOption = Some(injector.instance[GridInterface](Names.named("small")))
      case 9 => gridOption = Some(injector.instance[GridInterface](Names.named("normal")))
      case _ =>
    }
    */
    gridOption = Some(new Field(width, height))

    gridOption match {
      case Some(grid) => {
        var _grid = grid
        for (index <- 0 until width * height) {
          val row = (json \\ "row")(index).as[Int]
          val col = (json \\ "col")(index).as[Int]
          val cell = (json \\ "cell")(index)
          val cellType = (cell \ "type")(index).as[Int]
          val value = (cell \ "value").as[Int]
          val right = (cell \ "right").as[Int]
          val down = (cell \ "down").as[Int]
          cellType match {
            case 0 => _grid = _grid.set(row, col)
            case 1 => _grid = _grid.set(row, col, value)
            case 2 => _grid = _grid.set(row, col, right, down)
          }
          //_grid = _grid.set(row, col, value)

          //val given = (cell \ "given").as[Boolean]
          //val showCandidates = (cell \ "showCandidates").as[Boolean]
          //if (showCandidates) _grid = _grid.setShowCandidates(row, col)
        }
        gridOption = Some(_grid)
      }
      case None =>
    }
    gridOption
  }

  override def save(grid: FieldInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("grid.json"))
    pw.write(Json.prettyPrint(gridToJson(grid)))
    pw.close()
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
    //"given" -> cell.given,
    //"showCandidates" -> cell.showCandidates
    )
  }

}
