package de.htwg.se.kakuro.model

class Matrix(width: Int, height: Int) {

  val matrix = Array.ofDim[Int](height, width);
  def getWidth: Int = { width }
  def getHeight: Int = { height }

}
object Kakuro {
  def main(args: Array[String]): Unit = {
    val student = Player("Your Name")
    println("Hello, " + student.name)

  }
}