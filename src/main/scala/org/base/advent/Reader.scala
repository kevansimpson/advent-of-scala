package org.base.advent

import scala.io.Source

object Reader {
  def readFile(filename: String): String =
    Source.fromInputStream(getClass.getResourceAsStream(filename)).getLines().mkString

  def readLines(filename: String): Seq[String] =
    Source.fromInputStream(getClass.getResourceAsStream(filename)).getLines().toSeq

  def readNumbers(filename: String): Seq[Int] = readLines(filename).map(_.toInt)

  def readCSVLines(filename: String): Seq[String] = readLines(filename).flatMap(line => line.split("\\s*,\\s*"))
}
