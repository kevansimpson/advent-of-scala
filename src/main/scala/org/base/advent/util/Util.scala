package org.base.advent.util

import java.math.BigInteger

case class MinMax(min: Long, max: Long) {
  def includes(value: Long): Boolean = value >= min && value <= max
}

object Util {
  def bits2long(bits: String): Long = new BigInteger(bits, 2).longValue()

  def csv(line: String): Seq[String] = line.split("\\s*,\\s*").toSeq

  def minMax(a: Seq[Long]): MinMax =
    if (a.isEmpty) MinMax(0L, 0L)
    else a.foldLeft(MinMax(a.head, a.head))((result, e) => MinMax(math.min(result.min, e), math.max(result.max, e)))

  def split(string: String): Seq[String] = string.stripMargin.split("\n").toSeq
}
