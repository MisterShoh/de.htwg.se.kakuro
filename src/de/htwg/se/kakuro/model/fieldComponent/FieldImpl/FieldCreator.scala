package de.htwg.se.kakuro.model.fieldComponent.FieldImpl

import de.htwg.se.kakuro.model.fieldComponent.{ FieldCreatorTemplate, FieldInterface }

class FieldCreator extends FieldCreatorTemplate {

  //var field: new Field()

  override def makeField(size: Int): FieldInterface = {
    var field = new Field(size)
    field
  }

  def stringToField(input: String): FieldInterface = {
    var lines = input.split('\n')
    var row: Int = lines.size
    var col: Int = lines.head.count(_ == ',')
    var tabular = Array.ofDim[String](row, col)

    var field: FieldInterface = new Field(row, col)
    //var rSums: List[Tuple2[Int, Int]] = List()
    for (i <- tabular.indices) {
      tabular(i) = lines(i).split(',')
      for (j <- tabular(i).indices) {
        tabular(i)(j).toList.filter(c => c != ' ').map(c => c.toString.toInt) match {
          case Nil => field = field.set(i, j, 0)
          case v :: Nil => field = field.set(i, j, v)
          case s :: t :: Nil => {
            field = if (s == 0 && j == 0) field.set(i, j) else field.set(i, j, s, t)
            //if(s != 0) rSums = rSums.:: (i,j)
          }
        }
      }
    }
    field
  }

  override def fill(_field: FieldInterface): FieldInterface = {
    var grid: FieldInterface = new Field(_field.height, _field.width)

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
    grid = grid.set(1, 3, 0, 0)
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
    grid = grid.set(7, 7, 0)
    grid
  }
  /*
  def createEmptyGrid(size: Int): Field = {
    val field = Field(size, size)
    //notifyObservers
    return field
  }

  def createSampleField(field: Field): Field = {
    fillSampleField(field)
    //notifyObservers
    return field
  }


  def createSums(field: Field): List[Sum] ={
    val sums:List[Sum] // = new List[Sum]
    //for (cell <- field.field { }

    return sums
  }


  def fillSampleField(field: Field): Field = {
    field.initCell(0, 0, 0, 0)
    field.initCell(0, 1, 0, 23)
    field.initCell(0, 2, 0, 30)
    field.initCell(0, 3, 0, 0)
    field.initCell(0, 4, 0, 0)
    field.initCell(0, 5, 0, 27)
    field.initCell(0, 6, 0, 12)
    field.initCell(0, 7, 0, 16)
    field.initCell(1, 0, 16, 0)
    field.initCell(1, 1, 5)
    field.initCell(1, 2)
    field.initCell(1, 3, 0, 0)
    field.initCell(1, 4, 24, 17)
    field.initCell(1, 5)
    field.initCell(1, 6)
    field.initCell(1, 7)
    field.initCell(2, 0, 17, 0)
    field.initCell(2, 1)
    field.initCell(2, 2)
    field.initCell(2, 3, 29, 15)
    field.initCell(2, 4)
    field.initCell(2, 5)
    field.initCell(2, 6)
    field.initCell(2, 7)
    field.initCell(3, 0, 35, 0)
    field.initCell(3, 1)
    field.initCell(3, 2)
    field.initCell(3, 3)
    field.initCell(3, 4)
    field.initCell(3, 5)
    field.initCell(3, 6, 0, 12)
    field.initCell(3, 7, 0, 0)
    field.initCell(4, 0, 0, 0)
    field.initCell(4, 1, 7, 0)
    field.initCell(4, 2)
    field.initCell(4, 3)
    field.initCell(4, 4, 8, 7)
    field.initCell(4, 5)
    field.initCell(4, 6)
    field.initCell(4, 7, 0, 7)
    field.initCell(5, 0, 0, 0)
    field.initCell(5, 1, 0, 11)
    field.initCell(5, 2, 16, 10)
    field.initCell(5, 3)
    field.initCell(5, 4)
    field.initCell(5, 5)
    field.initCell(5, 6)
    field.initCell(5, 7)
    field.initCell(6, 0, 21, 0)
    field.initCell(6, 1)
    field.initCell(6, 2)
    field.initCell(6, 3)
    field.initCell(6, 4)
    field.initCell(6, 5, 5, 0)
    field.initCell(6, 6)
    field.initCell(6, 7)
    field.initCell(7, 0, 6, 0)
    field.initCell(7, 1)
    field.initCell(7, 2)
    field.initCell(7, 3)
    field.initCell(7, 4, 0, 0)
    field.initCell(7, 5, 3, 0)
    field.initCell(7, 6)
    field.initCell(7, 7)

    notifyObservers
    return field
  }
  */
}
