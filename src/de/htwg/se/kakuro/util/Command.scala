package de.htwg.se.kakuro.util
import de.htwg.se.kakuro.model.Field
trait Command {

  def doStep(field: Field): Unit
  def undoStep: Field
  def redoStep: Field
}
