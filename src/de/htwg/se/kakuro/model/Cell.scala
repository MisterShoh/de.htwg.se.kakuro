package de.htwg.se.kakuro.model

case class Cell(value: Int) {
  def pos: (Int, Int)
}