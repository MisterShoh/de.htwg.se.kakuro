package de.htwg.se.kakuro.model.fieldComponent.FieldImpl

import de.htwg.se.kakuro.model.fieldComponent.{ CellInterface, FieldCreatorTemplate, FieldInterface }

import scala.io.Source

class FieldCreator extends FieldCreatorTemplate {
  override def makeField(size: Int): FieldInterface = {
    var field = new Field(size)
    field = fill(field)
    field = generateSums(field)
    field
  }

  /*
  def readCsv(filename:String):FieldInterface = {
  //Source.fromFile(filename).count()
  for (line <- Source.fromFile(filename).getLines){
    line.split(',')
  }
  new Field(0)
  }


  def stringld(input: String): FieldInterface = {
    var lines = input.split('\n')
    var row: Int = lines.size
    var col: Int = lines.head.count(_ == ',' + 1)
    var tabular = Array.ofDim[String](row, col)

    var field: FieldInterface = new Field(row, col)
    //var rSums: List[Tuple2[Int, Int]] = List()
    for (row <- tabular.indices) {
      tabular(row) = lines(row).split(',')
      for (col <- tabular(row).indices) {
        tabular(row)(col).split(" ").map(c => c.toInt).toList match {
          case Nil => field = field.set(row, col, 0)
          case whiteCellValue :: Nil => field = field.set(row, col, whiteCellValue)
          case rightVal :: downVal :: Nil => {
            field = if (rightVal == 0 && downVal == 0)
              field.set(row, col)
            else
              field.set(row, col, rightVal, downVal)
          }
        }
      }
    }
    //field.generateSums(field)
  }
  */
  def generateSums(field: Field): Field = {
    var members: Vector[Cell] = Vector.empty[Cell]
    var _field = field
    for {
      row <- (0 until _field.width).reverse
      col <- (0 until _field.height).reverse
    } {
      if (_field.cell(row, col).isWhite) {
        members = members :+ _field.cell(row, col)
        println("------(" + row + ", " + col + "): added to - " + members.toString())
      } else if (_field.cell(row, col).hasRight) {
        _field = _field.set(Sum(_field.cell(row, col).rightSum, members))

        println("------(" + row + ", " + col + "): new Sum from - " + members.toString())
        members = Vector()
      }
    }

    for {
      col <- (0 until _field.height).reverse
      row <- (0 until _field.width).reverse
    } {
      if (_field.cell(row, col).isWhite) {
        members = members :+ _field.cell(row, col)
        println("------(" + row + ", " + col + "): added to - " + members.toString())
      } else if (_field.cell(row, col).hasDown) {
        _field = _field.set(Sum(_field.cell(row, col).downSum, members))
        println("------(" + row + ", " + col + "): added to - " + members.toString())
        members = Vector()
      }
    }
    _field
  }
  def fill(_field: Field): Field = {
    var grid: Field = new Field(_field.height, _field.width)

    grid = grid.set(0, 0)
    grid = grid.set(0, 1, 0, 23)
    grid = grid.set(0, 2, 0, 30)
    grid = grid.set(0, 3)
    grid = grid.set(0, 4)
    grid = grid.set(0, 5, 0, 27)
    grid = grid.set(0, 6, 0, 12)
    grid = grid.set(0, 7, 0, 16)
    grid = grid.set(1, 0, 16, 0)
    grid = grid.set(1, 1, 0)
    grid = grid.set(1, 2, 0)
    grid = grid.set(1, 3)
    grid = grid.set(1, 4, 24, 17)
    grid = grid.set(1, 5, 0)
    grid = grid.set(1, 6, 0)
    grid = grid.set(1, 7, 0)
    grid = grid.set(2, 0, 17, 0)
    grid = grid.set(2, 1, 0)
    grid = grid.set(2, 2, 0)
    grid = grid.set(2, 3, 29, 15)
    grid = grid.set(2, 4, 0)
    grid = grid.set(2, 5, 0)
    grid = grid.set(2, 6, 0)
    grid = grid.set(2, 7, 0)
    grid = grid.set(3, 0, 35, 0)
    grid = grid.set(3, 1, 0)
    grid = grid.set(3, 2, 0)
    grid = grid.set(3, 3, 0)
    grid = grid.set(3, 4, 0)
    grid = grid.set(3, 5, 0)
    grid = grid.set(3, 6, 0, 12)
    grid = grid.set(3, 7)
    grid = grid.set(4, 0)
    grid = grid.set(4, 1, 7, 0)
    grid = grid.set(4, 2, 0)
    grid = grid.set(4, 3, 0)
    grid = grid.set(4, 4, 8, 7)
    grid = grid.set(4, 5, 0)
    grid = grid.set(4, 6, 0)
    grid = grid.set(4, 7, 0, 7)
    grid = grid.set(5, 0)
    grid = grid.set(5, 1, 0, 11)
    grid = grid.set(5, 2, 16, 10)
    grid = grid.set(5, 3, 0)
    grid = grid.set(5, 4, 0)
    grid = grid.set(5, 5, 0)
    grid = grid.set(5, 6, 0)
    grid = grid.set(5, 7, 0)
    grid = grid.set(6, 0, 21, 0)
    grid = grid.set(6, 1, 0)
    grid = grid.set(6, 2, 0)
    grid = grid.set(6, 3, 0)
    grid = grid.set(6, 4, 0)
    grid = grid.set(6, 5, 5, 0)
    grid = grid.set(6, 6, 0)
    grid = grid.set(6, 7, 0)
    grid = grid.set(7, 0, 6, 0)
    grid = grid.set(7, 1, 0)
    grid = grid.set(7, 2, 0)
    grid = grid.set(7, 3, 0)
    grid = grid.set(7, 4)
    grid = grid.set(7, 5, 3, 0)
    grid = grid.set(7, 6, 0)
    grid = grid.set(7, 7, 2)
    grid
  }

}
