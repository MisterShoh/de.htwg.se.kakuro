package de.htwg.se.kakuro.model.fieldComponent.FieldImpl

import com.google.inject.Inject
import de.htwg.se.kakuro.model.fieldComponent.{ CellInterface, FieldInterface, SumInterface }
import org.apache.logging.log4j.{ LogManager, Logger }

case class Field(grid: Matrix[Cell], sums: Vector[Sum]) extends FieldInterface {

  val logger: Logger = LogManager.getLogger(this.getClass.getName)
  //var grid = Array.ofDim[Cell](height, width)
  //var s : Set[Int] = Set()
  def this(grid: Matrix[Cell]) = this(grid, Vector.empty[Sum])
  def this(width: Int, height: Int) = this(new Matrix[Cell](width, height, new Cell()))
  def this(size: Int) = this(new Matrix[Cell](size, new Cell()))

  //val sums: Vector[Sum] = generateSums()

  def set(s: Sum): Field = {
    //val newSum = sums + s
    copy(grid = grid, sums = sums :+ s)
  }

  //var blackCells: Array[BlackCell]
  //def isShowCandidates(row: Int, col: Int): Boolean = false
  //def showCandidates(row: Int, col: Int): Set[Int] = available(row, col)
  //def unsetShowCandidates(row: Int, col: Int): FieldInterface = this

  //def rows(row: Int): Vector[Cell] = grid.rows(row)
  //def cols(col: Int): Vector[Cell] = grid.rows.map(row => row(col))
  /*
  def toggleShotAllCandidates(): Unit = {}



  def getWhiteBlock(row: Int, col: Int): Set[Cell] = {
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


  def generateSums(): Unit = {
    var _members: List[Cell] = List()

    for {
      row <- (0 until grid.width).reverse
      col <- (0 until grid.height).reverse
    } {
      if (cell(row, col).isWhite) {
        _members.::(cell(row, col))
      }else if (cell(row, col).isBlack) {
        putSum(new Sum(cell(row, col).rightSum, _members.toVector))
        _members = List()
      }
    }

    for {
      col <- (0 until grid.height).reverse
      row <- (0 until grid.width).reverse
    } {
      if (cell(row, col).isWhite) {
        _members.::(cell(row, col))
      }else if (cell(row, col).isBlack) {
        putSum( new Sum(cell(row, col).downSum, _members.toVector))
        _members = List()
      }
    }
  }

  */
  def generateSums(): FieldInterface = {
    var _sums: Vector[Sum] = Vector.empty[Sum]
    var members: Vector[Cell] = Vector.empty[Cell]
    var _field = this //field
    for {
      row <- (0 until width).reverse
      col <- (0 until height).reverse
    } {
      if (cell(row, col).isWhite) {
        members = members :+ cell(row, col)
        //println("------(" + row + ", " + col + "): added to - " + members.toString())
      } else if (cell(row, col).hasRight) {
        _field = _field.set(Sum(_field.cell(row, col).rightSum, members))
        //_sums = sums :+ Sum(cell(row, col).rightSum, members)
        //println("------(" + row + ", " + col + "): new Sum from - " + members.toString())
        members = Vector()
      }
    }

    for {
      col <- (0 until height).reverse
      row <- (0 until width).reverse
    } {
      if (cell(row, col).isWhite) {
        members = members :+ cell(row, col)
        //println("------(" + row + ", " + col + "): added to - " + members.toString())
      } else if (cell(row, col).hasDown) {
        _field = _field.set(Sum(_field.cell(row, col).downSum, members))
        //_sums = sums :+ Sum(cell(row, col).downSum, members)
        //println("------(" + row + ", " + col + "): added to - " + members.toString())
        members = Vector()
      }
    }
    _field
  }

  override def set(row: Int, col: Int, value: Int): Field = {
    logger.debug("field.set(row: Int, col: Int, value: Int)" + "value - " + value)
    copy(grid.replaceCell(row, col, new Cell(value)))
  }

  override def set(row: Int, col: Int): Field = {
    copy(grid.replaceCell(row, col, new Cell()))
  }
  override def set(row: Int, col: Int, rightVal: Int, downVal: Int): Field = {
    copy(grid.replaceCell(row, col, new Cell(rightVal, downVal)))
  }

  override def reset(row: Int, col: Int): Field =
    copy(grid.replaceCell(row, col, new Cell(0)))

  override def toString: String = {
    //logger.debug("field.toString()")
    var result: String = "\n 0  1  2  3  4  5  6  7\n"
    result += "+--+--+--+--+--+--+--+--+\n"
    //var flength = grid.length - 1
    for (j <- 0 until width) {
      result += stringRow(j)
    }
    result + "\n" + printDebug()
  }

  def printDebug(): String = {
    sums.toString() + sums.size + " valid: " + valid.toString + ", solved " + solved.toString + "\n"
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

  def cell(row: Int, col: Int): Cell = grid.cell(row, col)

  //override def createNewField(size: Int): FieldInterface = (new FieldCreator).createNewField(size)

  override def valid: Boolean = sums.forall(_.isValid)
  override def solved: Boolean = sums.forall(_.isSolved)

  //override def size: Int = grid.size
  override def width: Int = grid.width

  override def height: Int = grid.height

  //override def cells(): Matrix[CellInterface] = grid.asInstanceOf[Matrix[CellInterface]]

  //override def available(row: Int, col: Int): Set[Int] = Set(1, 2, 3, 4, 6, 5, 7, 8, 9)
  //override def setShowCandidates(row: Int, col: Int): FieldInterface = this //copy(grid.replaceCell(row, col, cell(row, col).copy(showCandidates = true)))
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
