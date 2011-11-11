import scala.collection.mutable

object MapReduce extends App {


  val input = """|This is line number one
                 |This is line number two
                 |This is something different""".stripMargin

  val lines = input split("\n") flatMap {_.split(" ")}

  
  val result = mutable.Map[String,Int]()
  
  for (word <- lines) {
    result(word) = result.getOrElse(word, 0) + 1
  }
 
  result.keys.foreach(k => println(k + " -> " + result(k)))
  
  List(1, 2, 3) map (_ * 2)
}