package org.base.advent._2020

import scala.util.matching.Regex
import org.base.advent.Reader._
import org.base.advent._2020.Day16.Ticket

/**
  * <b>Part 1</b>
  * As you're walking to yet another connecting flight, you realize that one of the legs of your re-routed trip coming
  * up is on a high-speed train. However, the train ticket you were given is in a language you don't understand. You
  * should probably figure out what it says before you get to the train station after the next flight.
  *
  * Unfortunately, you can't actually read the words on the ticket. You can, however, read the numbers, and so you
  * figure out the fields these tickets must have and the valid ranges for values in those fields.
  *
  * You collect the rules for ticket fields, the numbers on your ticket, and the numbers on other nearby tickets for
  * the same train service (via the airport security cameras) together into a single document you can reference
  * (your puzzle input).
  *
  * The rules for ticket fields specify a list of fields that exist somewhere on the ticket and the valid ranges of
  * values for each field. For example, a rule like class: 1-3 or 5-7 means that one of the fields in every ticket is
  * named class and can be any value in the ranges 1-3 or 5-7 (inclusive, such that 3 and 5 are both valid in this
  * field, but 4 is not).
  *
  * Each ticket is represented by a single line of comma-separated values. The values are the numbers on the ticket in
  * the order they appear; every ticket has the same format. For example, consider this ticket:
  * <pre>
  * .--------------------------------------------------------.
  * | ????: 101    ?????: 102   ??????????: 103     ???: 104 |
  * |                                                        |
  * | ??: 301  ??: 302             ???????: 303      ??????? |
  * | ??: 401  ??: 402           ???? ????: 403    ????????? |
  * '--------------------------------------------------------'
  * </pre>
  *
  * Here, ? represents text in a language you don't understand. This ticket might be represented
  * as 101,102,103,104,301,302,303,401,402,403; of course, the actual train tickets you're looking at are much more
  * complicated. In any case, you've extracted just the numbers in such a way that the first number is always the same
  * specific field, the second number is always a different specific field, and so on - you just don't know what each
  * position actually means!
  *
  * Start by determining which tickets are completely invalid; these are tickets that contain values which aren't valid
  * for any field. Ignore your ticket for now.
  *
  * For example, suppose you have the following notes:
  * <pre>
  * class: 1-3 or 5-7
  * row: 6-11 or 33-44
  * seat: 13-40 or 45-50
  *
  * your ticket:
  * 7,1,14
  *
  * nearby tickets:
  * 7,3,47
  * 40,4,50
  * 55,2,20
  * 38,6,12
  * </pre>
  *
  * It doesn't matter which position corresponds to which field; you can identify invalid nearby tickets by considering
  * only whether tickets contain values that are not valid for any field. In this example, the values on the first
  * nearby ticket are all valid for at least one field. This is not true of the other three nearby tickets: the
  * values 4, 55, and 12 are are not valid for any field. Adding together all of the invalid values produces your
  * ticket scanning error rate: 4 + 55 + 12 = 71.
  *
  * Consider the validity of the nearby tickets you scanned. <b>What is your ticket scanning error rate?</b>
  *
  * <b>Part 2</b>
  * Now that you've identified which tickets contain invalid values, discard those tickets entirely. Use the remaining
  * valid tickets to determine which field is which.
  *
  * Using the valid ranges for each field, determine what order the fields appear on the tickets. The order is
  * consistent between all tickets: if seat is the third field, it is the third field on every ticket, including your ticket.
  *
  * For example, suppose you have the following notes:
  * <pre>
  * class: 0-1 or 4-19
  * row: 0-5 or 8-19
  * seat: 0-13 or 16-19
  *
  * your ticket:
  * 11,12,13
  *
  * nearby tickets:
  * 3,9,18
  * 15,1,5
  * 5,14,9
  * </pre>
  *
  * Based on the nearby tickets in the above example, the first position must be row, the second position must be class,
  * and the third position must be seat; you can conclude that in your ticket, class is 12, row is 11, and seat is 13.
  *
  * Once you work out which field is which, look for the six fields on your ticket that start with the word departure.
  * <b>What do you get if you multiply those six values together?</b>
  */
class Day16 {
  private lazy val input = readLines("/2020/input16.txt")

  def invalidTickets(lines: Seq[String]): Long = {
    val doc = Day16.readDocument(lines)
    doc.nearbyTix.map(nearby => nearby.filter(value => doc.rules.forall(!_.apply(value))).sum).sum
  }

  def productDepartures(lines: Seq[String], regex: Regex = "^(departure).*".r): Long = {
    val doc = Day16.readDocument(lines)
    val validTix = doc.nearbyTix.filter(nearby => nearby.forall(value => doc.rules.exists(_.apply(value))))
    val ruleMap = doc.rules
      .map(rule => rule.name -> doc.rules.indices.filter(index => validTix.forall(value => rule(value(index)))))
      .toMap
    val departures = resolveColumns(ruleMap).map(entry => entry._1 -> doc.numbers(entry._2))
    departures.filter(name => regex.matches(name._1)).values.map(_.toLong).product
  }

  def resolveColumns(colMap: Map[String, Seq[Int]], result: Map[String, Int] = Map.empty): Map[String, Int] = {
    if (colMap.isEmpty) result
    else {
      val singleRest = colMap.groupBy(_._2.size == 1)
      val single: (String, Seq[Int]) = singleRest.getOrElse(true, Map.empty).head
      val rest: Map[String, Seq[Int]] =
        singleRest.getOrElse(false, Map.empty).map(entry => entry._1 -> entry._2.filter(_ != single._2.head))
      resolveColumns(rest, result + (single._1 -> single._2.head))
    }
  }

  def solvePart1: Long = invalidTickets(input)

  def solvePart2: Long = productDepartures(input)
}

case class Rule(name: String, either: Seq[Range]) {
  def apply(value: Int): Boolean = either.exists(_.contains(value))
}

case class Document(rules: Seq[Rule], numbers: Ticket, nearbyTix: Seq[Ticket])

object Day16 {
  type Ticket = Seq[Int]

  private val RuleDef = "([\\w ]+): (\\d+)-(\\d+) or (\\d+)-(\\d+)".r

  def readDocument(input: Seq[String]): Document = {
    val (rawRules, rawTix) = input.span(_.nonEmpty)
    val (myTix, nearby) = rawTix.tail.span(_.nonEmpty)
    val rules = rawRules.map {
      case RuleDef(desc, a1, a2, b1, b2) =>
        Rule(desc, Seq(Range.inclusive(a1.toInt, a2.toInt), Range.inclusive(b1.toInt, b2.toInt)))
    }

    Document(rules, readTix(myTix(1)), nearby.slice(2, nearby.size).map(readTix))
  }

  private def readTix(line: String): Seq[Int] = line.split(",").map(_.toInt).toSeq
}
