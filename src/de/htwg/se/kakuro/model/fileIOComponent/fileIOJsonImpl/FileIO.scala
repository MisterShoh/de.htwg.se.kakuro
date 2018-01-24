package de.htwg.se.kakuro.model.fileIOComponent.fileIOJsonImpl

import de.htwg.se.kakuro.model.fieldComponent.FieldInterface
import de.htwg.se.kakuro.model.fileIOComponent.FileIOInterface
import play.api.libs.json._

import scala.io.Source

class FileIO extends FileIOInterface {
  override def load: Option[FieldInterface] = {
    var gridOption: Option[FieldInterface] = None
    val source: String = Source.fromFile("grid.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    val size = (json \ "grid" \ "size").get.toString.toInt
    gridOption match {
      case Some(grid) => {
        var _grid = grid
        for (index <- 0 until size * size) {
          val row = (json \\ "row")(index).as[Int]
          val col = (json \\ "col")(index).as[Int]
          val cell = (json \\ "cell")(index)
          val value = (cell \ "value").as[Int]
          _grid = _grid.set(row, col, value)
          val given = (cell \ "given").as[Boolean]
          val showCandidates = (cell \ "showCandidates").as[Boolean]
          if (showCandidates) _grid = _grid.setShowCandidates(row, col)
        }
        gridOption = Some(_grid)
      }
      case None =>
    }
    gridOption
  }

  override def save(grid: FieldInterface): Unit = ???
}
