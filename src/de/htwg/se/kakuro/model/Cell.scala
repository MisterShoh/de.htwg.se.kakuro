package de.htwg.se.kakuro.model

case class Cell(rowValue: Int, colValue: Int) {
  var whiteCell: Boolean = false;
  var rowVal: Int = rowValue;
  var colVal: Int = colValue;
  var valueRight: Int = 0;
  var valueDown: Int = 0;
  var whiteCellValue: Int = 0;

  def getPosition(): (Int, Int) = {
    return (rowVal, colVal)
  }

  def printCell(): Unit = {
    /*Case left or right site:
    if (colVal == 0 || colVal == 7) {
 		print("+--+\n")
 		print("    \n")
 		print("    \n")
 		print("+--+\n")

    }*/
    print(rowVal, colVal)
  }

  def toStringRight(): String = {

    if (whiteCell) {
      return ("  ")
    } else {
      if (valueRight < 10) {
        return valueRight.toString
      } else {
        if (valueRight > 0) {
          return (" " + valueRight)
        } else {
          return ("##")
        }
      }
    }
  }
  def toStringDown(): String = {
    if (whiteCell) {
      return ("**")
    } else {
      if (valueDown < 10) {
        return valueDown.toString
      } else {
        if (valueDown > 0) {
          return (" " + valueDown)
        } else {
          return ("##")
        }
      }
    }
  }
}

