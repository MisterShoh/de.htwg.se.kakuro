package de.htwg.se.kakuro.model.fieldComponent.FieldImpl

case class Matrix[T](rows: Vector[Vector[T]]) {

  def this(size: Int, filling: T) = this(Vector.tabulate(size, size) { (row, col) => filling })

  def this(width: Int, height: Int, filling: T) = this(Vector.tabulate(width, height) { (row, col) => filling })

  val size: Int = rows.size
  val height: Int = rows.size
  val width: Int = rows.head.size

  def cell(row: Int, col: Int): T = rows(row)(col)

  def fill(filling: T): Matrix[T] = copy(Vector.tabulate(height, width) { (row, col) => filling })

  def replaceCell(row: Int, col: Int, cell: T): Matrix[T] = copy(rows.updated(row, rows(row).updated(col, cell)))

  def toVector: Vector[T] = rows.flatten
}
