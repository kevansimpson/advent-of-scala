package org.base.advent._2020

import org.base.advent.Reader._

/**
  * <b>Part 1</b>
  * As your flight approaches the regional airport where you'll switch to a much larger plane, customs declaration
  * forms are distributed to the passengers.
  *
  * The form asks a series of 26 yes-or-no questions marked a through z. All you need to do is identify the questions
  * for which anyone in your group answers "yes". Since your group is just you, this doesn't take very long.
  *
  * However, the person sitting next to you seems to be experiencing a language barrier and asks if you can help.
  * For each of the people in their group, you write down the questions for which they answer "yes", one per line.
  * For example:
  * <pre>
  * abcx
  * abcy
  * abcz
  * </pre>
  * In this group, there are 6 questions to which anyone answered "yes": a, b, c, x, y, and z. (Duplicate answers to
  * the same question don't count extra; each question counts at most once.)
  *
  * Another group asks for your help, then another, and eventually you've collected answers from every group on the
  * plane (your puzzle input). Each group's answers are separated by a blank line, and within each group, each person's
  * answers are on a single line. For example:
  * <pre>
  * abc
  *
  * a
  * b
  * c
  *
  * ab
  * ac
  *
  * a
  * a
  * a
  * a
  *
  * b
  * </pre>
  * This list represents answers from five groups:
  * <ul>
  *     <li>The first group contains one person who answered "yes" to 3 questions: a, b, and c.</li>
  *     <li>The second group contains three people; combined, they answered "yes" to 3 questions: a, b, and c.</li>
  *     <li>The third group contains two people; combined, they answered "yes" to 3 questions: a, b, and c.</li>
  *     <li>The fourth group contains four people; combined, they answered "yes" to only 1 question, a.</li>
  *     <li>The last group contains one person who answered "yes" to only 1 question, b.</li>
  * </ul>
  * In this example, the sum of these counts is 3 + 3 + 3 + 1 + 1 = 11.
  *
  * For each group, count the number of questions to which anyone answered "yes". What is the sum of those counts?
  *
  * <b>Part 2</b>
  * As you finish the last group's customs declaration, you notice that you misread one word in the instructions:
  *
  * You don't need to identify the questions to which anyone answered "yes"; you need to identify the questions to
  * which everyone answered "yes"!
  *
  * Using the same example as above:
  * <pre>
  * abc
  *
  * a
  * b
  * c
  *
  * ab
  * ac
  *
  * a
  * a
  * a
  * a
  *
  * b
  * </pre>
  *
  * This list represents answers from five groups:
  * <ul>
  *     <li>In the first group, everyone (all 1 person) answered "yes" to 3 questions: a, b, and c.</li>
  *     <li>In the second group, there is no question to which everyone answered "yes".</li>
  *     <li>In the third group, everyone answered yes to only 1 question, a. Since some people did not answer "yes"
  *         to b or c, they don't count.</li>
  *     <li>In the fourth group, everyone answered yes to only 1 question, a.</li>
  *     <li>In the fifth group, everyone (all 1 person) answered "yes" to 1 question, b.</li>
  * </ul>
  * In this example, the sum of these counts is 3 + 0 + 1 + 1 + 1 = 6.
  *
  * For each group, count the number of questions to which everyone answered "yes". What is the sum of those counts?
  */
class Day06 {
  private lazy val input = readLines("/2020/input06.txt")
  private lazy val letters = ('a' to 'z').map(_.toString).toSet

  def sumQuestions(lines: Seq[String], answers: Seq[String] => Long): Long = {
    if (lines.isEmpty) 0L
    else {
      val span = lines.span(!_.isEmpty)
      answers(span._1) + sumQuestions(span._2.drop(1), answers)
    }
  }

  def anyAnswer(answers: Seq[String]): Long = answers.foldLeft(Set.empty[String])(_ concat _.split("")).size

  def everyAnswer(answers: Seq[String]): Long = answers.foldLeft(letters)(_ intersect _.split("").toSet).size

  def solvePart1: Long = sumQuestions(input, anyAnswer)

  def solvePart2: Long = sumQuestions(input, everyAnswer)
}
