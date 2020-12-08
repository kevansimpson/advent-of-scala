package org.base.advent.util

trait LineSplitter {
  def split(string: String) = string.stripMargin.split("\n").toSeq
}

class Util {}
