package week1

/**
  * Created by enrique on 16/02/17.
  */
object Main extends App {
  val data = JObj(
    Map(
      "firstName" -> JStr("Enrique"),
      "lastName" -> JStr("Pereira"),
      "Address" -> JObj(
        Map(
          "streetAddress" -> JStr("Brigadeiro"),
          "state" -> JStr("SP"),
          "postalCode"-> JNum(123)
        )
      ),
      "phoneNumbers" -> JSeq(
        List(
          JObj(Map(
            "type" -> JStr("home"), "number" -> JStr("1111-2222"),
            "type" -> JStr("work"), "number" -> JStr("3333-4444")
          ))
        )
      )
    )
  )

  def show(json: JSONScala): String = json match {
    case JSeq(elems) => "[" + (elems map show mkString ",") + "]"
    case JObj(bindings) =>
      val assocs = bindings map {
        case (key, value) => "\"" +key + "\": " + show(value)
      }
      "{" + (assocs mkString ", ") + "}"
    case JStr(str) => "\"" + str + "\""
    case JNum(num) => num toString
    case JNull => "null"
  }

  println(show(data))
}
