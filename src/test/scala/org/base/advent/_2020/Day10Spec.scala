package org.base.advent._2020

import org.base.advent.AdventSpec
import org.base.advent._2020.Day10Spec.Environment
import org.mockito.scalatest.IdiomaticMockito

object Day10Spec extends IdiomaticMockito {
  trait Environment {
    val day = new Day10
    val example1: Seq[Int] = Seq(16, 10, 15, 5, 1, 11, 7, 19, 6, 12, 4)
    val example2: Seq[Int] = Seq(28, 33, 18, 42, 31, 14, 46, 20, 48, 47, 24, 23, 49, 45, 19, 38, 39, 11, 1, 32, 25, 35,
      8, 17, 7, 9, 4, 2, 34, 10, 3)
  }
}

class Day10Spec extends AdventSpec {
  "Day10" when {
    "practicing examples" should {
      "find product of jolt differences" in new Environment {
        day.joltProduct(example1) shouldEqual 35
        day.joltProduct(example2) shouldEqual 220
      }
      "count distinct arrangements" in new Environment {
        day.distinctArrangements(example1) shouldEqual 8
        day.distinctArrangements(example2) shouldEqual 19208
      }
    }

    "solving" should {
      "answer part 1" in new Environment {
        day.solvePart1 shouldEqual 1917
      }
      "answer part 2" in new Environment {
        day.solvePart2 shouldEqual 113387824750592L
      }
    }
  }
}
