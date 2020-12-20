package org.base.advent._2020

import org.base.advent.AdventSpec
import org.base.advent._2020.Day03Spec.Environment
import org.base.advent.util.Grid
import org.mockito.scalatest.IdiomaticMockito

object Day03Spec extends IdiomaticMockito {
  trait Environment {
    val day = new Day03
    val example1: String =
      """..##.......
        |#...#...#..
        |.#....#..#.
        |..#.#...#.#
        |.#...##..#.
        |..#.##.....
        |.#.#.#....#
        |.#........#
        |#.##...#...
        |#...##....#
        |.#..#...#.#""".stripMargin
    val forest: Grid = Grid(example1.split("\n").toSeq)
  }
}

class Day03Spec extends AdventSpec {
  "Day03" when {
    "practicing examples" should {
      "toboggan at right 3 and down 1" in new Environment {
        day.tobogganTrees(forest, (3, 1)) shouldEqual 7
      }
      "compare slopes" in new Environment {
        day.compareSlopes(forest) shouldEqual 336
      }
    }

    "solving" should {
      "answer part 1" in new Environment {
        day.solvePart1 shouldEqual 265
      }
      "answer part 2" in new Environment {
        day.solvePart2 shouldEqual 3154761400L
      }
    }
  }
}
