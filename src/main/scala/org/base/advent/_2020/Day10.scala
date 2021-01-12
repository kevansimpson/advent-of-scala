package org.base.advent._2020

import org.base.advent.Reader._

/**
  * <b>Part 1</b>
  * Patched into the aircraft's data port, you discover weather forecasts of a massive tropical storm. Before you can
  * figure out whether it will impact your vacation plans, however, your device suddenly turns off!
  *
  * Its battery is dead.
  *
  * You'll need to plug it in. There's only one problem: the charging outlet near your seat produces the wrong number
  * of jolts. Always prepared, you make a list of all of the joltage adapters in your bag.
  *
  * Each of your joltage adapters is rated for a specific output joltage (your puzzle input). Any given adapter can
  * take an input 1, 2, or 3 jolts lower than its rating and still produce its rated output joltage.
  *
  * In addition, your device has a built-in joltage adapter rated for 3 jolts higher than the highest-rated adapter in
  * your bag. (If your adapter list were 3, 9, and 6, your device's built-in adapter would be rated for 12 jolts.)
  *
  * Treat the charging outlet near your seat as having an effective joltage rating of 0.
  *
  * Since you have some time to kill, you might as well test all of your adapters. Wouldn't want to get to your resort
  * and realize you can't even charge your device!
  *
  * If you use every adapter in your bag at once, what is the distribution of joltage differences between the charging
  * outlet, the adapters, and your device?
  *
  * For example, suppose that in your bag, you have adapters with the following joltage ratings:
  * <pre>
  * 16
  * 10
  * 15
  * 5
  * 1
  * 11
  * 7
  * 19
  * 6
  * 12
  * 4
  * </pre>
  *
  * With these adapters, your device's built-in joltage adapter would be rated for 19 + 3 = 22 jolts, 3 higher than
  * the highest-rated adapter.
  *
  * Because adapters can only connect to a source 1-3 jolts lower than its rating, in order to use every adapter,
  * you'd need to choose them like this:
  * <ul>
  *     <li>The charging outlet has an effective rating of 0 jolts, so the only adapters that could connect to it
  *         directly would need to have a joltage rating of 1, 2, or 3 jolts. Of these, only one you have is an adapter
  *         rated 1 jolt (difference of 1).</li>
  *     <li>From your 1-jolt rated adapter, the only choice is your 4-jolt rated adapter (difference of 3).</li>
  *     <li>From the 4-jolt rated adapter, the adapters rated 5, 6, or 7 are valid choices. However, in order to not
  *         skip any adapters, you have to pick the adapter rated 5 jolts (difference of 1).</li>
  *     <li>Similarly, the next choices would need to be the adapter rated 6 and then the adapter rated 7
  *         (with difference of 1 and 1).</li>
  *     <li>The only adapter that works with the 7-jolt rated adapter is the one rated 10 jolts (difference of 3).</li>
  *     <li>From 10, the choices are 11 or 12; choose 11 (difference of 1) and then 12 (difference of 1).</li>
  *     <li>After 12, only valid adapter has a rating of 15 (difference of 3), then 16 (difference of 1),
  *         then 19 (difference of 3).</li>
  *     <li>Finally, your device's built-in adapter is always 3 higher than the highest adapter, so its rating
  *         is 22 jolts (always a difference of 3).</li>
  * </ul>
  * In this example, when using every adapter, there are 7 differences of 1 jolt and 5 differences of 3 jolts.
  *
  * <i>Larger example in spec.</i>
  *
  * Find a chain that uses all of your adapters to connect the charging outlet to your device's built-in adapter and
  * count the joltage differences between the charging outlet, the adapters, and your device.
  * <b>What is the number of 1-jolt differences multiplied by the number of 3-jolt differences?</b>
  *
  * <b>Part 2</b>
  * To completely determine whether you have enough adapters, you'll need to figure out how many different ways they
  * can be arranged. Every arrangement needs to connect the charging outlet to your device. The previous rules about
  * when adapters can successfully connect still apply.
  *
  * The first example above (the one that starts with 16, 10, 15) supports the following arrangements:
  * <pre>
  * (0), 1, 4, 5, 6, 7, 10, 11, 12, 15, 16, 19, (22)
  * (0), 1, 4, 5, 6, 7, 10, 12, 15, 16, 19, (22)
  * (0), 1, 4, 5, 7, 10, 11, 12, 15, 16, 19, (22)
  * (0), 1, 4, 5, 7, 10, 12, 15, 16, 19, (22)
  * (0), 1, 4, 6, 7, 10, 11, 12, 15, 16, 19, (22)
  * (0), 1, 4, 6, 7, 10, 12, 15, 16, 19, (22)
  * (0), 1, 4, 7, 10, 11, 12, 15, 16, 19, (22)
  * (0), 1, 4, 7, 10, 12, 15, 16, 19, (22)
  * </pre>
  * (The charging outlet and your device's built-in adapter are shown in parentheses.) Given the adapters from the
  * first example, the total number of arrangements that connect the charging outlet to your device is 8.
  *
  * The second example above (the one that starts with 28, 33, 18) has many arrangements. Here are a few:
  * <pre>
  * (0), 1, 2, 3, 4, 7, 8, 9, 10, 11, 14, 17, 18, 19, 20, 23, 24, 25, 28, 31,
  * 32, 33, 34, 35, 38, 39, 42, 45, 46, 47, 48, 49, (52)
  *
  * (0), 1, 2, 3, 4, 7, 8, 9, 10, 11, 14, 17, 18, 19, 20, 23, 24, 25, 28, 31,
  * 32, 33, 34, 35, 38, 39, 42, 45, 46, 47, 49, (52)
  *
  * (0), 1, 2, 3, 4, 7, 8, 9, 10, 11, 14, 17, 18, 19, 20, 23, 24, 25, 28, 31,
  * 32, 33, 34, 35, 38, 39, 42, 45, 46, 48, 49, (52)
  *
  * (0), 1, 2, 3, 4, 7, 8, 9, 10, 11, 14, 17, 18, 19, 20, 23, 24, 25, 28, 31,
  * 32, 33, 34, 35, 38, 39, 42, 45, 46, 49, (52)
  *
  * (0), 1, 2, 3, 4, 7, 8, 9, 10, 11, 14, 17, 18, 19, 20, 23, 24, 25, 28, 31,
  * 32, 33, 34, 35, 38, 39, 42, 45, 47, 48, 49, (52)
  *
  * (0), 3, 4, 7, 10, 11, 14, 17, 20, 23, 25, 28, 31, 34, 35, 38, 39, 42, 45,
  * 46, 48, 49, (52)
  *
  * (0), 3, 4, 7, 10, 11, 14, 17, 20, 23, 25, 28, 31, 34, 35, 38, 39, 42, 45,
  * 46, 49, (52)
  *
  * (0), 3, 4, 7, 10, 11, 14, 17, 20, 23, 25, 28, 31, 34, 35, 38, 39, 42, 45,
  * 47, 48, 49, (52)
  *
  * (0), 3, 4, 7, 10, 11, 14, 17, 20, 23, 25, 28, 31, 34, 35, 38, 39, 42, 45,
  * 47, 49, (52)
  *
  * (0), 3, 4, 7, 10, 11, 14, 17, 20, 23, 25, 28, 31, 34, 35, 38, 39, 42, 45,
  * 48, 49, (52)
  * </pre>
  * In total, this set of adapters can connect the charging outlet to your device in 19208 distinct arrangements.
  *
  * You glance back down at your bag and try to remember why you brought so many adapters; there must be more than a
  * trillion valid ways to arrange them! Surely, there must be an efficient way to count the arrangements.
  *
  * <b>What is the total number of distinct ways you can arrange the adapters to connect the charging outlet to your device?</b>
  */
class Day10 {
  private lazy val input = readNumbers("/2020/input10.txt")

  def joltDiffs(adapters: Seq[Int]): Seq[Int] =
    adapters
      .:+(adapters.max + 3)
      .sorted
      .foldLeft(Seq.empty[Int])((result, value) =>
        value - result.head match {
          case 1 => Seq(value, 1 + result(1), result(2))
          case 3 => Seq(value, result(1), 1 + result(2))
          case _ => Seq(value, result(1), result(2))
        }
      )

  def joltProduct(adapters: Seq[Int]): Int = joltDiffs(adapters).product

  def distinctArrangements(adapters: Seq[Int]): Long = part2(adapters.+:(0).:+(adapters.max + 3).sorted)

  // copied from https://github.com/blu3r4y/AdventOfLanguages2020/blob/main/src/day10.scala
  def part2(jolts: Seq[Int]): Long = {
    val diffs = diff(jolts)
    val lengths = consecutive(diffs)
    val perms = lengths.map(permutations)
    perms.map(_.toLong).product
  }

  def consecutive(seq: Seq[Int]): Seq[Int] = for (values <- split(seq) if values.head == 1) yield values.length

  def permutations(n: Int): Int = if (n < 2) 1 else tribonacci(n + 2)

  def tribonacci(n: Int): Int = {
    n match {
      case 0 => 0
      case 1 => 0
      case 2 => 1
      case _ => tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3)
    }
  }

  def diff(seq: Seq[Int]): Seq[Int] = (seq.drop(1) zip seq).map(t => t._1 - t._2)

  def split(seq: Seq[Int]): List[List[Int]] = {
    // itertools.groupby in Scala (https://stackoverflow.com/a/4763086/927377)
    seq.foldRight(List[List[Int]]()) { (e, acc) =>
      acc match {
        case (`e` :: xs) :: fs => (e :: e :: xs) :: fs
        case _ => List(e) :: acc
      }
    }
  }

  // my accurate but time consuming solutions
  def distinctArrangements2(adapters: Seq[Int]): Long = {
    val max = adapters.max + 3
    val sortedAdapters = adapters.sorted.:+(max)
    arrange(sortedAdapters, max)
  }

  def arrange(jolts: Seq[Int], max: Int, adapter: Int = 0): Long = {
    if (adapter >= max) 1
    else {
      jolts.filter(j => j >= (adapter + 1) && j <= (adapter + 3)).map(next => arrange(jolts, max, next)).sum
    }
  }

  def isValidArrangement(codes: Seq[Int], index: Int = 0, value: Int = 0): Boolean = {
    if (index >= codes.length) true
    else
      codes(index) - value match {
        case 1 | 2 | 3 => isValidArrangement(codes, index + 1, codes(index))
        case _ => false
      }
  }

  def solvePart1: Long = joltProduct(input)

  def solvePart2: Long = distinctArrangements(input)
}
