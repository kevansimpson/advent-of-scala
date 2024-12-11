package org.base.advent._2020

import org.base.advent.AdventSpec
import org.base.advent._2020.Day01Spec.Environment
import org.mockito.scalatest.IdiomaticMockito

object Day01Spec extends IdiomaticMockito {
  trait Environment {
    val day = new Day01
    val example1: Seq[Int] = Seq(1721, 979, 366, 299, 675, 1456)
  }
}
class Day01Spec extends AdventSpec {
  "Day01" when {
    "practicing examples" should {
      "find the two entries that sum to 2020" in new Environment {
        day.findTwoEntriesWithTargetSum(example1, 2020) shouldEqual List(1721, 299)
      }
      "find the three entries that sum to 2020" in new Environment {
        day.findThreeEntriesWithTargetSum(example1, 2020) shouldEqual List(979, 366, 675)
      }
    }

    "solving" should {
      "answer part 1" in new Environment {
        day.solvePart1 shouldEqual 888331
      }
      "answer part 2" in new Environment {
        day.solvePart2 shouldEqual 130933530
      }
    }
  }
}
