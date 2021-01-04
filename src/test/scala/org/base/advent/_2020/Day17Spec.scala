package org.base.advent._2020

import org.base.advent.AdventSpec
import org.base.advent._2020.Day17Spec.Environment
import org.base.advent.util.Util._
import org.mockito.scalatest.IdiomaticMockito

object Day17Spec extends IdiomaticMockito {
  trait Environment {
    val day = new Day17
    val example1: Seq[String] =
      split(""".#.
              |..#
              |###""".stripMargin)
  }
}

class Day17Spec extends AdventSpec {
  "Day17" when {
    "practicing examples" should {
      "cycle in 3d" in new Environment {
        day simulate example1 shouldEqual 112
      }
      "cycle in 4d" in new Environment {
        day simulate4 example1 shouldEqual 848
      }
    }

    "solving" should {
      "answer part 1" in new Environment {
        day.solvePart1 shouldEqual 310
      }
      "answer part 2" in new Environment {
        day.solvePart2 shouldEqual 2056
      }
    }
  }
}
