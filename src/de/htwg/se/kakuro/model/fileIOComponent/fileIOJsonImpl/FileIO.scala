package de.htwg.se.kakuro.model.fileIOComponent.fileIOJsonImpl

import de.htwg.se.kakuro.model.fieldComponent.{ CellInterface, FieldInterface }
import de.htwg.se.kakuro.model.fileIOComponent.FileIOInterface
import org.apache.logging.log4j.{ LogManager, Logger }
import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.Field
import play.api.libs.json._

import scala.io.Source

class FileIO extends FileIOInterface {

  override def load: Option[FieldInterface] = {
    val logger: Logger = LogManager.getLogger(this.getClass.getName)
    var gridOption: Option[FieldInterface] = None
    val source: String = Source.fromFile("grid.json").getLines.mkString
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
          logger.debug("CELLTYPE:" + cellType)
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
    )
  }

}
