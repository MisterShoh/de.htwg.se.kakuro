package de.htwg.se.kakuro.model.fieldComponent.FieldImpl

import de.htwg.se.kakuro.model.fieldComponent.{ CellInterface, FieldCreatorTemplate, FieldInterface }

import scala.io.Source

class FieldCreator extends FieldCreatorTemplate {
  override def makeField(size: Int): FieldInterface = {
    val field = generateSums(fill(size))
    field
  }

  def generateSums(field: Field): Field = {
    var _members: Set[Cell] = Set()
    var _field = field
    for {
      row <- (0 until _field.width).reverse
      col <- (0 until _field.height).reverse
    } {
      if (_field.cell(row, col).isWhite) {
        _members = _members.+(_field.cell(row, col))
      } else if (_field.cell(row, col).isBlack) {
        _field = _field.putSum(Sum(_field.cell(row, col).rightSum, _members))
        _members = Set()
      }
    }

    for {
      col <- (0 until _field.height).reverse
      row <- (0 until _field.width).reverse
    } {
      if (_field.cell(row, col).isWhite) {
        _members = _members.+(_field.cell(row, col))
      } else if (_field.cell(row, col).isBlack) {
        _field = _field.putSum(Sum(_field.cell(row, col).downSum, _members))
        _members = Set()
      }
    }
    _field
  }

  def fill(size: Int): Field = {

    val field = (new Field(size)).set(0, 0).set(0, 1, 0, 23).set(0, 2, 0, 30).set(0, 3).set(0, 4).set(0, 5, 0, 27)
      .set(0, 6, 0, 12).set(0, 7, 0, 16).set(1, 0, 16, 0).set(1, 1, 0).set(1, 2, 0).set(1, 3)
      .set(1, 4, 24, 17).set(1, 5, 0).set(1, 6, 0).set(1, 7, 0).set(2, 0, 17, 0).set(2, 1, 0)
      .set(2, 2, 0).set(2, 3, 29, 15).set(2, 4, 0).set(2, 5, 0).set(2, 6, 0).set(2, 7, 0)
      .set(3, 0, 35, 0).set(3, 1, 0).set(3, 2, 0).set(3, 3, 0).set(3, 4, 0).set(3, 5, 0)
      .set(3, 6, 0, 12).set(3, 7).set(4, 0).set(4, 1, 7, 0).set(4, 2, 0).set(4, 3, 0)
      .set(4, 4, 8, 7).set(4, 5, 0).set(4, 6, 0).set(4, 7, 0, 7).set(5, 0).set(5, 1, 0, 11)
      .set(5, 2, 16, 10).set(5, 3, 0).set(5, 4, 0).set(5, 5, 0).set(5, 6, 0).set(5, 7, 0)
      .set(6, 0, 21, 0).set(6, 1, 0).set(6, 2, 0).set(6, 3, 0).set(6, 4, 0).set(6, 5, 5, 0)
      .set(6, 6, 0).set(6, 7, 0).set(7, 0, 6, 0).set(7, 1, 0).set(7, 2, 0).set(7, 3, 0)
      .set(7, 4).set(7, 5, 3, 0).set(7, 6, 0).set(7, 7, 0)
    field
  }

}
