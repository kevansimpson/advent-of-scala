package org.base.advent._2020

import org.base.advent.AdventSpec
import org.base.advent._2020.Day08Spec.Environment
import org.base.advent.util.Util._
import org.mockito.scalatest.IdiomaticMockito

object Day08Spec extends IdiomaticMockito {
  trait Environment {
    val day = new Day08
    val example1: Seq[String] = split("""nop +0
                                        |acc +1
                                        |jmp +4
                                        |acc +3
                                        |jmp -3
                                        |acc -99
                                        |acc +1
                                        |jmp -4
                                        |acc +6""".stripMargin)
    val example2: Seq[String] = split("""""".stripMargin)
  }
}

class Day08Spec extends AdventSpec {
  "Day08" when {
    "practicing examples" should {
      "sum any question" in new Environment {
        day.gameOn(example1)._1 shouldEqual 5
      }
      "sum every question" in new Environment {
        day.terminateLoop(example1) shouldEqual 8
      }
    }

    "solving" should {
      "answer part 1" in new Environment {
        day.solvePart1 shouldEqual 1528
      }
      "answer part 2" in new Environment {
        day.solvePart2 shouldEqual 640
      }
    }
  }
}
