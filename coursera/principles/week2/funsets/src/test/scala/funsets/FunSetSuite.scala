package funsets

import org.scalatest.FunSuite


import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  // test("string take") {
  //   val message = "hello, world"
  //   assert(message.take(5) == "hello")
  // }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  // test("adding ints") {
  //   assert(1 + 2 === 3)
  // }


  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s4 = singletonSet(4)
    val s5 = singletonSet(5)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements of each set") {
    new TestSets {
      val s = union(s1, s2)
      assert( contains(s, 1), "Union 1")
      assert( contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("intersect the set of all elements that are both in `s` and `t`") {
    new TestSets {
      val s1and2 = union(s1, s2)
      val s2and3 = union(s2, s3)
      val s = intersect(s1and2, s2and3)
      assert(!contains(s, 1), "intersect 1")
      assert( contains(s, 2), "intersect 2")
      assert(!contains(s, 3), "intersect 3")
    }
  }

  test("diff the set of all elements of `s` that are not in `t`") {
    new TestSets {
      val s1and2 = union(s1, s2)
      val s2and3 = union(s2, s3)
      val s = diff(s1and2, s2and3)
      assert( contains(s, 1), "diff 1")
      assert(!contains(s, 2), "diff 2")
      assert(!contains(s, 3), "diff 3")
    }
  }

  test("filter Returns the subset of `s` for which `p` holds") {
    new TestSets {
      val s1and2and3and5 = union(s1, union(s2, union(s3, union(s4, s5))))
      val oddFilter: Int => Boolean = x => x % 2 == 1
      val evenFilter: Int => Boolean = x => x % 2 == 0
      val sOdds = filter(s1and2and3and5, oddFilter)
      val sEvens = filter(s1and2and3and5, evenFilter)
      assert( contains(sOdds, 1), "filter odd 1")
      assert(!contains(sOdds, 2), "filter odd 2")
      assert( contains(sOdds, 3), "filter odd 3")
      assert(!contains(sOdds, 7), "filter odd 7")
      assert(!contains(sEvens, 1), "filter even 1")
      assert( contains(sEvens, 2), "filter even 2")
      assert(!contains(sEvens, 3), "filter even 3")
      assert( contains(sEvens, 4), "filter even 4")
      assert(!contains(sEvens, 10), "filter even 10")
    }
  }

  test("forAll Returns whether all bounded integers within `s` satisfy `p`") {
    new TestSets {
      val s1and2and3and5 = union(s1, union(s2, union(s3, union(s4, s5))))
      val onlyEvens = union(s2, s4)
      val evenFilter: Int => Boolean = x => x % 2 == 0
      val sEvens = filter(s1and2and3and5, evenFilter)
      assert(!forall(s1and2and3and5, evenFilter))
      assert(forall(onlyEvens, evenFilter))
    }
  }

  test("exists Returns whether there exists a bounded integer within `s` that satisfies `p`") {
    new TestSets {
      val s1and2and3and5 = union(s1, union(s2, union(s3, union(s4, s5))))
      val onlyEvens = union(s2, s4)
      val evenFilter: Int => Boolean = x => x % 2 == 0
      val sEvens = filter(s1and2and3and5, evenFilter)
      assert(!forall(s1and2and3and5, evenFilter))
      assert(forall(onlyEvens, evenFilter))
    }
  }

  test("map Returns a set transformed by applying `f` to each element of `s`") {
    new TestSets {
      val s1and2and3and5 = union(s1, union(s2, union(s3, union(s4, s5))))
      val square: Int => Int = x => x * x
      assert( map(s1and2and3and5, square)( 1))
      assert( map(s1and2and3and5, square)( 4))
      assert( map(s1and2and3and5, square)( 9))
      assert( map(s1and2and3and5, square)(25))
      assert(!map(s1and2and3and5, square)(36))
    }
  }

}