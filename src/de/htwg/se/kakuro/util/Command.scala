package de.htwg.se.kakuro.util
import de.htwg.se.kakuro.model.fieldComponent.FieldInterface
trait Command {

  def doStep: FieldInterface
  def undoStep: FieldInterface
  def redoStep: FieldInterface

}
