package org.base.advent._2020

import org.base.advent.AdventSpec
import org.base.advent._2020.Day12Spec.Environment
import org.base.advent.util.{LineSplitter, Point}
import org.mockito.scalatest.IdiomaticMockito

object Day12Spec extends IdiomaticMockito with LineSplitter {
  trait Environment {
    val day = new Day12
    val example1: Seq[String] =
      split("""F10
              |N3
              |F7
              |R90
              |F11""".stripMargin)
  }
}

class Day12Spec extends AdventSpec {
  "Day12" when {
    "practicing examples" should {
      "follow directions" in new Environment {
        Point.manhattan(Point(17, 8)) shouldEqual 25
        day.followDirections(example1) shouldEqual 25
      }
      "follow waypoint" in new Environment {
        day.followWaypoint(example1) shouldEqual 286
      }
    }

    "solving" should {
      "answer part 1" in new Environment {
        day.solvePart1 shouldEqual 1601
      }
      "answer part 2" in new Environment {
        day.solvePart2 shouldEqual 13340
      }
    }
  }
}
