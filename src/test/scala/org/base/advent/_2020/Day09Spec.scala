package org.base.advent._2020

import org.base.advent.AdventSpec
import org.base.advent._2020.Day09Spec.Environment
import org.mockito.scalatest.IdiomaticMockito

object Day09Spec extends IdiomaticMockito {
  trait Environment {
    val day = new Day09
    val example1: Seq[Int] =
      Seq(35, 20, 15, 25, 47, 40, 62, 55, 65, 95, 102, 117, 150, 182, 127, 219, 299, 277, 309, 576)
  }
}

class Day09Spec extends AdventSpec {
  "Day09" when {
    "practicing examples" should {
      "sum any question" in new Environment {
        day.decodeXmas(example1, 5) shouldEqual 127
      }
      "sum every question" in new Environment {
        day.decryptXmas(example1, 5) shouldEqual 62
      }
    }

    "solving" should {
      "answer part 1" in new Environment {
        day.solvePart1 shouldEqual 138879426
      }
      "answer part 2" in new Environment {
        day.solvePart2 shouldEqual 23761694
      }
    }
  }
}
