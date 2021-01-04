package org.base.advent._2020

import org.base.advent.Reader._

/**
  * <b>Part 1</b>
  * As you look out the window and notice a heavily-forested continent slowly appear over the horizon, you are
  * interrupted by the child sitting next to you. They're curious if you could help them with their math homework.
  *
  * Unfortunately, it seems like this "math" follows different rules than you remember.
  *
  * The homework (your puzzle input) consists of a series of expressions that consist of addition (+),
  * multiplication (*), and parentheses ((...)). Just like normal math, parentheses indicate that the expression inside
  * must be evaluated before it can be used by the surrounding expression. Addition still finds the sum of the numbers
  * on both sides of the operator, and multiplication still finds the product.
  *
  * However, the rules of operator precedence have changed. Rather than evaluating multiplication before addition, the
  * operators have the same precedence, and are evaluated left-to-right regardless of the order in which they appear.
  *
  * For example, the steps to evaluate the expression 1 + 2 * 3 + 4 * 5 + 6 are as follows:
  * <pre>
  * 1 + 2 * 3 + 4 * 5 + 6
  *   3   * 3 + 4 * 5 + 6
  *       9   + 4 * 5 + 6
  *          13   * 5 + 6
  *              65   + 6
  *                  71
  * </pre>
  *
  * Parentheses can override this order; for example, here is what happens if parentheses are added to
  * form 1 + (2 * 3) + (4 * (5 + 6)):
  * <pre>
  * 1 + (2 * 3) + (4 * (5 + 6))
  * 1 +    6    + (4 * (5 + 6))
  *      7      + (4 * (5 + 6))
  *      7      + (4 *   11   )
  *      7      +     44
  *             51
  * </pre>
  *
  * Here are a few more examples:
  * <ul>
  *     <li>2 * 3 + (4 * 5) becomes 26.</li>
  *     <li>5 + (8 * 3 + 9 + 3 * 4 * 3) becomes 437.</li>
  *     <li>5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4)) becomes 12240.</li>
  *     <li>((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2 becomes 13632.</li>
  * </ul>
  *
  * Before you can help with the homework, you need to understand it yourself. <b>Evaluate the expression on each line
  * of the homework; what is the sum of the resulting values?</b>
  *
  * <b>Part 2</b>
  * You manage to answer the child's questions and they finish part 1 of their homework, but get stuck when they reach
  * the next section: advanced math.
  *
  * Now, addition and multiplication have different precedence levels, but they're not the ones you're familiar with.
  * Instead, addition is evaluated before multiplication.
  *
  * For example, the steps to evaluate the expression 1 + 2 * 3 + 4 * 5 + 6 are now as follows:
  * <pre>
  * 1 + 2 * 3 + 4 * 5 + 6
  * 3   * 3 + 4 * 5 + 6
  * 3   *   7   * 5 + 6
  * 3   *   7   *  11
  * 21       *  11
  * 231
  * </pre>
  *
  * Here are the other examples from above:
  * <ul>
  *     <li>1 + (2 * 3) + (4 * (5 + 6)) still becomes 51.</li>
  *     <li>2 * 3 + (4 * 5) becomes 46.</li>
  *     <li>5 + (8 * 3 + 9 + 3 * 4 * 3) becomes 1445.</li>
  *     <li>5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4)) becomes 669060.</li>
  *     <li>((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2 becomes 23340.</li>
  * </ul>
  *
  * <b>What do you get if you add up the results of evaluating the homework problems using these new rules?</b>
  */
class Day18 {
  private lazy val input = readLines("/2020/input18.txt")

  private val HasParens = "(.*)\\(([^)]+)\\)(.*)".r
  private val NextOp = "(.*)([+*]) (\\d+)".r
  private val Add = "(.+) + (.+)".r
  private val Multiply = "(.+) \\* (.+)".r

  type Math = String => Long

  def sumExpressions(expressions: Seq[String], math: Math): Long = expressions.map(math).sum

  def newMath(expr: String): Long = {
    expr match {
      case HasParens(before, middle, after) => newMath(before.concat(newMath(middle).toString).concat(after))
      case NextOp(rest, op, value) =>
        op match {
          case "+" => value.toLong + newMath(rest.trim)
          case "*" => value.toLong * newMath(rest.trim)
        }
      case _ => expr.toLong
    }
  }

  def advancedMath(expr: String): Long = {
    expr match {
      case HasParens(before, middle, after) => advancedMath(before.concat(advancedMath(middle).toString).concat(after))
      case Add(left, right) => advancedMath(left) + advancedMath(right)
      case Multiply(left, right) => advancedMath(left) * advancedMath(right)
      case NextOp(rest, op, value) =>
        op match {
          case "+" => value.toLong + advancedMath(rest.trim)
          case "*" => value.toLong * advancedMath(rest.trim)
        }
      case _ => expr.toLong
    }
  }

  def solvePart1: Long = sumExpressions(input, newMath)

  def solvePart2: Long = sumExpressions(input, advancedMath)
}
