//package de.htwg.se.kakuro.model.fieldComponent


import java.io.File
import java.nio.file.FileSystem
import javax.security.auth.login.Configuration

import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.Field
import de.htwg.se.kakuro.model.fieldComponent.FieldInterface


def getListOfFiles(dir: String):List[File] = {
  val d = new File(dir)
  if (d.exists && d.isDirectory) {
    d.listFiles.filter(_.isFile).toList
  } else {
    List[File]()
  }
}

//val filetree = recursiveListFiles(new File("./../eclipse-workspace/de.htwg.se.kakuro/field1.csv"))
val source = scala.io.Source.fromFile("Z:/field1.csv")
val input = try source.mkString finally source.close()
val lines = input.split('\n')
val row: Int = lines.length
val col: Int = lines.head.count(_ == ',')+1
val tabular = Array.ofDim[String](row, col)

var field: FieldInterface = new Field(row)
//var rSums: List[Tuple2[Int, Int]] = List()
//field = fill(field)
//var field2: FieldInterface = field.set(2,2,10)

//println(field)

for (i <- 0 until row) {
  tabular(i) = lines(i).split(',')
  //println(lines(i))
  /*
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
  */
}



//field =
//var feld = stringToField(line)
