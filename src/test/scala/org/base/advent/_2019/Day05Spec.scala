package org.base.advent._2019

import org.base.advent.AdventSpec
import org.base.advent._2019.Day05Spec.Environment
import org.mockito.scalatest.IdiomaticMockito

object Day05Spec extends IdiomaticMockito {
  trait Environment {
    val day = new Day05
  }
}

class Day05Spec extends AdventSpec {
  "Day05" when {
    "solving" should {
      "answer part 1" in new Environment {
        day.solvePart1 shouldEqual 13285749
      }
      "answer part 2" in new Environment {
//        day.solvePart2 shouldEqual 130933530
      }
    }
  }
}
