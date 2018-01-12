package de.htwg.se.kakuro.controller

import de.htwg.se.kakuro.model.{ Field, Cell, FieldCreator }
import de.htwg.se.kakuro.util.Observable

class Controller(var field: Field) extends Observable {

  def initField(): Field = {
    var samplefield = new FieldCreator()
    field = samplefield.createEmptyGrid(8)
    field = samplefield.createSampleField(field)
    return field
  }
  def set(row: Int, col: Int, value: Int): Boolean = {
    if (field.cell(row, col).whiteCell) {
      field.cell(row, col).whiteCellValue = value
      return true
    } else {
      return false
    }
  }

}
