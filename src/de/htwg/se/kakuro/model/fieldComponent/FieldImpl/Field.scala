package de.htwg.se.kakuro.model.fieldComponent.FieldImpl

import de.htwg.se.kakuro.model.fieldComponent.{ CellInterface, FieldInterface, SumInterface }
import org.apache.logging.log4j.{ LogManager, Logger }

case class Field(grid: Matrix[Cell], sums: Set[SumInterface]) extends FieldInterface {

  val logger: Logger = LogManager.getLogger(this.getClass.getName)
  //var grid = Array.ofDim[Cell](height, width)
  //var s : Set[Int] = Set()
  def this(grid: Matrix[Cell]) = this(grid, Set.empty[SumInterface])
  def this(width: Int, height: Int) = this(new Matrix[Cell](width, height, new Cell()))
  def this(size: Int) = this(new Matrix[Cell](size, new Cell()))

  //var blackCells: Array[BlackCell]
  def isShowCandidates(row: Int, col: Int): Boolean = false
  def showCandidates(row: Int, col: Int): Set[Int] = available(row, col)
  def unsetShowCandidates(row: Int, col: Int): FieldInterface = this

  def rows(row: Int): Vector[Cell] = grid.rows(row)
  def cols(col: Int): Vector[Cell] = grid.rows.map(row => row(col))

  def toggleShotAllCandidates(): Unit = {}
  override def putSum(s: SumInterface): FieldInterface = {
    copy(grid, sums.+(s))
  }

  def getWhiteRow(row: Int, col: Int): Set[Cell] = {
    grid.rows(row).drop(col).dropRight(grid.rows(row).indexWhere(p => p.isBlack, row) - width).toSet
      .asInstanceOf[Set[Cell]]
  }

  def generateSums: FieldInterface = {
    for (row <- 0 until grid.height) {
      for (col <- 0 until grid.width) {
        if (cell(row, col).hasRight) {
          rows(row).drop(col).map(_.isWhite)
          /*
          var summy = col
          var sumlist = List[CellInterface]()
          while( col  < grid.width && cell(row,summy).isWhite){
            sumlist.+(cell(row, summy))
          }
          putSum()
          */
        }
      }
    }
    //grid.toVector.filter(_.hasRight).foreach
    //field.coords.foreach(field.cell(_1,_2))
    //)
    this
  }

  def generateSums2(): FieldInterface = {
    var _sums: List[Sum] = List()
    for (i <- 0 until width) {
      for (j <- 0 until height) {
        if (!grid.cell(j, i).isBlack) {
          var members = List[Cell]()
          for (k <- i until width) {
            // Achtung toStringRight ist wahrscheinlich nicht der richtige String!
            //if (grid.cell(k, j).isWhite) members.+(grid.cell(k, j))
          }
        }
      }
    }

    //var b : BlackCell
    //blackCells.foreach(c => if(c.downVal != 0) _sums.+new Sum())
    this
  }

  override def set(row: Int, col: Int, value: Int): FieldInterface = {
    logger.debug("field.set(row: Int, col: Int, value: Int)" + "value - " + value)
    copy(grid.replaceCell(row, col, new Cell(value)))
  }

  override def set(row: Int, col: Int): FieldInterface =
    copy(grid.replaceCell(row, col, new Cell()))

  override def set(row: Int, col: Int, rightVal: Int, downVal: Int): FieldInterface = {
    copy(grid.replaceCell(row, col, new Cell(rightVal, downVal)))
  }

  override def reset(row: Int, col: Int): FieldInterface =
    copy(grid.replaceCell(row, col, new Cell(0)))

  override def toString: String = {
    logger.debug("field.toString()")
    var result: String = "\n 0  1  2  3  4  5  6  7\n"
    result += "+--+--+--+--+--+--+--+--+\n"
    //var flength = grid.length - 1
    for (j <- 0 until width) {
      result += stringRow(j)
    }
    result
  }

  def stringRow(row: Int): String = {
    logger.debug("field.stringRow()")
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

  def cell(row: Int, col: Int): CellInterface = grid.cell(row, col)

  override def createNewGrid(size: Int): FieldInterface = (new FieldCreator).createNewField(size)

  override def valid: Boolean = sums.forall(_.isValid)

  override def solved: Boolean = sums.forall(_.isSolved)

  //override def size: Int = grid.size
  override def width: Int = grid.width
  override def height: Int = grid.height

  //override def cells(): Matrix[CellInterface] = grid.asInstanceOf[Matrix[CellInterface]]

  override def available(row: Int, col: Int): Set[Int] = Set(1, 2, 3, 4, 6, 5, 7, 8, 9) //TODO fix ...
  override def setShowCandidates(row: Int, col: Int): FieldInterface = this //copy(grid.replaceCell(row, col, cell(row, col).copy(showCandidates = true)))
  override def isBlack(row: Int, col: Int): Boolean = cell(row, col).isBlack

  override def isWhite(row: Int, col: Int): Boolean = cell(row, col).isWhite

  override def isNone(row: Int, col: Int): Boolean = !isWhite(row, col) && !isBlack(row, col)
  /*
  //override def cells(): Matrix[FullCellInterface] =

  def coords(cell: CellInterface): (Int, Int) = {
    //grid.toVector.map()
    (0, 0)
  }
  */
}
