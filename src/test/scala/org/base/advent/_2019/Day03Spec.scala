package org.base.advent._2019

import org.base.advent.AdventSpec
import org.base.advent._2019.Day03Spec.Environment
import org.base.advent.util.Util._
import org.mockito.scalatest.IdiomaticMockito

object Day03Spec extends IdiomaticMockito {
  trait Environment {
    val day = new Day03
    val example1: Seq[Seq[String]] =
      split("""R75,D30,R83,U83,L12,D49,R71,U7,L72
              |U62,R66,U55,R34,D71,R55,D58,R83""".stripMargin).map(csv)
    val example2: Seq[Seq[String]] =
      split("""R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51
              |U98,R91,D20,R16,D67,R40,U7,R15,U6,R7""".stripMargin).map(csv)
  }
}

class Day03Spec extends AdventSpec {
  "Day03" when {
    "practicing examples" should {
      "foo 1" in new Environment {
        day.closestIntersection(example1) shouldEqual 159
        day.closestIntersection(example2) shouldEqual 135
      }
      "foo 2" in new Environment {
        day.fewestSteps(example1) shouldEqual 610
        day.fewestSteps(example2) shouldEqual 410
      }
    }

    "solving" should {
      "answer part 1" in new Environment {
        day.solvePart1 shouldEqual 352
      }
      "answer part 2" in new Environment {
        day.solvePart2 shouldEqual 43848
      }
    }
  }
}
