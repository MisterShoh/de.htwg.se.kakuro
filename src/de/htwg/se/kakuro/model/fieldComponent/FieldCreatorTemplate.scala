package de.htwg.se.kakuro.model.fieldComponent

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
