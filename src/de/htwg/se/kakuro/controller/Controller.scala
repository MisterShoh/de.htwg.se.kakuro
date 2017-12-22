package de.htwg.se.kakuro.controller

import de.htwg.se.kakuro.model.{Cell, Field, FieldCreator}
import util.Observable

import scala.swing.Publisher

class Controller(var field: Field) extends Publisher {

  def initField(): Field = {
    var samplefield = new FieldCreator()
    field = samplefield.createEmptyGrid(8)
    field = samplefield.createSampleField(field)
    field
  }
  def set(row: Int, col: Int, value: Int): Boolean = {
    if (field.cell(row, col).whiteCell) {
      field.cell(row, col).whiteCellValue = value
      true
    } else false
  }

}
