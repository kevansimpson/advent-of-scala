package org.base.advent._2020

import org.base.advent.AdventSpec
import org.base.advent._2020.Day07Spec.Environment
import org.base.advent.util.Util._
import org.mockito.scalatest.IdiomaticMockito

object Day07Spec extends IdiomaticMockito {
  trait Environment {
    val day = new Day07
    val example1: Seq[String] = split("""light red bags contain 1 bright white bag, 2 muted yellow bags.
                                        |dark orange bags contain 3 bright white bags, 4 muted yellow bags.
                                        |bright white bags contain 1 shiny gold bag.
                                        |muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
                                        |shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
                                        |dark olive bags contain 3 faded blue bags, 4 dotted black bags.
                                        |vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
                                        |faded blue bags contain no other bags.
                                        |dotted black bags contain no other bags.""".stripMargin)
    val example2: Seq[String] = split("""shiny gold bags contain 2 dark red bags.
                                        |dark red bags contain 2 dark orange bags.
                                        |dark orange bags contain 2 dark yellow bags.
                                        |dark yellow bags contain 2 dark green bags.
                                        |dark green bags contain 2 dark blue bags.
                                        |dark blue bags contain 2 dark violet bags.
                                        |dark violet bags contain no other bags.""".stripMargin)
  }
}

class Day07Spec extends AdventSpec {
  "Day07" when {
    "practicing examples" should {
      "sum any question" in new Environment {
        day.packShinyGold(example1) shouldEqual 4
      }
      "sum every question" in new Environment {
        day.shinyGoldContains(example1) shouldEqual 32
        day.shinyGoldContains(example2) shouldEqual 126
      }
    }

    "solving" should {
      "answer part 1" in new Environment {
        day.solvePart1 shouldEqual 274
      }
      "answer part 2" in new Environment {
        day.solvePart2 shouldEqual 158730
      }
    }
  }
}
