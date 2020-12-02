package org.base

import org.scalatest.{Assertion, Matchers, OptionValues, WordSpec}

package object advent {
  trait AdventSpec extends WordSpec with Matchers with OptionValues with Assertion {}
}
