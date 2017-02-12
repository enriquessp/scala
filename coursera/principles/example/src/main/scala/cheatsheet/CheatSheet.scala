package cheatsheet

/**
  * Created by enrique on 2/12/17.
  */
object CheatSheet {

  ////////////////////
  // Evaluation rules
  ///////////////////
  def exampleEvalWhenCalled = 2 // evaluated when called
  val exampleEvalImmediately = 2 // evaluated immediately
  lazy val exampleLazy = 2 // evaluated once when needed

  def squareByValue(x: Double) = x * x // params evaluate before function
  def squareByName(x: => Double) = x * x // params evaluate after the function when needed
  def myFct(bindings: Int*) = { print(bindings) }

  /////////////////////////
  // Higher order functions
  /////////////////////////

  // sum() returns a function that takes two integers and returns an integer
  def sum(f: Int => Int): (Int, Int) => Int = {
    def sumf(a: Int, b: Int): Int = {
      f(a)+f(b)
    }
    sumf
  }

  // same as above. Its type is (Int => Int) => (Int, Int) => Int
  def sum2(f: Int => Int)(a:Int, b: Int): Int = f(a)+f(b)

  def cube(x: Int) = x*x*x

  ////////////
  // Currying
  ///////////
  def fUncurried(a: Int, b: Int): Int = a + b
  def fCurried(a: Int)(b: Int): Int = a + b

  ////////////
  // Classes
  ///////////
  class MyClass(x: Int, y: Int) { // Defines a new type MyClass with a constructor
    require(y > 0, "y must be positive") // precondition, triggering an IllegalArgumentException

    //def this (x: Int) = { ... }     // auxiliary constructor

    def nb1 = x // public method computed every time it is called

    def nb2 = y

    def square(value: Int): Int = value * value

    private def test(a: Int): Int = { 0 } // private method

    val nb3 = x + y

    override def toString = x + ", " + y

  }

  ///////////////////////
  // Classes hierarchies
  //////////////////////
  abstract class TopLevel {
    def method1(x: Int): Int // abstract method
    def method2(x: Int): Int = { x * x }
  }

  class Level1 extends TopLevel {
    def method1(x: Int): Int = x + x
    override def method2(x: Int): Int = x - x // Toplevel's method2 needs to be explicitly overriden
  }

  object MyObject extends TopLevel { // Defines a singleton object. No other can be created
    def method1(x: Int): Int = x / x
  }

  
}