Sources:
http://www.newthinktank.com/2015/08/learn-scala-one-video/

//Import
import scala.io.StdIn.{readLine,readInt}
import scala.math._
import scala.collection.mutable.ArrayBuffer
import java.io.PrintWriter
import scala.io.Source
// Konstante 
val num13 = 1.999999999999999
// Variable
var randInt = 100000
// Es gibt ++x , x-- nicht in Scala
x += 1
x -= 1

//Bedingungen ==, !=, >, <, >=, <=
//Logische Operatoren &&, ||, !

if ((age >= 5) && (age <= 6)) {
  println("Go to Kindergarten")
} else if ((age > 6) && (age <= 7)) {
  println("Go to Grade 1")
} else {
  println("Go to Grade " + (age - 5))
}

// main  
object ScalaTut {
  def main(args: Array[String]) {
 	// Loops und Bedingungen
    var i = 0
    while (i <= 10) {
      println(i)
      i += 1
    }
    do {
      println(i)
      i += 1
    } while(i <= 20)
    for (i <- 1 to 10){
      println(i)
    }
    //NEU: until 
    val randLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    for (i <- 0 until randLetters.length){
      println(randLetters(i))
    }
   //Über liste iterieren 
   val aList = List(1,2,3,4,5)
    for(i <- aList){
      println("List Item " + i)
    }
    //NEU: yield 
    var evenList = for { i <- 1 to 20
      // Hier können Bedingungen eingefügt werden
      // getrennt durch ";"
      if (i % 2) == 0
    } yield i
    // Es gibt kein break in Scala also einfach mit einem if-statement.
    def printPrimes() {
      val primeList = List(1,2,3,5,7,11)
      for (i <- primeList){
           // Break
          if(i == 11){
          return
        }
        // Continue
        if (i != 1){
          println(i)
        }
      }
    }

// ---------- INPUT / OUTPUT ----------
 
var numberGuess = 0
do{
  print("Guess a number : ")
  //readInt, readDouble, readByte, readShort, readLong
  numberGuess = readLine.toInt
  } while(numberGuess != 15)
  printf("You guessed the secret number %d\n", 15)
  println(s"Hello $name")
 
  println(f"I am ${age + 1} and weigh $weight%.2f")
 
  // printf Style 
  // %c : Characters
  // %d : Integers
  // %f : Floating Point Numbers
  // %s : Strings
  // Special Characters : \n, \b, \\, \a
 
// ---------- STRINGS ----------
var randSent = "I saw a dragon fly by"
 
// Get the 3rd index value
println("3rd Index : " + randSent(3))
 
// Get String length
println("String length " + randSent.length())
 
// Concatenate strings
println(randSent.concat(" and explode"))
 
// Compare strings for equality
println("Are strings equal " + "I saw a dragon".equals(randSent))
 
// Get index of a match
println("dragon starts at index ", randSent.indexOf("dragon"))
 
// Convert a string into an array
val randSentArray = randSent.toArray
 
for (v <- randSentArray)
  println(v)
 
// ---------- FUNCTIONS ----------
// def funcName (param1:dataType, param2:dataType) : returnType = {
//    function body
//    return valueToReturn
// }
 
// You can give parameters default values
def getSum( num1:Int = 1, num2:Int = 1) : Int = {
  return num1 + num2
}
 
println("5 + 4 = " + getSum(5,4))
 
// you can use named arguments
println("5 + 4 = " + getSum(num2=5, num1=4))
 
// A function that returns nothing (Procedure)
def sayHi() : Unit = {
  println("Hi how are you doing")
}
 
sayHi
 
// Receive variable number of arguments
def getSum2(args: Int*) : Int = {
  var sum : Int = 0
  for(num <- args){
    sum += num
  }
  sum
}
 
println("getSum2: " + getSum2(1,2,3,4,5))
 
// Recursion example calculating factorials
def factorial(num : BigInt) : BigInt = {
  if (num <= 1)
    1
  else
    num * factorial(num - 1)
}
 
// 1st: num = 4 * factorial(3) = 4 * 6 = 24
// 2nd: num = 3 * factorial(2) = 3 * 2 = 6
// 3rd: num = 2 * factorial(1) = 2 * 1 = 2
 
println("Factorial of 4 = " + factorial(4))
 

 
// ---------- MAPS ----------
// Maps are collections of key value pairs
 
// Create a Map that can't be changed (Immutable)
val employees = Map("Manager" -> "Bob Smith", "Secretary" -> "Sue Brown")
 
// Get value using the key after checking that it exists
if(employees.contains("Manager"))
  printf("Manager : %s\n", employees("Manager"))
 
// Create a Mutable map
val customers = collection.mutable.Map(100 -> "Paul Smith",
  101 -> "Sally Smith")
 
printf("Cust 1 : %s\n", customers(100))
 
// Change a value using the key
customers(100) = "Tom Marks"
 
// Add an item
customers(102) = "Megan Swift"
 
// Output a Map
for((k,v) <- customers)
  printf("%d : %s\n", k, v)
 
// ---------- TUPLES ----------
// Tuples can hold values of many types, but they are immutable
 
var tupleMarge = (103, "Marge Simpson", 10.25)
 
printf("%s owes us $%.2f\n", tupleMarge._2, tupleMarge._3)
 
// Iterate through a tuple
tupleMarge.productIterator.foreach{ i => println(i)}
 
// Convert Tuple to String
println(tupleMarge.toString())
 
// ---------- CLASSES ----------
val rover = new Animal
rover.setName("Rover")
rover.setSound("Woof")
printf("%s says %s\n", rover.getName, rover.getSound)
 
val whiskers = new Animal("Whiskers", "Meow")
println(s"${whiskers.getName} with id ${whiskers.id} says ${whiskers.getSound}")
 
println(whiskers.toString)
 
val spike = new Dog("Spike", "Woof", "Grrrr")
 
spike.setName("Spike")
println(spike.toString)
 
val fang = new Wolf("Fang")
fang.moveSpeed = 36.0
println(fang.move)
 
// ---------- TRAITS ----------
val superman = new Superhero("Superman")
println(superman.fly)
println(superman.hitByBullet)
println(superman.ricochet(2500))
 
// ---------- HIGHER ORDER FUNCTIONS ----------
// Functions can be passed like any other variable
// You need the _ after the function to state you meant the function
val log10Func = log10 _
 
println(log10Func(1000))
 
// You can apply a function to all items of a list with map
List(1000.0,10000.0).map(log10Func).foreach(println)
 
// You can use an anonymous function with map as well
// Receives an Int x and multiplies every x by 50
List(1,2,3,4,5).map((x : Int) => x * 50).foreach(println)
 
// Filter will pass only those values that meet a condition
List(1,2,3,4,5,6).filter(_ % 2 == 0).foreach(println)
 
// Pass different functions to a function
def times3(num : Int) = num * 3
def times4(num : Int) = num * 4
 
// Define the function parameter type and return type
def multIt(func : (Int) => Double, num : Int ) = {
  func(num)
}
 
printf("3 * 100 = %.1f)\n", multIt(times3, 100))
 
// A closure is a function that depends on a variable declared outside
// of the function
val divisorVal = 5
val divisor5 = (num : Double) => num / divisorVal
println("5 / 5 = " + divisor5(5.0))
 
// ---------- FILE I/O ----------
// Use import java.io.PrintWriter to write to a file
val writer = new PrintWriter("test.txt")
writer.write("Just some random text\nSome more random text")
writer.close()
 
// Use import scala.io.Source to read from files
val textFromFile = Source.fromFile("test.txt", "UTF-8")
 
// Iterate through each line in the file and print
val lineIterator = textFromFile.getLines
for(line <- lineIterator){
  println(line)
}
textFromFile.close()
 
// ---------- EXCEPTION HANDLING ----------
 
def divideNums(num1 : Int, num2 : Int) = try
{
  (num1 / num2)
} catch {
    case ex: java.lang.ArithmeticException => "Can't divide by zero"
} finally {
  // Clean up after exception here
}
 
println("3 / 0 = " + divideNums(3,0))
 
} // ---------- END OF MAIN ----------
 
// ---------- CLASSES ----------
// Classes are used as blueprints to create objects
// Objects define the attributes (fields) and capabilities (methods) of an
// object
 
class Animal(var name : String, var sound : String) {
  this.setName(name)
 
  // Any code that follows the class name is executed each time an
  // object is created as part of the Primary Constructor
 
  // This function is defined in the Animal companion object below
  val id = Animal.newIdNum
 
  // You must initialize all fields
  // protected means the field can only be accessed directly by methods
  // defined in the class or by subclasses
  // private fields can't be accessed by subclasses of Animal
  // public fields can be accessed directly by anything
 
  // protected var name = "No Name"
  // protected var sound = "No Sound"
 
  // Getters and setters are used to protect data
  def getName() : String = name
  def getSound() : String = sound
  def setName(name : String){
    // Check if the String contains numbers and if so don't allow
    if(!(name.matches(".*\\d+.*")))
 
      // this allows us to refer to any object without knowing its name
      this.name = name
    else
      this.name = "No Name"
  }
  def setSound(sound : String) {
    this.sound = sound
  }
 
  // Subclasses can't call this constructor unlike with Java
  def this (name : String){
 
    // This calls the primary constructor defined on the class line
    this("No Name", "No Sound")
    this.setName(name)
  }
 
  def this (){
    // This calls the primary constructor defined on the class line
    this("No Name", "No Sound")
  }
 
  // You can override any other method
  override def toString() : String = {
 
    // How to format Strings
    return "%s with the id %d says %s".format(this.name, this.id, this.sound)
  }
 
}
 
// The companion object for a class is where you'd define static class
// variables and functions in Java
object Animal {
  private var idNumber = 0
  private def newIdNum = { idNumber += 1; idNumber }
}
 
// ---------- INHERITANCE ----------
// A class that inherits from another gains all its fields and methods
// A class declared final can't be extended
class Dog (name : String, sound : String, growl : String) extends
Animal(name, sound){
 
  def this (name : String, sound : String){
    this("No Name", sound, "No Growl")
    this.setName(name)
  }
 
  def this (name : String){
    this("No Name", "No Sound", "No Growl")
    this.setName(name)
  }
 
  def this (){
    this("No Name", "No Sound", "No Growl")
  }
 
  // You can override any other method
  override def toString() : String = {
    return "%s with the id %d says %s or %s".format(
      this.name, this.id, this.sound, this.growl)
  }
}
 
// ---------- ABSTRACT CLASS ----------
// An abstract class can't be instantiated. It is made up of both abstract
// and non-abstract methods and fields
 
abstract class Mammal(val name : String){
  // An abstract field has no initial value
  var moveSpeed : Double
 
  // An abstract method has no body
  def move : String
 
}
 
class Wolf(name : String) extends Mammal(name){
  // You don't use override when defining abstract fields
  var moveSpeed = 35.0;
 
  def move = "The wolf %s runs %.2f mph".format(this.name, this.moveSpeed)
 
}
 
// ---------- TRAITS ----------
// A trait is a like a Java interface in that a class can extend more then 1
// Unlike Java interfaces traits can provide concrete methods and fields
trait Flyable {
  def fly : String
 
}
 
trait Bulletproof {
  def hitByBullet : String
 
  // You can define concrete methods in traits
  def ricochet(startSpeed : Double) : String = {
    "The bullet ricochets at a speed of %.1f ft/sec".format(startSpeed * .75)
  }
}
 
// The first trait starts with extends and then with for each other
class Superhero(val name : String) extends Flyable with Bulletproof{
  def fly = "%s flys through the air".format(this.name)
 
  def hitByBullet = "The bullet bounces off of %s".format(this.name)
}
 
} // ---------- End of object ScalaTut ----------
