package org.base.advent._2020

import org.base.advent.AdventSpec
import org.base.advent._2020.Day14Spec.Environment
import org.base.advent.util.LineSplitter
import org.mockito.scalatest.IdiomaticMockito

object Day14Spec extends IdiomaticMockito with LineSplitter {
  trait Environment {
    val day = new Day14
    val example1: Seq[String] =
      split("""mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
              |mem[8] = 11
              |mem[7] = 101
              |mem[8] = 0""".stripMargin)
    val example2: Seq[String] =
      split("""mask = 000000000000000000000000000000X1001X
              |mem[42] = 100
              |mask = 00000000000000000000000000000000X0XX
              |mem[26] = 1""".stripMargin)
  }
}

class Day14Spec extends AdventSpec {
  "Day14" when {
    "practicing examples" should {
      "dock ferry" in new Environment {
        day.dockFerry(example1) shouldEqual 165
      }
      "dock ferry again" in new Environment {
        day.dockFerry(example2, day.writeValueV2) shouldEqual 208
      }
    }

    "solving" should {
      "answer part 1" in new Environment {
        day.solvePart1 shouldEqual 12135523360904L
      }
      "answer part 2" in new Environment {
        day.solvePart2 shouldEqual 2741969047858L
      }
    }
  }
}
