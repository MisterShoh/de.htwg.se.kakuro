package de.htwg.se.kakuro.model.fieldComponent.FieldImpl

import de.htwg.se.kakuro.model.fieldComponent.{ BlackCellInterface, CellInterface, SumInterface, _ }
import org.apache.logging.log4j.LogManager

case class Field(grid: Matrix[SuperCell], sums: Set[SumInterface]) extends FieldInterface {

  val logger = LogManager.getLogger(this.getClass.getName)
  //var grid = Array.ofDim[Cell](height, width)
  //var s : Set[Int] = Set()
  def this(grid: Matrix[SuperCell]) = this(grid, Set.empty[SumInterface])
  def this(width: Int, height: Int) = this(new Matrix[SuperCell](width, height, new SuperCell()))
  def this(size: Int) = this(new Matrix[SuperCell](size, new SuperCell()))

  //var blackCells: Array[BlackCell]
  def isShowCandidates(row: Int, col: Int): Boolean = false
  def showCandidates(row: Int, col: Int): Set[Int] = available(row, col)
  def unsetShowCandidates(row: Int, col: Int): FieldInterface = this

  def toggleShotAllCandidates(): Unit = {}
  override def putSum(s: SumInterface): FieldInterface = {
    copy(grid, sums.+(s))
  }

  def getWhiteRow(row: Int, col: Int): Set[SuperCell] = {
    grid.rows(row).drop(col).dropRight(grid.rows(row).indexWhere(p => p.isBlack, row) - width).toSet
      .asInstanceOf[Set[SuperCell]]
  }

  def generateSums(): FieldInterface = {
    var _sums: List[Sum] = List()
    for (i <- 0 until width) {
      for (j <- 0 until height) {
        if (!grid.cell(j, i).isBlack) {
          var members = List[SuperCell]()
          for (k <- i until width) {
            // Achtung toStringRight ist wahrscheinlich nicht der richtige String!
            //if (grid.cell(k, j).isWhite) members.+(grid.cell(k, j).asInstanceOf[WhiteCell])
          }
        }
      }
    }

    //var b : BlackCell
    //blackCells.foreach(c => if(c.downVal != 0) _sums.+new Sum())
    this
  }

  override def set(row: Int, col: Int, value: Int): FieldInterface = {
    logger.debug("controller.set()" + "value - " + value)
    copy(grid.replaceCell(row, col, new SuperCell(value)))
  }

  override def set(row: Int, col: Int): FieldInterface =
    copy(grid.replaceCell(row, col, new SuperCell()))

  override def set(row: Int, col: Int, rightVal: Int, downVal: Int): FieldInterface = {
    // Achtung v, rightSum, downSum nur wegen den errors
    //val v = Vector[WhiteCellInterface]()
    //val rightSum = Sum(10, v, true)
    //val downSum = Sum(10, v, true)

    copy(grid.replaceCell(row, col, new SuperCell(rightVal, downVal)))
  }

  override def reset(row: Int, col: Int): FieldInterface =
    copy(grid.replaceCell(row, col, new SuperCell(0)))

  override def toString: String = {
    var result: String = "\n 0  1  2  3  4  5  6  7\n"
    result += "+--+--+--+--+--+--+--+--+\n"
    //var flength = grid.length - 1
    for (j <- 0 until width) {
      result += stringRow(j)
    }
    result
  }

  def stringRow(row: Int): String = {
    var result: String = "|"
    for (col <- 0 until width) {
      result += grid.cell(row, col).toStringRight
      //result += grid(row).toStringRight
      result += "|"
    }
    result += " " + row + "\n|"
    for (col <- 0 until width) {
      result += grid.cell(row, col).toStringDown
      //result += grid(row)(i).toStringDown
      result += "|"
    }
    result += "\n+"
    for (i <- 0 until width) {
      result += "--+"
    }
    result += "\n"
    result
  }

  /*
  def populateSums(): Vector[Sum] ={
    val x : Int = grid.foldLeft(_ + _.)

    sums = Array.ofDim[Sum](grid.foldLeft(_.asInstanceOf[BlackCell])

  }
  */
  //override def blackCells(): Vector[BlackCellInterface] = { grid.toVector.filter(_.isBlack).asInstanceOf[Vector[BlackCellInterface]] }

  def cell(row: Int, col: Int): FullCellInterface = grid.cell(row, col)

  override def createNewGrid(size: Int): FieldInterface = (new FieldCreator).createNewField(size)

  override def valid: Boolean = sums.forall(_.isValid)

  override def solved: Boolean = sums.forall(_.isSolved)

  //override def size: Int = grid.size
  override def width: Int = grid.width
  override def height: Int = grid.height

  //override def cells(): Matrix[CellInterface] = grid.asInstanceOf[Matrix[CellInterface]]

  override def available(row: Int, col: Int): Set[Int] = Set(1, 2, 3, 4, 6, 5, 7, 8, 9) //TODO fix ...
  override def setShowCandidates(row: Int, col: Int): FieldInterface = this //copy(grid.replaceCell(row, col, cell(row, col).copy(showCandidates = true)))
  override def isBlack(row: Int, col: Int) = cell(row, col).isBlack

  override def isWhite(row: Int, col: Int) = cell(row, col).isWhite

  override def isNone(row: Int, col: Int) = !isWhite(row, col) && !isBlack(row, col)
}
