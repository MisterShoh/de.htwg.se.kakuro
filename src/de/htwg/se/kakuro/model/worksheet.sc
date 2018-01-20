import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.{Field, FieldCreator, Cell}
import de.htwg.se.kakuro.model.fieldComponent.FieldInterface

//(1 to 9).toSet.&~(members.toSet.map(_.value)).filter(_ <= sumValue - current)
val b:List[Tuple2[String, Int]] = List(("a",1),("b",2),("c",3),("d",4))
val a:Range = (1 to 9)
val q:Set[Int] = b.map(_._2).toSet
val d:Set[Int] = a.toSet.diff(b.map(_._2).toSet)
val e = d.filter(_ <= 6)
//val c = a.toSet.&~(a.toSet.map(_.))
var rSums: List[Tuple2[Int, Int]] = List()
//rSums.::(0,0).::(1,1)
var x = Vector((0,0),(1,1),(2,2),(3,3),(4,4),(5,5))
var y = x.flatMap(t => List(t._1, t._2))

//val X = (0 to 45).map{ _:Int => if(_ == 0) "##" else if (_ < 10) " "+ _ else _ }


var white:Cell = new Cell(8)
var black:Cell = new Cell(4,5)

val whiStr:String = white.toStringRight// +"\n"+white.toStringDown
val blStr:String = white.toStringDown
def test(a:Boolean, b: Boolean, c: Int, d: Int): Unit ={}
var field: FieldInterface = new Field(8)
field = field.set(2,2,5,7)
field = field.set(2,3,6)

val creator:FieldCreator = new FieldCreator()

field = creator.fill(field)