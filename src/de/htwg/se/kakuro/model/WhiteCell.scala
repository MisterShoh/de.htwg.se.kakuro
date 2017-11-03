package de.htwg.se.kakuro.model

case class WhiteCell extends Cell{
  
  def isSet: Boolean = value != 0
  def rowSum: Sum
  def colSum: Sum
  
}