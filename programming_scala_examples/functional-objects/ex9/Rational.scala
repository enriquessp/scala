/*
 * Copyright (C) 2007-2010 Artima, Inc. All rights reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Example code from:
 *
 * Programming in Scala, Second Edition
 * by Martin Odersky, Lex Spoon, Bill Venners
 *
 * http://booksites.artima.com/programming_in_scala_2ed
 */

class Rational(n: Int, d: Int) {

  require(d != 0)

  private val g = gcd(n.abs, d.abs)
  val numer = n / g
  val denom = d / g

  def this(n: Int) = this(n, 1)

  def + (that: Rational): Rational =
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom
    )

  def * (that: Rational): Rational =
    new Rational(numer * that.numer, denom * that.denom)

  override def toString = numer +"/"+ denom

  private def gcd(a: Int, b: Int): Int = 
    if (b == 0) a else gcd(b, a % b)
}

object Main {
  def main(args: Array[String]) {
    val x = new Rational(1, 2)
    val y = new Rational(2, 3)
    println("x [" + x + "]")
    println("y [" + y + "]")
    println("x + y [" + (x + y) + "]")
    println("x.+(y) [" + (x.+(y)) + "]")
    println("x + x * y [" + (x + x * y) + "]")
    println("(x + x) * y [" + ((x + x) * y) + "]")
    println("x + (x * y) [" + (x + (x * y)) + "]")
  }
}
