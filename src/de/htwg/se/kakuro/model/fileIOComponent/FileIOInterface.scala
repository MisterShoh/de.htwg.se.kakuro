package de.htwg.se.kakuro.model.fileIOComponent

import de.htwg.se.kakuro.model.fieldComponent.FieldInterface

trait FileIOInterface {
  def load: Option[FieldInterface]
  def save(grid: FieldInterface)

}
