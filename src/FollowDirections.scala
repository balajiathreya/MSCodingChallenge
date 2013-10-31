import scala.io.Source

/**
 * Created with IntelliJ IDEA.
 * User: Balaji Athreya
 * Date: 10/30/13
 * Time: 10:15 PM
 * To change this template use File | Settings | File Templates.
 */
object FollowDirections {
  val location = "resources/FollowDirections/"

  val EAST = "E"
  val WEST = "W"
  val NORTH = "N"
  val SOUTH = "S"

  def main(args : Array[String]) {
    val source = Source.fromFile(location+"SampleInput.txt")
    val lines = source.getLines()
    val pos = lines.foldLeft(new Tuple3[Int,Int,String](0,0,"N"))(
      (position : Tuple3[Int,Int,String],instruction) => {
        processLine(position,instruction)
      }
    )
    println(pos._1+","+pos._2)
  }

  def processLine(position : Tuple3[Int,Int,String], line : String) : Tuple3[Int,Int,String] = {
    val pattern = "move -*\\d+".r
    line.toLowerCase match {
      case pattern() => {
        val dis = line.substring(5).toInt
        position._3 match {
          case EAST => new Tuple3(position._1+dis,position._2,EAST)
          case WEST => new Tuple3(position._1-dis,position._2,WEST)
          case NORTH => new Tuple3(position._1,position._2+dis,NORTH)
          case SOUTH => new Tuple3(position._1,position._2-dis,SOUTH)
          case _ => position
        }
      }
      case "turn left" => {
        position._3 match {
          case EAST => new Tuple3(position._1,position._2,NORTH)
          case WEST => new Tuple3(position._1,position._2,SOUTH)
          case NORTH => new Tuple3(position._1,position._2,WEST)
          case SOUTH => new Tuple3(position._1,position._2,EAST)
          case _ => position
        }
      }
      case "turn right" => {
        position._3 match {
          case EAST => new Tuple3(position._1,position._2,SOUTH)
          case WEST => new Tuple3(position._1,position._2,NORTH)
          case NORTH => new Tuple3(position._1,position._2,EAST)
          case SOUTH => new Tuple3(position._1,position._2,WEST)
          case _ => position
        }
      }
      case _ => {
        println("unable to process : "+line)
        position
      }
    }
  }

}
