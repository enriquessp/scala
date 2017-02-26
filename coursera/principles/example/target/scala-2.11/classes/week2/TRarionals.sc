class Rational(x: Int, y: Int) {
  require(y != 0, "denominator must be nonzero")

  def this(x: Int) = this(x, 1) // Another constructor with one argument

  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

  val numer = x / gcd(x, y)
  val denom = y / gcd(x, y)

  def +(that: Rational): Rational =
    new Rational(numer * that.denom + that.numer * denom, denom * that.denom)

  def unary_- :Rational = new Rational(-numer, denom)

  def - (that: Rational) = this + -that

  def < (that: Rational) = numer * that.denom < that.numer * denom

  def max(that: Rational): Rational = if (that.<(this)) this else that

  override def toString(): String = numer + "/" + denom
}

val x = new Rational(1, 3)
val y = new Rational(5, 7)
val z = new Rational(3, 2)
val xWithOneArgument = new Rational(1)

x.<(y)
x max y

x.numer
x.denom

y.+(x)
x.-(y).-(z)

def addRational(r: Rational, s: Rational): Rational =
  new Rational(r.numer * s.denom + s.numer * r.denom, r.denom * s.denom)

def mkString(r: Rational): String =
  r.numer + "/" + r.denom

mkString(addRational(new Rational(1, 2), new Rational(2, 3)))

//val strange = new Rational(1, 0)
//strange.+(strange)

abstract class IntSet {
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
}

class Empty extends IntSet {
  override def incl(x: Int): IntSet = new NonEmpty(x, new Empty, new Empty)
  override def contains(x: Int): Boolean = false
  override def toString = "."
}

class NonEmpty(element: Int, left: IntSet, right: IntSet) extends IntSet {
  override def incl(x: Int): IntSet =
    if (x < element) new NonEmpty(element, left incl x, right)
    else if (x > element) new NonEmpty(element, left, right incl x)
    else this

  override def contains(x: Int): Boolean =
    if (x < element) left contains x
    else if (x > element) right contains x
    else true

  override def toString = "{" + left + element + right + "}"
}

val t1 = new NonEmpty(3, new Empty, new Empty)
val t2 = t1 incl 5