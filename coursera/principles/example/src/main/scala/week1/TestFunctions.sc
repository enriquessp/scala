import scala.annotation.tailrec

object test {
  val f: String => String = {
    case "ping" => "pong"
  }
  f("ping")
  //  f("abc")

  val f2: PartialFunction[String, String] = {
    case "ping" => "pong"
  }
  f2.isDefinedAt("ping")
  f2.isDefinedAt("pong")

  def callByName(x: => Int, y: => Int): Int = x * x

  def callByValue(x: Int, y: Int): Int = x * x

  callByName(3 + 2, 2 + 2)
  callByValue(3 + 2, 2 + 2)


  def abs(x: Double): Double = if (x >= 0) x else -x

  abs(-10)

  def and(x: Boolean, y: => Boolean): Boolean = if (x) y else false

  and(true, false)
  and(false, true)
  and(false, false)
  and(true, true)

  def or(x: Boolean, y: => Boolean): Boolean = if (!x) y else true

  or(true, false)
  or(false, true)
  or(true, true)
  or(false, false)

  def sqrt(x: Double) = {

    def sqrtIter(guess: Double, x: Double): Double =
      if (isGoodEnough(guess, x)) guess
      else sqrtIter(improve(guess, x), x)

    def isGoodEnough(guess: Double, x: Double): Boolean =
      abs(guess * guess - x) / x < 0.001

    def improve(guess: Double, x: Double): Double =
      (guess + x / guess) / 2

    sqrtIter(1.0, x)
  }

  def sqrt2(x: Double) = {

    def sqrtIter(guess: Double): Double =
      if (isGoodEnough(guess)) guess
      else sqrtIter(improve(guess))

    def isGoodEnough(guess: Double): Boolean =
      abs(guess * guess - x) / x < 0.001

    def improve(guess: Double): Double =
      (guess + x / guess) / 2

    sqrtIter(1.0)
  }
  sqrt(2)
  sqrt(4)
  sqrt(1e-6)
  sqrt(1e60)

  sqrt2(2)
  sqrt2(4)
  sqrt2(1e-6)
  sqrt2(1e60)

  def factorial(n: Int): Int =
    if (n == 0) 1 else n * factorial(n - 1)

  factorial(4)

  def factorialTail(n: Int): Int = {
    @tailrec
    def loop(acc: Int, n: Int): Int =
      if (n == 0) acc
      else loop(acc * n, n - 1)

    loop(1, n)
  }

  factorialTail(4)
}