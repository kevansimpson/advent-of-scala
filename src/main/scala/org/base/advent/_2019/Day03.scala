package org.base.advent._2019

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

import org.base.advent.Reader._
import org.base.advent.util._

/**
  * <b>Part 1</b>
  *  * The gravity assist was successful, and you're well on your way to the Venus refuelling station. During the rush back
  * on Earth, the fuel management system wasn't completely installed, so that's next on the priority list.
  *
  * Opening the front panel reveals a jumble of wires. Specifically, two wires are connected to a central port and extend
  * outward on a grid. You trace the path each wire takes as it leaves the central port, one wire per line of text (your
  * puzzle input).
  *
  * The wires twist and turn, but the two wires occasionally cross paths. To fix the circuit, you need to find the
  * intersection point closest to the central port. Because the wires are on a grid, use the Manhattan distance for this
  * measurement. While the wires do technically cross right at the central port where they both start, this point does
  * not count, nor does a wire count as crossing with itself.
  *
  * For example, if the first wire's path is R8,U5,L5,D3, then starting from the central port (o), it goes right 8, up 5,
  * left 5, and finally down 3:
  * <pre>
  * ...........
  * ...........
  * ...........
  * ....+----+.
  * ....|....|.
  * ....|....|.
  * ....|....|.
  * .........|.
  * .o-------+.
  * ...........
  * </pre>
  *
  *Then, if the second wire's path is U7,R6,D4,L4, it goes up 7, right 6, down 4, and left 4:
  * <pre>
  * ...........
  * .+-----+...
  * .|.....|...
  * .|..+--X-+.
  * .|..|..|.|.
  * .|.-X--+.|.
  * .|..|....|.
  * .|.......|.
  * .o-------+.
  * ...........
  * </pre>
  *
  * These wires cross at two locations (marked X), but the lower-left one is closer to the central port: its distance is 3 + 3 = 6.
  *
  * Here are a few more examples:
  * <pre>
  *   - R75,D30,R83,U83,L12,D49,R71,U7,L72
  *     U62,R66,U55,R34,D71,R55,D58,R83 = distance 159
  *   - R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51
  *     U98,R91,D20,R16,D67,R40,U7,R15,U6,R7 = distance 135
  * </pre>
  * What is the Manhattan distance from the central port to the closest intersection?
  *
  * <b>Part 2</b>
  * It turns out that this circuit is very timing-sensitive; you actually need to minimize the signal delay.
  *
  * To do this, calculate the number of steps each wire takes to reach each intersection; choose the intersection where
  * the sum of both wires' steps is lowest. If a wire visits a position on the grid multiple times, use the steps value
  * from the first time it visits that position when calculating the total value of a specific intersection.
  *
  * The number of steps a wire takes is the total number of grid squares the wire has entered to get to that location,
  * including the intersection being considered. Again consider the example from above:
  * <pre>
  * ...........
  * .+-----+...
  * .|.....|...
  * .|..+--X-+.
  * .|..|..|.|.
  * .|.-X--+.|.
  * .|..|....|.
  * .|.......|.
  * .o-------+.
  * ...........
  * </pre>
  *
  * In the above example, the intersection closest to the central port is reached after 8+5+5+2 = 20 steps by the first
  * wire and 7+6+4+3 = 20 steps by the second wire for a total of 20+20 = 40 steps.
  *
  * However, the top-right intersection is better: the first wire takes only 8+5+2 = 15 and the second wire takes only
  * 7+6+2 = 15, a total of 15+15 = 30 steps.
  *
  * Here are the best steps for the extra examples from above:
  * <pre>
  *   - R75,D30,R83,U83,L12,D49,R71,U7,L72
  *     U62,R66,U55,R34,D71,R55,D58,R83 = 610 steps
  *   - R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51
  *     U98,R91,D20,R16,D67,R40,U7,R15,U6,R7 = 410 steps
  * </pre>
  *
  * <b>What is the fewest combined steps the wires must take to reach an intersection?</b>
  */
class Day03 {
  private lazy val input = readCSVLines("/2019/input03.txt")

  type Tangled = Seq[Seq[String]]

  def solvePart1: Long = closestIntersection(input)

  def solvePart2: Long = fewestSteps(input)

  def closestIntersection(tangledWires: Tangled): Int =
    analyzePath(untangle(tangledWires), (pt: Point) => Point.manhattan(pt))

  def fewestSteps(tangledWires: Tangled): Int = {
    val wires = untangle(tangledWires)
    analyzePath(wires, (pt: Point) => countStepsAt(pt, wires))
  }

  def countStepsAt(point: Point, wirePaths: Seq[WirePath]): Int = wirePaths.map(_.steps.getOrElse(point, 0)).sum

  def analyzePath(wirePaths: Seq[WirePath], fxn: Point => Int): Int =
    wirePaths.head.path.toSet.intersect(wirePaths.last.path.toSet).map(fxn).min

  def untangle(tangledWires: Tangled): Seq[WirePath] = tangledWires.map(wirePath)

  def wirePath(wire: Seq[String]): WirePath = {
    val path: ListBuffer[Point] = ListBuffer[Point]()
    val steps: mutable.Map[Point, Int] = new mutable.HashMap[Point, Int]()
    var pt = Point.ORIGIN
    var count = 0
    for (w <- wire) {
      val dir = w.substring(0, 1)
      val dist = w.substring(1).toInt
      for (_ <- 1 to dist) {
        pt = pt.moveDir(dir)
        path += pt
        count += 1
        if (!steps.contains(pt)) steps.put(pt, count)
      }
    }
    WirePath(pt, path.toSeq, steps.toMap)
  }

  def wirePathFunctionalAndSlower(wire: Seq[String]): WirePath = {
    wire
      .foldLeft((0, WirePath()))((result: (Int, WirePath), w: String) => {
        val (count, wp) = result
        val dist = w.substring(1).toInt
        val path = (1 to dist).map(wp.point.moveDir(w.substring(0, 1), _))
        val map = path.zipWithIndex
          .filter(ptIx => !wp.steps.contains(ptIx._1))
          .map(ptIx => ptIx._1 -> (count + ptIx._2 + 1))
          .toMap
        (count + dist, WirePath(path.last, wp.path ++ path, wp.steps ++ map))
      })
      ._2
  }
}

case class WirePath(point: Point = Point.ORIGIN, path: Seq[Point] = Seq.empty, steps: Map[Point, Int] = Map.empty)
