package org.base.advent._2019

import org.base.advent.AdventSpec
import org.base.advent._2019.Day01Spec.Environment
import org.mockito.scalatest.IdiomaticMockito

object Day01Spec extends IdiomaticMockito {
  trait Environment {
    val day = new Day01
  }
}

class Day01Spec extends AdventSpec {
  "Day01" when {
    "practicing examples" should {
      "calculating required fuel" in new Environment {
        day.requiredFuel(12) shouldEqual 2
        day.requiredFuel(14) shouldEqual 2
        day.requiredFuel(1969) shouldEqual 654
        day.requiredFuel(100756) shouldEqual 33583
      }
      "include fuel for fuel" in new Environment {
        day.fuelFuel(12) shouldEqual 2
        day.fuelFuel(14) shouldEqual 2
        day.fuelFuel(1969) shouldEqual 966
        day.fuelFuel(100756) shouldEqual 50346
      }
    }

    "solving" should {
      "answer part 1" in new Environment {
        day.solvePart1 shouldEqual 3266288
      }
      "answer part 2" in new Environment {
        day.solvePart2 shouldEqual 4896582
      }
    }
  }
}
