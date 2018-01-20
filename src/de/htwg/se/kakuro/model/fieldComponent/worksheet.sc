
import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.{Cell, Field, FieldCreator, Sum}
import de.htwg.se.kakuro.model.fieldComponent.FieldInterface

//val filetree = recursiveListFiles(new File("./../eclipse-workspace/de.htwg.se.kakuro/field1.csv"))
//val source = scala.io.Source.fromFile("Z:/field1.csv")
//val input = try source.mkString finally source.close()
//val lines = input.split('\n')
//val row: Int = lines.length
//val col: Int = lines.head.count(_ == ',')+1
//val tabular = Array.ofDim[String](row, col)

var field: FieldInterface = new Field(8)
val gen:FieldCreator = new FieldCreator()
//var rSums: List[Tuple2[Int, Int]] = List()
//field = gen.fill(field)

field = field.set(0,0,12,0)
field = field.set(0,1,0)
field = field.set(0,2,2)
field = field.set(0,3,12,11)

/*
for(row <- 0 until field.grid.height){
  for(col <- 0 until field.grid.width){
    if(field.cell(row,col).hasRight){
      //val x = field.rows(row).drop(col).find(_.isBlack)
      val x = field.rows(row).drop(col).map(_.isWhite)
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
*/

var vec = Vector.tabulate(4)(new Cell(_))

val sum = Sum(7 , vec)
val sumsum = sum.current
val isv = sum.isValid
val iss= sum.isSolved
//var field2: FieldInterface = field.set(2,2,10)

//println(field)
/*
for (i <- 0 until row) {
  tabular(i) = lines(i).split(',')
  //println(lines(i))

  for (j <- tabular(i).indices) {
    tabular(i)(j).toList.filter(c => c != ' ').map(c => c.toString.toInt) match {
      case Nil =>           field = field.set(i, j, 0)
      case v :: Nil =>      field = field.set(i, j, v)
      case s :: t :: Nil => {
        field = if (s == 0 && j == 0) field.set(i,j) else field.set(i, j, s, t)
        //if(s != 0) rSums = rSums.:: (i,j)
      }
    }
  }

}
*/


//field =
//var feld = stringToField(line)
