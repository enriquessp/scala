package week3

/**
  * Created by enrique on 2/26/17.
  */
object Hello {

  def main(args: Array[String]) =
    println("Hello world")
    val t1 = new NonEmpty(3, Empty, Empty)
    val t2 = t1 incl 5
    println(t2)

}