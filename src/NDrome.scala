import scala.io.Source
/**
 * Created with IntelliJ IDEA.
 * User: Balaji Athreya
 * Date: 10/26/13
 * Time: 12:19 AM
 * To change this template use File | Settings | File Templates.
 */
object NDrome {

  val location = "resources/NDrome/"

  def main(args : Array[String]) {
    for(line <- Source.fromFile(location+"SampleInput.txt").getLines()) {
      val arr : Array[String] = line.split("\\|")
      val str = arr(0)
      val n = arr(1).toInt
      val list : List[String] = str.grouped(n).toList
      val revString = reversedString(list)
      if(str.equalsIgnoreCase(revString))
        println(List(line,"1").mkString("|"))
      else
        println(List(line,"0").mkString("|"))
    }
  }

  def reversedString(list : List[String]) : String = list.reverse.mkString("")

}
