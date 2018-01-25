package de.htwg.se.kakuro.model.fileIOComponent.fileIOXmlImpl

import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.Field
import de.htwg.se.kakuro.model.fieldComponent.FieldInterface
import de.htwg.se.kakuro.model.fileIOComponent.FileIOInterface

import scala.xml.{ Elem, NodeSeq, PrettyPrinter }

class FileIO extends FileIOInterface {
  override def load: Option[FieldInterface] = {
    var gridOption: Option[FieldInterface] = None
    val file = scala.xml.XML.loadFile("grid.xml")
    val widthAttr = file \\ "grid" \ "@width"
    val heightAttr = file \\ "grid" \ "@height"

    val width = widthAttr.text.toInt
    val height = heightAttr.text.toInt

    gridOption = Some(new Field(width, height))

    val cellNodes = file \\ "cell"
    gridOption match {
      case Some(grid) => {
        var _grid = grid
        for (cell <- cellNodes) {
          val row: Int = (cell \ "@row").text.toInt
          val col: Int = (cell \ "@col").text.toInt
          val value: Int = (cell \ "@value").text.toInt
          val rightSum: Int = (cell \ "@rightSum").text.toInt
          val downSum: Int = (cell \ "@downSum").text.toInt
          val iswhite: Boolean = (cell \ "@white").text.toBoolean
          val isblack: Boolean = (cell \ "@black").text.toBoolean
          if (iswhite) { _grid = _grid.set(row, col, value) }
          else if (isblack) { _grid = _grid.set(row, col, rightSum, downSum) }
          else { _grid = _grid.set(row, col) }
        }
        gridOption = Some(_grid)
      }
      case None =>
    }
    gridOption
  }

  override def save(grid: FieldInterface): Unit = saveString(grid)

  def saveXML(grid: FieldInterface): Unit = {
    scala.xml.XML.save("grid.xml", gridToXml(grid))
  }

  def saveString(grid: FieldInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("grid.xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(gridToXml(grid))
    pw.write(xml)
    pw.close()
  }
  def gridToXml(grid: FieldInterface): Elem = {
    <grid width={ grid.width.toString } height={ grid.height.toString }>
      {
        for {
          row <- 0 until grid.width
          col <- 0 until grid.height
        } yield cellToXml(grid, row, col, grid.cell(row, col).isBlack, grid.cell(row, col).isWhite)
      }
    </grid>
  }

  def cellToXml(grid: FieldInterface, row: Int, col: Int, w: Boolean, b: Boolean): Elem = {
    <cell row={ row.toString } col={ col.toString } value={ grid.cell(row, col).value.toString } rightSum={ grid.cell(row, col).rightSum.toString } downSum={ grid.cell(row, col).downSum.toString } white={ w.toString } black={ b.toString }/>
  }

}
