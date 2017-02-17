package week1

import scala.util.parsing.json.JSON

/**
  * Created by enrique on 16/02/17.
  */
abstract class JSONScala

case class JSeq(elements: List[JSONScala]) extends JSONScala
case class JObj(bindings: Map[String, JSONScala]) extends JSONScala
case class JStr(str: String) extends JSONScala
case class JNum(num: Double) extends JSONScala
case class JBool(bool: Boolean) extends JSONScala
case object JNull extends JSONScala
