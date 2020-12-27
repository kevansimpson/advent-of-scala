package org.base.advent._2020

import org.base.advent.AdventSpec
import org.base.advent._2020.Day15Spec.Environment
import org.mockito.scalatest.IdiomaticMockito

object Day15Spec extends IdiomaticMockito {
  trait Environment {
    val day = new Day15
    val example1: Seq[Int] = Seq(0, 3, 6)
    val example1a: Seq[Int] = Seq(1, 3, 2)
    val example1b: Seq[Int] = Seq(2, 1, 3)
    val example1c: Seq[Int] = Seq(1, 2, 3)
    val example1d: Seq[Int] = Seq(2, 3, 1)
    val example1e: Seq[Int] = Seq(3, 2, 1)
    val example1f: Seq[Int] = Seq(3, 1, 2)
  }
}

class Day15Spec extends AdventSpec {
  "Day15" when {
    "practicing examples" should {
      "play memory game" in new Environment {
        day.memoryGameGaveUp(example1) shouldEqual 436
        day.memoryGameGaveUp(example1a) shouldEqual 1
        day.memoryGameGaveUp(example1b) shouldEqual 10
        day.memoryGameGaveUp(example1c) shouldEqual 27
        day.memoryGameGaveUp(example1d) shouldEqual 78
        day.memoryGameGaveUp(example1e) shouldEqual 438
        day.memoryGameGaveUp(example1f) shouldEqual 1836
      }
    }

    "solving" should {
      "answer part 1" in new Environment {
        day.solvePart1 shouldEqual 371
      }
      "answer part 2" in new Environment {
//        day.solvePart2 shouldEqual 352 // takes 25sec
      }
    }
  }
}
