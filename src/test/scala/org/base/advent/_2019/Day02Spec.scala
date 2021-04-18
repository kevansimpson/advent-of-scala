package org.base.advent._2019

import org.base.advent.AdventSpec
import org.base.advent._2019.Day02Spec.Environment
import org.base.advent._2019.intcode._
import org.mockito.scalatest.IdiomaticMockito

object Day02Spec extends IdiomaticMockito {
  trait Environment {
    val day = new Day02
    val example1: Seq[Long] = Seq(1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50)
  }
}

class Day02Spec extends AdventSpec {
  "Day02" when {
    "practicing examples" should {
      "restore gravity assist" in new Environment {
        Program(example1).run shouldEqual 3500
      }
    }

    "solving" should {
      "answer part 1" in new Environment {
        day.solvePart1 shouldEqual 4570637
      }
      "answer part 2" in new Environment {
        day.solvePart2 shouldEqual 5485
      }
    }
  }
}
