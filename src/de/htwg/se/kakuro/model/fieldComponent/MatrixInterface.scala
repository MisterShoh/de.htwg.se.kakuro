package de.htwg.se.kakuro.model.fieldComponent

import de.htwg.se.kakuro.model.fieldComponent.CellInterface
import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.Matrix

trait MatrixInterface[T] {
  def cell(row: Int, col: Int): T
  def fill(filling: T): MatrixInterface[T]
  def replaceCell(row: Int, col: Int, cell: T): Matrix[T]
}
