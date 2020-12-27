package org.base.advent

import org.base.advent.ReaderSpec.Environment
import org.mockito.scalatest.IdiomaticMockito

object ReaderSpec extends IdiomaticMockito {
  trait Environment {}
}

class ReaderSpec extends AdventSpec {
  "A ReaderSpec" when {
    "Testing Reader" should {
      "readFile" in new Environment {
        Reader.readFile("/testNumberCSV.txt") shouldEqual "1 2 3"
      }

      "readLines" in new Environment {
        Reader.readLines("/testNumberLines.txt") shouldEqual Seq("123", "456", "789")
      }

      "readNumbers" in new Environment {
        Reader.readNumbers("/testNumberLines.txt") shouldEqual Seq(123, 456, 789)
      }
    }
  }
}
