package org.base.advent._2020

import org.apache.commons.lang3.StringUtils
import org.base.advent.Reader._

/**
  * <b>Part 1</b>
  * You land at the regional airport in time for your next flight. In fact, it looks like you'll even have time to grab
  * some food: all flights are currently delayed due to issues in luggage processing.
  *
  * Due to recent aviation regulations, many rules (your puzzle input) are being enforced about bags and their contents;
  * bags must be color-coded and must contain specific quantities of other color-coded bags. Apparently, nobody
  * responsible for these regulations considered how long they would take to enforce!
  *
  * For example, consider the following rules:
  * <pre>
  * light red bags contain 1 bright white bag, 2 muted yellow bags.
  * dark orange bags contain 3 bright white bags, 4 muted yellow bags.
  * bright white bags contain 1 shiny gold bag.
  * muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
  * shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
  * dark olive bags contain 3 faded blue bags, 4 dotted black bags.
  * vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
  * faded blue bags contain no other bags.
  * dotted black bags contain no other bags.
  * </pre>
  *
  * These rules specify the required contents for 9 bag types. In this example, every faded blue bag is empty, every
  * vibrant plum bag contains 11 bags (5 faded blue and 6 dotted black), and so on.
  *
  * You have a shiny gold bag. If you wanted to carry it in at least one other bag, how many different bag colors would
  * be valid for the outermost bag? (In other words: how many colors can, eventually, contain at least one shiny gold bag?)
  *
  * In the above rules, the following options would be available to you:
  * <ul>
  *     <li>A bright white bag, which can hold your shiny gold bag directly.</li>
  *     <li>A muted yellow bag, which can hold your shiny gold bag directly, plus some other bags.</li>
  *     <li>A dark orange bag, which can hold bright white and muted yellow bags, either of which could then hold your shiny gold bag.</li>
  *     <li>A light red bag, which can hold bright white and muted yellow bags, either of which could then hold your shiny gold bag.</li>
  * </ul>
  *
  * <b>Part 2</b>
  * It's getting pretty expensive to fly these days - not because of ticket prices, but because of the ridiculous number
  * of bags you need to buy!
  *
  * Consider again your shiny gold bag and the rules from the above example:
  * <ul>
  *     <li>faded blue bags contain 0 other bags.</li>
  *     <li>dotted black bags contain 0 other bags.</li>
  *     <li>vibrant plum bags contain 11 other bags: 5 faded blue bags and 6 dotted black bags.</li>
  *     <li>dark olive bags contain 7 other bags: 3 faded blue bags and 4 dotted black bags.</li>
  * </ul>
  *
  * So, a single shiny gold bag must contain 1 dark olive bag (and the 7 bags within it) plus 2 vibrant plum bags
  * (and the 11 bags within each of those): 1 + 1*7 + 2 + 2*11 = 32 bags!
  *
  * Of course, the actual rules have a small chance of going several levels deeper than this example; be sure to count
  * all of the bags, even if the nesting becomes topologically impractical!
  *
  * Here's another example:
  * <pre>
  * shiny gold bags contain 2 dark red bags.
  * dark red bags contain 2 dark orange bags.
  * dark orange bags contain 2 dark yellow bags.
  * dark yellow bags contain 2 dark green bags.
  * dark green bags contain 2 dark blue bags.
  * dark blue bags contain 2 dark violet bags.
  * dark violet bags contain no other bags.
  * </pre>
  * In this example, a single shiny gold bag must contain 126 other bags.
  *
  * How many individual bags are required inside your single shiny gold bag?
  */
class Day07 {
  private lazy val input = readLines("/2020/input07.txt")

  type BagQty = (String, Int)
  type BagMap = Map[String, Seq[BagQty]]

  private val RULE = "(.+) bags contain (.+)\\.".r
  private val BAGS = "(\\d+) (.+) bag[s]?".r

  private final val sgBag: BagQty = ("shiny gold", 1)

  def packShinyGold(lines: Seq[String]): Int = {
    implicit val bagMap: BagMap = processLuggage(lines)
    bagMap.keys.filter(!sgBag._1.equals(_)).count(bagColor => canHold(sgBag, Seq((bagColor, 1))) > 0)
  }

  def shinyGoldContains(lines: Seq[String]): Int = {
    implicit val bagMap: BagMap = processLuggage(lines)
    countBags(Seq(sgBag)) - 1 // don't count the shiny gold bag itself
  }

  def countBags(rules: Seq[BagQty] = Seq.empty[BagQty])(implicit bagMap: BagMap): Int = {
    if (rules.isEmpty) 1
    else
      rules
        .map(bagQty => {
          val next = bagMap.getOrElse(bagQty._1, Seq.empty[BagQty])
          bagQty._2 * countBags(next) + (if (next.isEmpty) 0 else bagQty._2)
        })
        .sum
  }

  def canHold(bag: BagQty, rules: Seq[BagQty] = Seq.empty[BagQty])(implicit bagMap: BagMap): Int = {
    if (rules.isEmpty) 0
    else {
      val holds = rules.filter(bq => StringUtils.equals(bq._1, bag._1))
      if (holds.isEmpty)
        rules.map(bagQty => canHold((bag._1, bag._2 * bagQty._2), bagMap.getOrElse(bagQty._1, Seq.empty[BagQty]))).sum
      else holds.head._2 * bag._2
    }
  }

  def processLuggage(lines: Seq[String]): BagMap =
    lines.map { case RULE(color, contents) => color -> splitBags(contents) }.toMap

  def splitBags(contents: String): Seq[(String, Int)] =
    contents
      .split(", ")
      .toSeq
      .map {
        case BAGS(count, color2) => (color2, count.toInt)
        case bags => (bags, 0)
      }
      .filter(_._2 > 0)

  def solvePart1: Long = packShinyGold(input)

  def solvePart2: Long = shinyGoldContains(input)
}
