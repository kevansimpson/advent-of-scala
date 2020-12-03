package org.base.advent._2020

import org.base.advent.AdventSpec
import org.base.advent._2020.Day02Spec.Environment
import org.mockito.scalatest.IdiomaticMockito

object Day02Spec extends IdiomaticMockito {
  trait Environment {
    val day = new Day02
    val example1 = Seq("1-3 a: abcde", "1-3 b: cdefg", "2-9 c: ccccccccc")
  }
}
class Day02Spec extends AdventSpec {
  "Day02" when {
    "practicing examples" should {
      "tally valid count passwords" in new Environment {
        day.countValidPasswords(example1, new CountPolicy) shouldEqual 2
      }
      "tally valid position passwords" in new Environment {
        day.countValidPasswords(example1, new PositionPolicy) shouldEqual 1
      }
    }

    "solving" should {
      "answer part 1" in new Environment {
        day.solvePart1 shouldEqual 625
      }
      "answer part 2" in new Environment {
        day.solvePart2 shouldEqual 391
      }
    }
  }
}
