package org.base.advent._2020

import org.base.advent.Reader

/**
  * <b>Part 1</b>
  * With your neighbor happily enjoying their video game, you turn your attention to an open data port on the little
  * screen in the seat in front of you.
  *
  * Though the port is non-standard, you manage to connect it to your computer through the clever use of several
  * paperclips. Upon connection, the port outputs a series of numbers (your puzzle input).
  *
  * The data appears to be encrypted with the eXchange-Masking Addition System (XMAS) which, conveniently for you, is
  * an old cypher with an important weakness.
  *
  * XMAS starts by transmitting a preamble of 25 numbers. After that, each number you receive should be the sum of any
  * two of the 25 immediately previous numbers. The two numbers will have different values, and there might be more
  * than one such pair.
  *
  * For example, suppose your preamble consists of the numbers 1 through 25 in a random order. To be valid, the next
  * number must be the sum of two of those numbers:
  * <ul>
  *     <li>26 would be a valid next number, as it could be 1 plus 25 (or many other pairs, like 2 and 24).</li>
  *     <li>49 would be a valid next number, as it is the sum of 24 and 25.</li>
  *     <li>100 would not be valid; no two of the previous 25 numbers sum to 100.</li>
  *     <li>50 would also not be valid; although 25 appears in the previous 25 numbers, the two numbers in the pair
  *         must be different.</li>
  * </ul>
  *
  * Suppose the 26th number is 45, and the first number (no longer an option, as it is more than 25 numbers ago) was 20.
  * Now, for the next number to be valid, there needs to be some pair of numbers among 1-19, 21-25, or 45 that add up to it:
  * <ul>
  *     <li>26 would still be a valid next number, as 1 and 25 are still within the previous 25 numbers.</li>
  *     <li>65 would not be valid, as no two of the available numbers sum to it.</li>
  *     <li>64 and 66 would both be valid, as they are the result of 19+45 and 21+45 respectively.</li>
  * </ul>
  *
  * Here is a larger example which only considers the previous 5 numbers (and has a preamble of length 5):
  * <pre>
  * 35
  * 20
  * 15
  * 25
  * 47
  * 40
  * 62
  * 55
  * 65
  * 95
  * 102
  * 117
  * 150
  * 182
  * 127
  * 219
  * 299
  * 277
  * 309
  * 576
  * </pre>
  *
  * In this example, after the 5-number preamble, almost every number is the sum of two of the previous 5 numbers;
  * the only number that does not follow this rule is 127.
  *
  * The first step of attacking the weakness in the XMAS data is to find the first number in the list (after the
  * preamble) which is not the sum of two of the 25 numbers before it.
  * <b>What is the first number that does not have this property?</b>
  *
  * <b>Part 2</b>
  * The final step in breaking the XMAS encryption relies on the invalid number you just found: you must find a contiguous set of at least two numbers in your list which sum to the invalid number from step 1.
  *
  * Again consider the above example:
  * <pre>see above</pre>
  *
  * In this list, adding up all of the numbers from 15 through 40 produces the invalid number from step 1, 127.
  * (Of course, the contiguous set of numbers in your actual list might be much longer.)
  *
  * To find the encryption weakness, add together the smallest and largest number in this contiguous range; in this
  * example, these are 15 and 47, producing 62.
  *
  * <b>What is the encryption weakness in your XMAS-encrypted list of numbers?</b>
  */
class Day09 extends Reader {
  private lazy val input = readNumbers("/2020/input09.txt")

  def decodeXmas(codes: Seq[Int], preamble: Int = 25, index: Int = 0): Int = {
    val current = preamble + index
    val target = codes(current)
    val pairs = codes.slice(index, current).combinations(2).toSeq
    if (pairs.map(_.sum).count(_ == target) > 0) decodeXmas(codes, preamble, index + 1) else target
  }

  def decryptXmas(codes: Seq[Int], preamble: Int = 25): Long = {
    val target = decodeXmas(codes, preamble)
    val longCodes = codes.map(_.toLong)
    val range = (4 to 17).map(findRange(longCodes, target, 0, _)).filter(_.nonEmpty).head
    println(range)
    range.min + range.max
  }

  def findRange(codes: Seq[Long], target: Long, index: Int = 0, length: Int = 2): Seq[Long] = {
    if ((index + length) >= codes.length) Seq.empty[Long]
    else if (codes.slice(index, index + length).sum == target) codes.slice(index, index + length)
    else findRange(codes, target, index + 1, length)
  }

  def solvePart1: Long = decodeXmas(input)

  def solvePart2: Long = decryptXmas(input)
}
