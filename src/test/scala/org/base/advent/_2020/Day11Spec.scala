package org.base.advent._2020

import org.base.advent.AdventSpec
import org.base.advent._2020.Day11Spec.Environment
import org.base.advent.util.{CLaw, Grid, LineSplitter}
import org.mockito.scalatest.IdiomaticMockito

object Day11Spec extends IdiomaticMockito with LineSplitter {
  trait Environment {
    val day = new Day11
    val example1: Seq[String] =
      split("""L.LL.LL.LL
              |LLLLLLL.LL
              |L.L.L..L..
              |LLLL.LL.LL
              |L.LL.LL.LL
              |L.LLLLL.LL
              |..L.L.....
              |LLLLLLLLLL
              |L.LLLLLL.L
              |L.LLLLL.LL""".stripMargin)
    val example2: Seq[String] =
      split(""".......#.
              |...#.....
              |.#.......
              |.........
              |..#L....#
              |....#....
              |.........
              |#........
              |...#.....""".stripMargin)
  }
}

class Day11Spec extends AdventSpec {
  "Day11" when {
    "practicing examples" should {
      "find occupied seats" in new Environment {
        day.findSeats(Grid(example1)) shouldEqual 37
      }
      "find visible seats" in new Environment {
        day.around(CLaw(Grid(example2), 3, 4, "L")) shouldEqual Seq("#", "#", "#", "#", "#", "#", "#", "#")
        day.findSeats(Grid(example1), 0, day.lookUntil) shouldEqual 26
      }
    }

    "solving" should {
      "answer part 1" in new Environment {
        day.solvePart1 shouldEqual 2346
      }
      "answer part 2" in new Environment {
        day.solvePart2 shouldEqual 2111
      }
    }
  }
}
