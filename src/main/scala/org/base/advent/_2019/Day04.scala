package org.base.advent._2019

import org.apache.commons.lang3.StringUtils

/**
  * <b>Part 1</b>
  * You arrive at the Venus fuel depot only to discover it's protected by a password. The Elves had written the password
  * on a sticky note, but someone threw it out.
  *
  * However, they do remember a few key facts about the password:
  * <ul>
  *      <li>It is a six-digit number.</li>
  *      <li>The value is within the range given in your puzzle input.</li>
  *      <li>Two adjacent digits are the same (like 22 in 122345).</li>
  *      <li>Going from left to right, the digits never decrease;
  *          they only ever increase or stay the same (like 111123 or 135679).</li>
  * </ul>
  * Other than the range rule, the following are true:
  * <ul>
  *      <li>111111 meets these criteria (double 11, never decreases).</li>
  *      <li>223450 does not meet these criteria (decreasing pair of digits 50).</li>
  *      <li>123789 does not meet these criteria (no double).</li>
  * </ul>
  *
  * How many different passwords within the range given in your puzzle input meet these criteria?
  *
  * <h2>Part 2</h2>
  * An Elf just remembered one more important detail: the two adjacent matching digits are not part of a larger group of
  * matching digits.
  *
  * Given this additional criterion, but still ignoring the range rule, the following are now true:
  * <ul>
  *      <li>112233 meets these criteria because the digits never decrease and all repeated digits are exactly two digits long.</li>
  *      <li>123444 no longer meets the criteria (the repeated 44 is part of a larger group of 444).</li>
  *      <li>111122 meets the criteria (even though 1 is repeated more than twice, it still contains a double 22).</li>
  * </ul>
  *
  * How many different passwords within the range given in your puzzle input meet all of the criteria?
  */
class Day04 {
  private lazy val input = 235741 to 706948

  def solvePart1: Long = input.count(simple)

  def solvePart2: Long = input.count(complex)

  private val DOUBLE = "(\\d)(\\1)".r

  def simple(value: Int): Boolean = isOrdered(value) && hasDouble(value)

  def complex(value: Int): Boolean = isOrdered(value) && hasOnlyDouble(value)

  def hasDouble(value: Int): Boolean = DOUBLE.findFirstIn(value.toString).nonEmpty

  def hasOnlyDouble(value: Int): Boolean = {
    val str = value.toString
    str.split("").count(StringUtils.countMatches(str, _) == 2) > 0
  }

  def isOrdered(value: Int): Boolean = value.toString.split("").sorted.mkString.toInt == value
}
