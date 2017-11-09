package de.htwg.se.kakuro.model

import scala.math.sqrt
import scala.util.Random

case class Sum(info :BlackCell, members: Set[WhiteCell]) {
  def this(size: Int) = this(new Vector[Cell](size, WhiteCell(0)))
  def infoCell: BlackCell
  def Members: Set[WhiteCell]
  def value: Int
}