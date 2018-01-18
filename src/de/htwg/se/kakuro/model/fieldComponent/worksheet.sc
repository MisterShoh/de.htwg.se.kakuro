//package de.htwg.se.kakuro.model.fieldComponent

import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.FieldCreator
import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.Field
import java.io.File

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
var field: Field = new Field(8)
val creator:FieldCreator = new FieldCreator()


def recursiveListFiles(f: File): Array[File] = {
  val these = f.listFiles
  //these ++ these.filter(_.isDirectory).flatMap(recursiveListFiles)
  these
}
//val filetree = recursiveListFiles(new File("./../eclipse-workspace/de.htwg.se.kakuro/field1.csv"))
val source = scala.io.Source.fromFile("./../eclipse-workspace/de.htwg.se.kakuro/field1.csv")
val lines = try source.mkString finally source.close()
field = creator.stringToField(lines)