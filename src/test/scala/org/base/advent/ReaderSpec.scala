package org.base.advent

import org.base.advent.ReaderSpec.Environment
import org.mockito.scalatest.IdiomaticMockito

object ReaderSpec extends IdiomaticMockito {
  trait Environment {
    val reader: SimpleReader = new SimpleReader
  }
}

class ReaderSpec extends AdventSpec {
  "A ReaderSpec" when {
    "Testing Reader" should {
      "readFile" in new Environment {
        reader.readFile("/testNumberCSV.txt") shouldEqual "1 2 3"
      }

      "readLines" in new Environment {
        reader.readLines("/testNumberLines.txt") shouldEqual Seq("123", "456", "789")
      }

      "readNumbers" in new Environment {
        reader.readNumbers("/testNumberLines.txt") shouldEqual Seq(123, 456, 789)
      }
    }
  }
}
