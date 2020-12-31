package org.base.advent._2020

import org.base.advent.AdventSpec
import org.base.advent._2020.Day16Spec.Environment
import org.base.advent.util.LineSplitter
import org.mockito.scalatest.IdiomaticMockito

object Day16Spec extends IdiomaticMockito with LineSplitter {
  trait Environment {
    val day = new Day16
    val example1: Seq[String] =
      split("""class: 1-3 or 5-7
              |row: 6-11 or 33-44
              |seat: 13-40 or 45-50
              |
              |your ticket:
              |7,1,14
              |
              |nearby tickets:
              |7,3,47
              |40,4,50
              |55,2,20
              |38,6,12""".stripMargin)
    val example2: Seq[String] =
      split("""class: 0-1 or 4-19
              |row: 0-5 or 8-19
              |seat: 0-13 or 16-19
              |
              |your ticket:
              |11,12,13
              |
              |nearby tickets:
              |3,9,18
              |15,1,5
              |5,14,9""".stripMargin)
  }
}

class Day16Spec extends AdventSpec {
  "Day16" when {
    "practicing examples" should {
      "sum invalid tickets" in new Environment {
        day.invalidTickets(example1) shouldEqual 71
      }
      "product departures" in new Environment {
        day.productDepartures(example2, "(row|class|seat)".r) shouldEqual 11 * 12 * 13
      }
    }

    "solving" should {
      "answer part 1" in new Environment {
        day.solvePart1 shouldEqual 26053
      }
      "answer part 2" in new Environment {
        day.solvePart2 shouldEqual 1515506256421L
      }
    }
  }
}
