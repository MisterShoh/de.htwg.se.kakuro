package de.htwg.se.kakuro.model

import de.htwg.se.kakuro.model.{ Cell, Field }
import de.htwg.se.kakuro.util.Observable

class FieldCreator() extends Observable {

  def createEmptyGrid(size: Int): Field = {
    val field = Field(size, size)
    notifyObservers
    return field
  }

  def createSampleField(field: Field): Field = {
    fillSampleField(field)
    notifyObservers
    return field
  }

  /*
  def createSums(field: Field): List[Sum] ={
    val sums:List[Sum] // = new List[Sum]
    //for (cell <- field.field { }

    return sums
  }
  */

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
}