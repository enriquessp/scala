package cheatsheet

import CheatSheet._

/**
  * Created by enrique on 2/12/17.
  */
object Main extends App {

  println("EVALUATION RULES")
  println(squareByValue(3))
  println(squareByName(3))
  println(myFct(1,2,3))
  println()

  println("HIGHER ORDER FUNCTIONS")
  println(sum((x: Int) => x * x)(2, 2))
  println(sum( x       => x * x)(2, 2))

  println(sum2((x: Int) => x * x)(2, 2))
  println(sum2( x       => x * x)(2, 2))

  println(sum(x=> x * x * x)(1,10))
  println(sum(     cube    )(1,10))
  println()

  println("CURRYING")
  println(fUncurried(2, 2))
  println(fCurried(2)(2))
  println()

  println("CLASSES")
  println(new MyClass(1, 2))
  println()

  println("OPERATORS")
  def myClass = new MyClass(1,2)
  println(myClass square 3)
  println()

  println("CLASS HIERARCHIES")
  println(new Level1)
  println(MyObject.method1(3))

  println("TYPE PARAMETERS")
  println(new MyGenericClass[Int](3))
  println(new MyGenericClass(3)) // Type is being inferred
  def myFct1[T <: TopLevel](arg: T): T = { return arg }// T must derive from TopLevel or be TopLevel
  def myFct2[T >: Level1](arg: T): T = { return arg } // T must be a supertype of Level1
  def myFct3[T >: Level1 <: TopLevel ](arg: T): T = { return arg }

}