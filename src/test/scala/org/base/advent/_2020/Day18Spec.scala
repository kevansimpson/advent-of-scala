package org.base.advent._2020

import org.base.advent.AdventSpec
import org.base.advent._2020.Day18Spec.Environment
import org.mockito.scalatest.IdiomaticMockito

object Day18Spec extends IdiomaticMockito {
  trait Environment {
    val day = new Day18
    val example1a: String = "1 + 2 * 3 + 4 * 5 + 6"
    val example1b: String = "1 + (2 * 3) + (4 * (5 + 6))"
    val example2: String = "2 * 3 + (4 * 5)"
    val example3: String = "5 + (8 * 3 + 9 + 3 * 4 * 3)"
    val example4: String = "5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))"
    val example5: String = "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2"
  }
}

class Day18Spec extends AdventSpec {
  "Day18" when {
    "practicing examples" should {
      "learn new math" in new Environment {
        day.newMath(example1a) shouldEqual 71
        day.newMath(example1b) shouldEqual 51
        day.newMath(example2) shouldEqual 26
        day.newMath(example3) shouldEqual 437
        day.newMath(example4) shouldEqual 12240
        day.newMath(example5) shouldEqual 13632
      }
      "learn advanced math" in new Environment {
        day.advancedMath(example1a) shouldEqual 231
        day.advancedMath(example1b) shouldEqual 51
        day.advancedMath(example2) shouldEqual 46
        day.advancedMath(example3) shouldEqual 1445
        day.advancedMath(example4) shouldEqual 669060
        day.advancedMath(example5) shouldEqual 23340
      }
    }

    "solving" should {
      "answer part 1" in new Environment {
        day.solvePart1 shouldEqual 45283905029161L
      }
      "answer part 2" in new Environment {
        day.solvePart2 shouldEqual 216975281211165L
      }
    }
  }
}
