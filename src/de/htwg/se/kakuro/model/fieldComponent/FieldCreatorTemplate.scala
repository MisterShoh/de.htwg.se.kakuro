package de.htwg.se.kakuro.model.fieldComponent

import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.Field
import de.htwg.se.kakuro.model.fieldComponent.FieldInterface

trait FieldCreatorTemplate {

  def makeField(size: Int): FieldInterface //abstract

  def createNewField(size: Int): FieldInterface = {
    var field: FieldInterface = makeField(size)
    field = prepare(field)
    field = fill(field)
    field
  }

  def prepare(field: FieldInterface): FieldInterface = {
    //default: NOOP
    field
  }

  def fill(field: FieldInterface): FieldInterface // abstract

  def postprocess(field: FieldInterface): FieldInterface = {
    //default: NOOP
    field
  }
}
