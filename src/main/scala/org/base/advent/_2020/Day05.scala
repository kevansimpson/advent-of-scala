package org.base.advent._2020

import org.apache.commons.lang3.StringUtils
import org.base.advent.Reader

/**
  * <b>Part 1</b>
  * You board your plane only to discover a new problem: you dropped your boarding pass! You aren't sure which seat is
  * yours, and all of the flight attendants are busy with the flood of people that suddenly made it through passport control.
  *
  * You write a quick program to use your phone's camera to scan all of the nearby boarding passes (your puzzle input);
  * perhaps you can find your seat through process of elimination.
  *
  * Instead of zones or groups, this airline uses binary space partitioning to seat people. A seat might be specified
  * like FBFBBFFRLR, where F means "front", B means "back", L means "left", and R means "right".
  *
  * The first 7 characters will either be F or B; these specify exactly one of the 128 rows on the plane
  * (numbered 0 through 127). Each letter tells you which half of a region the given seat is in. Start with the whole
  * list of rows; the first letter indicates whether the seat is in the front (0 through 63) or the
  * back (64 through 127). The next letter indicates which half of that region the seat is in, and so on until you're
  * left with exactly one row.
  *
  * For example, consider just the first seven characters of FBFBBFFRLR:
  * <ul>
  *     <li>Start by considering the whole range, rows 0 through 127.</li>
  *     <li>F means to take the lower half, keeping rows 0 through 63.</li>
  *     <li>B means to take the upper half, keeping rows 32 through 63.</li>
  *     <li>F means to take the lower half, keeping rows 32 through 47.</li>
  *     <li>B means to take the upper half, keeping rows 40 through 47.</li>
  *     <li>B keeps rows 44 through 47.</li>
  *     <li>F keeps rows 44 through 45.</li>
  *     <li>The final F keeps the lower of the two, row 44.</li>
  * </ul>
  *
  * The last three characters will be either L or R; these specify exactly one of the 8 columns of seats on the plane (numbered 0 through 7). The same process as above proceeds again, this time with only three steps. L means to keep the lower half, while R means to keep the upper half.
  *
  * For example, consider just the last 3 characters of FBFBBFFRLR:
  * <ul>
  *     <li>Start by considering the whole range, columns 0 through 7.</li>
  *     <li>R means to take the upper half, keeping columns 4 through 7.</li>
  *     <li>L means to take the lower half, keeping columns 4 through 5.</li>
  *     <li>The final R keeps the upper of the two, column 5.</li>
  * </ul>
  *
  * So, decoding FBFBBFFRLR reveals that it is the seat at row 44, column 5.
  *
  * Every seat also has a unique seat ID: multiply the row by 8, then add the column.
  * In this example, the seat has ID 44 * 8 + 5 = 357.
  *
  * Here are some other boarding passes:
  * <ul>
  *     <li>BFFFBBFRRR: row 70, column 7, seat ID 567.</li>
  *     <li>FFFBBBFRRR: row 14, column 7, seat ID 119.</li>
  *     <li>BBFFBBFRLL: row 102, column 4, seat ID 820.</li>
  * </ul>
  *
  * As a sanity check, look through your list of boarding passes. What is the highest seat ID on a boarding pass?
  *
  * <b>Part 2</b>
  * Ding! The "fasten seat belt" signs have turned on. Time to find your seat.
  *
  * It's a completely full flight, so your seat should be the only missing boarding pass in your list. However, there's
  * a catch: some of the seats at the very front and back of the plane don't exist on this aircraft, so they'll be
  * missing from your list as well.
  *
  * Your seat wasn't at the very front or back, though; the seats with IDs +1 and -1 from yours will be in your list.
  *
  * What is the ID of your seat?
  */
class Day05 extends Reader {
  private lazy val input = readLines("/2020/input05.txt")

  def highestSeatID(lines: Seq[String]): Long = {
    lines.map(toSeatID).map(seat2int).max
  }

  def mySeatID(lines: Seq[String]): Long = {
    val seat = lines.map(toSeatID).sorted.groupBy(_._1).filter(_._2.size == 7)
    val row = seat.head._1
    val col = (0 to 7).sum - seat.values.map(_.map(_._2)).head.sum
    seat2int((row, col))
  }

  def toSeatID(seat: String): (Int, Int) = {
    (partition(seat.substring(0, 7)), partition(seat.substring(7)))
  }

  def seat2int(tpl: (Int, Int)): Long = tpl._1 * 8 + tpl._2

  def partition(seat: String, start: Int = 0): Int = {
    if (StringUtils.isEmpty(seat)) start
    else {
      val delta = math.pow(2, seat.length - 1).toInt
      seat.substring(0, 1) match {
        case "B" | "R" => partition(seat.substring(1), start + delta)
        case _ => partition(seat.substring(1), start)
      }
    }
  }

  def solvePart1: Long = highestSeatID(input)

  def solvePart2: Long = mySeatID(input)
}
