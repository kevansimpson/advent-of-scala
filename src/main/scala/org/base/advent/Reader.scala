package org.base.advent

import java.io.IOException
import java.nio.charset.Charset

import scala.jdk.CollectionConverters

import org.apache.commons.io.IOUtils
import org.apache.commons.io.IOUtils._
import org.apache.commons.lang3.math.NumberUtils

trait Reader {
  def readFile(filename: String): String = {
    resourceToString(filename, Charset.defaultCharset())
  }

  @throws(classOf[IOException])
  def readLines(filename: String): Seq[String] = {
    CollectionConverters
      .ListHasAsScala(IOUtils.readLines(getClass.getResourceAsStream(filename), Charset.defaultCharset()))
      .asScala
      .toSeq
  }

  @throws(classOf[IOException])
  def readNumbers(filename: String): Seq[Int] = {
    readLines(filename).map(NumberUtils.toInt)
  }

  @throws(classOf[IOException])
  def readCSVLines(filename: String): Seq[String] = {
    readLines(filename).flatMap(line => line.split("\\s*,\\s*"))
  }

//  @throws(classOf[IOException])
//  def readCSVNumbers(filename: String): Seq[Int] = {
//    readLines(filename).flatMap(line => line.split("\\s*,\\s*"))
//  }

//  default int[] readNumbersCSV(filename: String) throws IOException {
//    return Stream.of(readInput(filename).split("\\s*,\\s*")).mapToInt(Integer::parseInt).toArray();
//  }
}

case class SimpleReader() extends Reader {}
