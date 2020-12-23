package org.base.advent._2020

import org.base.advent.Reader
import org.base.advent.util.Point

/**
  * <b>Part 1</b>
  * Your ferry made decent progress toward the island, but the storm came in faster than anyone expected. The ferry
  * needs to take evasive actions!
  *
  * Unfortunately, the ship's navigation computer seems to be malfunctioning; rather than giving a route directly to
  * safety, it produced extremely circuitous instructions. When the captain uses the PA system to ask if anyone can
  * help, you quickly volunteer.
  *
  * The navigation instructions (your puzzle input) consists of a sequence of single-character actions paired with
  * integer input values. After staring at them for a few minutes, you work out what they probably mean:
  * <ul>
  *     <li>Action N means to move north by the given value.</li>
  *     <li>Action S means to move south by the given value.</li>
  *     <li>Action E means to move east by the given value.</li>
  *     <li>Action W means to move west by the given value.</li>
  *     <li>Action L means to turn left the given number of degrees.</li>
  *     <li>Action R means to turn right the given number of degrees.</li>
  *     <li>Action F means to move forward by the given value in the direction the ship is currently facing.</li>
  * </ul>
  *
  * The ship starts by facing east. Only the L and R actions change the direction the ship is facing. (That is, if
  * the ship is facing east and the next instruction is N10, the ship would move north 10 units, but would still move
  * east if the following action were F.)
  *
  * For example:
  * <pre>
  * F10
  * N3
  * F7
  * R90
  * F11
  * </pre>
  *
  * These instructions would be handled as follows:
  * <ul>
  *     <li>F10 would move the ship 10 units east (because the ship starts by facing east) to east 10, north 0.</li>
  *     <li>N3 would move the ship 3 units north to east 10, north 3.</li>
  *     <li>F7 would move the ship another 7 units east (because the ship is still facing east) to east 17, north 3.</li>
  *     <li>R90 would cause the ship to turn right by 90 degrees and face south; it remains at east 17, north 3.</li>
  *     <li>F11 would move the ship 11 units south to east 17, south 8.</li>
  * </ul>
  *
  * At the end of these instructions, the ship's Manhattan distance (sum of the absolute values of its east/west
  * position and its north/south position) from its starting position is 17 + 8 = 25.
  *
  * Figure out where the navigation instructions lead.
  * <b>What is the Manhattan distance between that location and the ship's starting position?</b>
  *
  * <b>Part 2</b>
  * Before you can give the destination to the captain, you realize that the actual action meanings were printed on the
  * back of the instructions the whole time.
  *
  * Almost all of the actions indicate how to move a waypoint which is relative to the ship's position:
  * <ul>
  *     <li>Action N means to move the waypoint north by the given value.</li>
  *     <li>Action S means to move the waypoint south by the given value.</li>
  *     <li>Action E means to move the waypoint east by the given value.</li>
  *     <li>Action W means to move the waypoint west by the given value.</li>
  *     <li>Action L means to rotate the waypoint around the ship left (counter-clockwise) the given number of degrees.</li>
  *     <li>Action R means to rotate the waypoint around the ship right (clockwise) the given number of degrees.</li>
  *     <li>Action F means to move forward to the waypoint a number of times equal to the given value.</li>
  * </ul>
  *
  * The waypoint starts 10 units east and 1 unit north relative to the ship. The waypoint is relative to the ship;
  * that is, if the ship moves, the waypoint moves with it.
  *
  * For example, using the same instructions as above:
  * <ul>
  *     <li>F10 moves the ship to the waypoint 10 times (a total of 100 units east and 10 units north), leaving the
  *         ship at east 100, north 10. The waypoint stays 10 units east and 1 unit north of the ship.</li>
  *     <li>N3 moves the waypoint 3 units north to 10 units east and 4 units north of the ship.
  *         The ship remains at east 100, north 10.</li>
  *     <li>F7 moves the ship to the waypoint 7 times (a total of 70 units east and 28 units north), leaving the
  *         ship at east 170, north 38. The waypoint stays 10 units east and 4 units north of the ship.</li>
  *     <li>R90 rotates the waypoint around the ship clockwise 90 degrees, moving it to 4 units east and 10 units south
  *         of the ship. The ship remains at east 170, north 38.</li>
  *     <li>F11 moves the ship to the waypoint 11 times (a total of 44 units east and 110 units south), leaving the
  *         ship at east 214, south 72. The waypoint stays 4 units east and 10 units south of the ship.</li>
  * </ul>
  *
  * After these operations, the ship's Manhattan distance from its starting position is 214 + 72 = 286.
  *
  * Figure out where the navigation instructions actually lead. <b>What is the Manhattan distance between that location
  * and the ship's starting position?</b>
  *
  */
class Day12 extends Reader {
  private lazy val input = readLines("/2020/input12.txt")

  type Dir = (Point, Int)

  private val Instruction = "([NSEWLRF])(\\d+)".r

  def followDirections(directions: Seq[String]): Long = {
    val pt = directions.foldLeft((Point.ORIGIN, 1))((loc, instruction) => step(loc, instruction))
    println(pt)
    Point.manhattan(pt._1)
  }

  def followWaypoint(directions: Seq[String]): Long = {
    val pt = directions.foldLeft((Point.ORIGIN, Point(10, 1)))((shipWP, instruction) => {
      instruction match {
        case Instruction(vector, distance) =>
          val dist = distance.toInt
          vector match {
            case "N" => (shipWP._1, shipWP._2.up(dist))
            case "S" => (shipWP._1, shipWP._2.down(dist))
            case "E" => (shipWP._1, shipWP._2.right(dist))
            case "W" => (shipWP._1, shipWP._2.left(dist))
            case "L" =>
              dist match {
                case 90 => (shipWP._1, Point(-1 * shipWP._2.y, shipWP._2.x))
                case 180 => (shipWP._1, Point(-1 * shipWP._2.x, -1 * shipWP._2.y))
                case 270 => (shipWP._1, Point(shipWP._2.y, -1 * shipWP._2.x))
              }
            case "R" =>
              dist match {
                case 90 => (shipWP._1, Point(shipWP._2.y, -1 * shipWP._2.x))
                case 180 => (shipWP._1, Point(-1 * shipWP._2.x, -1 * shipWP._2.y))
                case 270 => (shipWP._1, Point(-1 * shipWP._2.y, shipWP._2.x))
              }
            case "F" => (shipWP._1.move(dist * shipWP._2.x, dist * shipWP._2.y), shipWP._2)
          }
      }
    })

    Point.manhattan(pt._1)
  }

  def step(loc: Dir, instruction: String): Dir = {
    instruction match {
      case Instruction(vector, distance) =>
        val dist = distance.toInt
        vector match {
          case "N" => (loc._1.up(dist), loc._2)
          case "S" => (loc._1.down(dist), loc._2)
          case "E" => (loc._1.right(dist), loc._2)
          case "W" => (loc._1.left(dist), loc._2)
          case "L" => (loc._1, (loc._2 - (dist / 90) + 4) % 4)
          case "R" => (loc._1, (loc._2 + (dist / 90) + 4) % 4)
          case "F" =>
            loc._2 match {
              case 0 => (loc._1.up(distance.toInt), loc._2)
              case 1 => (loc._1.right(distance.toInt), loc._2)
              case 2 => (loc._1.down(distance.toInt), loc._2)
              case 3 => (loc._1.left(distance.toInt), loc._2)
            }
        }
    }
  }

  def solvePart1: Long = followDirections(input)

  def solvePart2: Long = followWaypoint(input)
}
