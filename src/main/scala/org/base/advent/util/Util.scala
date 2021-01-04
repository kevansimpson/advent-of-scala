package org.base.advent.util

case class MinMax[A](min: A, max: A)

object Util {
  def minMax(a: Seq[Int]): MinMax[Int] = {
    if (a.isEmpty) MinMax(0, 0)
    else a.foldLeft(MinMax(a.head, a.head))((result, e) => MinMax(math.min(result.min, e), math.max(result.max, e)))
  }

  def split(string: String): Seq[String] = string.stripMargin.split("\n").toSeq
}
