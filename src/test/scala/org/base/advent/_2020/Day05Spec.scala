package org.base.advent._2020

import org.base.advent.AdventSpec
import org.base.advent._2020.Day05Spec.Environment
import org.mockito.scalatest.IdiomaticMockito

object Day05Spec extends IdiomaticMockito {
  trait Environment {
    val day = new Day05
    val example1: String = "FBFBBFFRLR"
    val example2: String = "BFFFBBFRRR"
    val example3: String = "FFFBBBFRRR"
    val example4: String = "BBFFBBFRLL"
  }
}

class Day05Spec extends AdventSpec {
  "Day05" when {
    "practicing examples" should {
      "find highest seat ID" in new Environment {
        day.toSeatID(example1) shouldEqual ((44, 5))
        day.toSeatID(example2) shouldEqual ((70, 7))
        day.toSeatID(example3) shouldEqual ((14, 7))
        day.toSeatID(example4) shouldEqual ((102, 4))
        day.highestSeatID(Seq(example1, example2, example3, example4)) shouldEqual 820
      }
    }

    "solving" should {
      "answer part 1" in new Environment {
        day.solvePart1 shouldEqual 828
      }
      "answer part 2" in new Environment {
        day.solvePart2 shouldEqual 565L
      }
    }
  }
}
