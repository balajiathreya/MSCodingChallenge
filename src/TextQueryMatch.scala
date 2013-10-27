import io.Source
import util.matching.Regex

/**
 * Created with IntelliJ IDEA.
 * User: Balaji Athreya
 * Date: 10/26/13
 * Time: 12:27 AM
 * To change this template use File | Settings | File Templates.
 */
object TextQueryMatch {

  val location = "resources/TextQueryMatch/"

  def main(args : Array[String]){
    var lineCount = 0
    var ele_num = 0
    var pattern = ""
    var isQuery = true
    for(line <- Source.fromFile(location+"SampleInput.txt").getLines()) {
      if(lineCount == 0){
        ele_num = line.toInt
        lineCount += 1
      }
      else if (isQuery){
        isQuery = false
        lineCount += 1
        // drop the first white space char that is created after split
        val p = line.toLowerCase().split("").drop(1).foldLeft("")((str:String,c:String) => str + "\\s*" + c)
        pattern = "\\b"+p.drop(1)
      }
      else{
        isQuery = true
        lineCount += 1
        textQueryMatch(line.toLowerCase(),pattern)
      }
    }
  }

  def textQueryMatch(str : String, regex : String){
    val options = regex.r findFirstIn (str)
    if (options.size > 0){
      println(List(str,true).mkString("|"))
    }
    else{
      println(List(str,false).mkString("|"))
    }
  }
}
