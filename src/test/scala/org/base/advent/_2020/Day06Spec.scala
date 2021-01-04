package org.base.advent._2020

import org.base.advent.AdventSpec
import org.base.advent._2020.Day06Spec.Environment
import org.base.advent.util.Util._
import org.mockito.scalatest.IdiomaticMockito

object Day06Spec extends IdiomaticMockito {
  trait Environment {
    val day = new Day06
    val example1: Seq[String] = split("""abcx
                                        |abcy
                                        |abcz""".stripMargin)
    val example2: Seq[String] = split("""abc
                                       |
                                       |a
                                       |b
                                       |c
                                       |
                                       |ab
                                       |ac
                                       |
                                       |a
                                       |a
                                       |a
                                       |a
                                       |
                                       |b""".stripMargin)
    val example3: Seq[String] = split("""abc
                                        |
                                        |a
                                        |b
                                        |c
                                        |
                                        |ab
                                        |ac
                                        |
                                        |a
                                        |a
                                        |a
                                        |a
                                        |
                                        |b""".stripMargin)
  }
}

class Day06Spec extends AdventSpec {
  "Day06" when {
    "practicing examples" should {
      "sum any question" in new Environment {
        day.sumQuestions(example1, day.anyAnswer) shouldEqual 6
        day.sumQuestions(example2, day.anyAnswer) shouldEqual 11
      }
      "sum every question" in new Environment {
        day.sumQuestions(example3, day.everyAnswer) shouldEqual 6
      }
    }

    "solving" should {
      "answer part 1" in new Environment {
        day.solvePart1 shouldEqual 6457
      }
      "answer part 2" in new Environment {
        day.solvePart2 shouldEqual 3260
      }
    }
  }
}
