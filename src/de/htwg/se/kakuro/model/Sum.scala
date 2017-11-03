package de.htwg.se.kakuro.model

case class Sum (info :BlackCell, ){
  def infoCell: BlackCell
  def Members: Set[WhiteCell]
  def value: Int
}