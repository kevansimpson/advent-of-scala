package org.base.advent._2019

import org.base.advent.AdventSpec
import org.base.advent._2019.Day04Spec.Environment
import org.mockito.scalatest.IdiomaticMockito

object Day04Spec extends IdiomaticMockito {
  trait Environment {
    val day = new Day04
  }
}

class Day04Spec extends AdventSpec {
  "Day04" when {
    "practicing examples" should {
      "foo 1" in new Environment {
        day.isOrdered(111111) shouldEqual true
        day.hasDouble(111111) shouldEqual true
        day.simple(111111) shouldEqual true
        day.simple(223450) shouldEqual false
        day.simple(123789) shouldEqual false
      }
      "foo 2" in new Environment {
        day.complex(112233) shouldEqual true
        day.complex(123444) shouldEqual false
        day.complex(111122) shouldEqual true
      }
    }

    "solving" should {
      "answer part 1" in new Environment {
        day.solvePart1 shouldEqual 1178
      }
      "answer part 2" in new Environment {
        day.solvePart2 shouldEqual 763
      }
    }
  }
}
