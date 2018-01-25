package de.htwg.se.kakuro.model.fieldComponent

trait FieldCreatorTemplate {

  def makeField(size: Int): FieldInterface

  def createNewField(size: Int): FieldInterface = {
    var field: FieldInterface = makeField(size)
    field = prepare(field)
    field = fill(field)
    field
  }

  def prepare(field: FieldInterface): FieldInterface = {
    field
  }

  def fill(field: FieldInterface): FieldInterface

  def postprocess(field: FieldInterface): FieldInterface = {
    field
  }
}
