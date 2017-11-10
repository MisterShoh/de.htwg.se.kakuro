package de.htwg.se.kakuro.model

class WhiteCell extends Cell(value: Int){
  
  def isSet: Boolean = value != 0
  def rowSum: Sum
  def colSum: Sum
  
}