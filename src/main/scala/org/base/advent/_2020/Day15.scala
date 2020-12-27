package org.base.advent._2020

import scala.annotation.tailrec

/**
  * <b>Part 1</b>
  * You catch the airport shuttle and try to book a new flight to your vacation island. Due to the storm, all direct
  * flights have been cancelled, but a route is available to get around the storm. You take it.
  *
  * While you wait for your flight, you decide to check in with the Elves back at the North Pole. They're playing a
  * memory game and are ever so excited to explain the rules!
  *
  * In this game, the players take turns saying numbers. They begin by taking turns reading from a list of starting
  * numbers (your puzzle input). Then, each turn consists of considering the most recently spoken number:
  * <ul>
  *     <li>If that was the first time the number has been spoken, the current player says 0.</li>
  *     <li>Otherwise, the number had been spoken before; the current player announces how many turns apart the number
  *         is from when it was previously spoken.</li>
  * </ul>
  *
  * So, after the starting numbers, each turn results in that player speaking aloud either 0 (if the last number is new)
  * or an age (if the last number is a repeat).
  *
  * For example, suppose the starting numbers are 0,3,6:
  * <ul>
  *     <li>Turn 1: The 1st number spoken is a starting number, 0.</li>
  *     <li>Turn 2: The 2nd number spoken is a starting number, 3.</li>
  *     <li>Turn 3: The 3rd number spoken is a starting number, 6.</li>
  *     <li>Turn 4: Now, consider the last number spoken, 6. Since that was the first time the number had been spoken,
  *         the 4th number spoken is 0.</li>
  *     <li>Turn 5: Next, again consider the last number spoken, 0. Since it had been spoken before, the next number to
  *         speak is the difference between the turn number when it was last spoken (the previous turn, 4) and the turn
  *         number of the time it was most recently spoken before then (turn 1). Thus, the 5th number spoken is 4 - 1, 3.</li>
  *     <li>Turn 6: The last number spoken, 3 had also been spoken before, most recently on turns 5 and 2.
  *         So, the 6th number spoken is 5 - 2, 3.</li>
  *     <li>Turn 7: Since 3 was just spoken twice in a row, and the last two turns are 1 turn apart, the 7th number spoken is 1.</li>
  *     <li>Turn 8: Since 1 is new, the 8th number spoken is 0.</li>
  *     <li>Turn 9: 0 was last spoken on turns 8 and 4, so the 9th number spoken is the difference between them, 4.</li>
  *     <li>Turn 10: 4 is new, so the 10th number spoken is 0.</li>
  * </ul>
  *
  * (The game ends when the Elves get sick of playing or dinner is ready, whichever comes first.)
  *
  * Their question for you is: what will be the 2020th number spoken? In the example above, the 2020th number spoken will be 436.
  *
  * Here are a few more examples:
  * <ul>
  *     <li>Given the starting numbers 1,3,2, the 2020th number spoken is 1.</li>
  *     <li>Given the starting numbers 2,1,3, the 2020th number spoken is 10.</li>
  *     <li>Given the starting numbers 1,2,3, the 2020th number spoken is 27.</li>
  *     <li>Given the starting numbers 2,3,1, the 2020th number spoken is 78.</li>
  *     <li>Given the starting numbers 3,2,1, the 2020th number spoken is 438.</li>
  *     <li>Given the starting numbers 3,1,2, the 2020th number spoken is 1836.</li>
  * </ul>
  *
  * Given your starting numbers, <b>what will be the 2020th number spoken?</b>
  *
  * <b>Part 2</b>
  */
class Day15 {
  private val input = Seq(9, 3, 1, 0, 8, 4)

  def memoryGameGaveUp(numbers: Seq[Int]): Int = {
    val map: Map[Int, (Int, Int)] =
      numbers.zipWithIndex.map(num => num._1 -> ((num._2, -1))).toMap.removed(numbers.last)
    val spoken = (numbers.size - 1 to 2020 - map.size).foldLeft((map, numbers.last))((memory, turn) => {
      val (mem, lastSpoken) = memory
      val last2seen = mem.getOrElse(lastSpoken, (-1, -1))
      val next = if (last2seen._1 < 0) (turn, -1) else (turn, last2seen._1)
      // println(s"$last2seen ===> $next ===> $lastSpoken")
      (mem + (lastSpoken -> next), if (last2seen._1 < 0) 0 else next._1 - next._2)
    })

    spoken._2
  }

  // more math; shrug
  // thanks to https://github.com/konkit/AdventOfCode2020/blob/master/15/src/main/scala/tech/konkit/adventofcode/FifteenTwo.scala
  def memoryGame(queue: Seq[Int], target: Int = 2020): Int = {
    runIteration(
      isFirstRun = true,
      currentElement = ValueWithIndex(queue.last, queue.length - 1),
      map = queue.zipWithIndex.map { case (value, index) => value -> List(index) }.toMap,
      targetCount = target,
      count = queue.length
    )
  }

  @tailrec
  private def runIteration(
      isFirstRun: Boolean,
      currentElement: ValueWithIndex,
      map: Map[Int, List[Int]],
      targetCount: Int,
      count: Int
  ): Int = {
//    if (count % 5000000 == 0) {
//      println(s"Queue length: $count")
//    }

    if (count == targetCount) {
      currentElement.value
    } else {
      if (!isFirstRun && elementExists(map, currentElement)) {
        val newMap = updatedMap(count, map, currentElement)
        val newValue = getDifferenceOfPreviousTwoValues(newMap, currentElement)
        val newElement = ValueWithIndex(newValue, count)

        runIteration(isFirstRun = false, newElement, newMap, targetCount, count + 1)
      } else {
        val newMap = updatedMap(count, map, currentElement)
        val newElement = ValueWithIndex(0, count)

        runIteration(isFirstRun = false, newElement, newMap, targetCount, count + 1)
      }
    }
  }

  private def updatedMap(queueLength: Int, map: Map[Int, List[Int]], currentElement: ValueWithIndex) = {
    val newList = (queueLength - 1) :: map.getOrElse(currentElement.value, List.empty)
    map + (currentElement.value -> newList.take(2))
  }

  private def elementExists(map: Map[Int, List[Int]], currentElement: ValueWithIndex) =
    map.contains(currentElement.value)

  private def getDifferenceOfPreviousTwoValues(map: Map[Int, List[Int]], currentElement: ValueWithIndex) = {
    val twoPreviousIndices = map(currentElement.value).take(2)
    if (twoPreviousIndices.length == 2) {
      twoPreviousIndices.head - twoPreviousIndices.last
    } else {
      twoPreviousIndices.head
    }
  }

  case class ValueWithIndex(value: Int, index: Int)

  def solvePart1: Long = memoryGame(input)

  def solvePart2: Long = memoryGame(input, 30000000)
}
