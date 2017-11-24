package de.htwg.se.kakuro.model

case class Cell(test: Int) {
  val value = test

  def getValue(): Int = {
    return value
  }

  def printCell(): Unit = {
    println(value)
  }
}

