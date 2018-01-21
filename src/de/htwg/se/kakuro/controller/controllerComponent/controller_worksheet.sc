
var s = (0,0)

def isSelected(row: Int, col: Int): Boolean = s == (row,col)

def selectCell(row: Int, col: Int): Unit = {
  s = (row,col)
}

selectCell(2,1)
val t = isSelected(2,1)
val w = isSelected(1,2)