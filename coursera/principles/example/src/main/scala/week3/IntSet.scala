package week3

/**
  * Created by enrique on 2/26/17.
  */
abstract class IntSet {
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
  def union(other: IntSet): IntSet
}

object Empty extends IntSet {
  override def incl(x: Int): IntSet = new NonEmpty(x, Empty, Empty)
  override def contains(x: Int): Boolean = false
  override def toString = "."
  override def union(other: IntSet): IntSet = other
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

  override def union(other: IntSet): IntSet =
    ((left union right) union other) incl element
}