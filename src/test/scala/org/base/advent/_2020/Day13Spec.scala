package org.base.advent._2020

import org.base.advent.AdventSpec
import org.base.advent._2020.Day13Spec.Environment
import org.base.advent.util.LineSplitter
import org.mockito.scalatest.IdiomaticMockito

object Day13Spec extends IdiomaticMockito with LineSplitter {
  trait Environment {
    val day = new Day13
    val example1: Seq[String] =
      split("""939
              |7,13,x,x,59,x,31,19""".stripMargin)
  }
}

class Day13Spec extends AdventSpec {
  "Day13" when {
    "practicing examples" should {
      "take bus" in new Environment {
        day.takeBus(example1) shouldEqual 295
      }
      "win contest by cheating :)" in new Environment {
        day.winContest(example1) shouldEqual 1068781
        day.remainderTheorem("67,7,59,61") shouldEqual 754018
        day.remainderTheorem("67,x,7,59,61") shouldEqual 779210
        day.remainderTheorem("67,7,x,59,61") shouldEqual 1261476
        day.remainderTheorem("1789,37,47,1889") shouldEqual 1202161486
      }
    }

    "solving" should {
      "answer part 1" in new Environment {
        day.solvePart1 shouldEqual 222
      }
      "answer part 2" in new Environment {
        day.solvePart2 shouldEqual 408270049879073L
      }
    }
  }
}
