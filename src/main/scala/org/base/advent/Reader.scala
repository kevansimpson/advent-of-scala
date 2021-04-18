package org.base.advent

import scala.io.Source

import org.base.advent.util.Util.csv

object Reader {
  def readFile(filename: String): String =
    Source.fromInputStream(getClass.getResourceAsStream(filename)).getLines().mkString

  def readLines(filename: String): Seq[String] =
    Source.fromInputStream(getClass.getResourceAsStream(filename)).getLines().toSeq

  def readNumbers(filename: String): Seq[Int] = readLines(filename).map(_.toInt)

  def readLongs(filename: String): Seq[Long] = readLines(filename).map(_.toLong)

  def readCSVLines(filename: String): Seq[Seq[String]] = readLines(filename).map(csv)

  def readCSVNumbers(filename: String): Seq[Int] = readCSVLines(filename).head.map(_.toInt)

  def readCSVLongs(filename: String): Seq[Long] = readCSVLines(filename).head.map(_.toLong)
}
