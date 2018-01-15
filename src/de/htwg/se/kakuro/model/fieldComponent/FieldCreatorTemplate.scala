package model.fieldComponent

import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.Field
import de.htwg.se.kakuro.model.fieldComponent.FieldInterface

trait FieldCreatorTemplate {
  var field: FieldInterface

  def makefield(size: Int): FieldInterface //abstract

  def createNewField(size: Int): FieldInterface = {
    var field: FieldInterface = makefield(size)
    field = prepare(field)
    field = fill(field)
    field
  }

  def prepare(field: FieldInterface): FieldInterface = {
    //default: NOOP
    field
  }

  def fill(field: FieldInterface): FieldInterface // abstract
}
