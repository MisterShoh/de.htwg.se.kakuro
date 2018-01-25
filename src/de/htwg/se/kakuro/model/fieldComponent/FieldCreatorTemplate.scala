package de.htwg.se.kakuro.model.fieldComponent

trait FieldCreatorTemplate {

  def makeField(size: Int): FieldInterface

  def createNewField(size: Int): FieldInterface = {
    val field: FieldInterface = makeField(size: Int)
    field
  }

}
