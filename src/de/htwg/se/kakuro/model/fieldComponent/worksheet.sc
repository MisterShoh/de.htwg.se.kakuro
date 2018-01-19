//package de.htwg.se.kakuro.model.fieldComponent

import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.FieldCreator
import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.Field
import java.io.File

import de.htwg.se.kakuro.model.fieldComponent.FieldInterface

//(1 to 9).toSet.&~(members.toSet.map(_.value)).filter(_ <= sumValue - current)
val b:List[Tuple2[String, Int]] = List(("a",1),("b",2),("c",3),("d",4))
val a:Range = (1 to 9)
val c:Set[Int] = b.map(_._2).toSet
val d:Set[Int] = a.toSet.diff(b.map(_._2).toSet)
val e = d.filter(_ <= 6)
//val c = a.toSet.&~(a.toSet.map(_.))

var rSums: List[Tuple2[Int, Int]] = List()
rSums.::(0,0).::(1,1)


var x = Vector((0,0),(1,1),(2,2),(3,3),(4,4),(5,5))
var y = x.flatMap(t => List(t._1, t._2))
//var field: Field = new Field(8)
//val creator:FieldCreator = new FieldCreator()



//val filetree = recursiveListFiles(new File("./../eclipse-workspace/de.htwg.se.kakuro/field1.csv"))
val source = scala.io.Source.fromFile("./../eclipse-workspace/de.htwg.se.kakuro/field1.csv")
val input = try source.mkString finally source.close()
val lines = input.split('\n')
val row: Int = lines.length
val col: Int = lines.head.count(_ == ',')
val tabular = Array.ofDim[String](row, col)

/*var field: FieldInterface = new Field(row)
//var rSums: List[Tuple2[Int, Int]] = List()
for (i <- tabular.indices) {
  tabular(i) = lines(i).split(',')
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
//field =
//var feld = stringToField(line)
