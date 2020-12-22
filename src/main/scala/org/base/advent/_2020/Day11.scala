package org.base.advent._2020

import org.base.advent.Reader
import org.base.advent.util.{CLaw, Grid, Point}

/**
  * <b>Part 1</b>
  * Your plane lands with plenty of time to spare. The final leg of your journey is a ferry that goes directly to the
  * tropical island where you can finally start your vacation. As you reach the waiting area to board the ferry, you
  * realize you're so early, nobody else has even arrived yet!
  *
  * By modeling the process people use to choose (or abandon) their seat in the waiting area, you're pretty sure you
  * can predict the best place to sit. You make a quick map of the seat layout (your puzzle input).
  *
  * The seat layout fits neatly on a grid. Each position is either floor (.), an empty seat (L), or an occupied seat (#).
  * For example, the initial seat layout might look like this:
  * <pre>
  * L.LL.LL.LL
  * LLLLLLL.LL
  * L.L.L..L..
  * LLLL.LL.LL
  * L.LL.LL.LL
  * L.LLLLL.LL
  * ..L.L.....
  * LLLLLLLLLL
  * L.LLLLLL.L
  * L.LLLLL.LL
  * </pre>
  *
  * Now, you just need to model the people who will be arriving shortly. Fortunately, people are entirely predictable
  * and always follow a simple set of rules. All decisions are based on the number of occupied seats adjacent to a
  * given seat (one of the eight positions immediately up, down, left, right, or diagonal from the seat). The following
  * rules are applied to every seat simultaneously:
  * <ul>
  *     <li>If a seat is empty (L) and there are no occupied seats adjacent to it, the seat becomes occupied.</li>
  *     <li>If a seat is occupied (#) and four or more seats adjacent to it are also occupied, the seat becomes empty.</li>
  *     <li>Otherwise, the seat's state does not change.</li>
  * </ul>
  *
  * Floor (.) never changes; seats don't move, and nobody sits on the floor.
  *
  * After one round of these rules, every seat in the example layout becomes occupied:
  * <pre>
  * #.##.##.##
  * #######.##
  * #.#.#..#..
  * ####.##.##
  * #.##.##.##
  * #.#####.##
  * ..#.#.....
  * ##########
  * #.######.#
  * #.#####.##
  * </pre>
  *
  * After a second round, the seats with four or more occupied adjacent seats become empty again:
  * <pre>
  * #.LL.L#.##
  * #LLLLLL.L#
  * L.L.L..L..
  * #LLL.LL.L#
  * #.LL.LL.LL
  * #.LLLL#.##
  * ..L.L.....
  * #LLLLLLLL#
  * #.LLLLLL.L
  * #.#LLLL.##
  * </pre>
  *
  * This process continues for three more rounds:
  * <pre>
  * #.##.L#.##
  * #L###LL.L#
  * L.#.#..#..
  * #L##.##.L#
  * #.##.LL.LL
  * #.###L#.##
  * ..#.#.....
  * #L######L#
  * #.LL###L.L
  * #.#L###.##
  *
  * #.#L.L#.##
  * #LLL#LL.L#
  * L.L.L..#..
  * #LLL.##.L#
  * #.LL.LL.LL
  * #.LL#L#.##
  * ..L.L.....
  * #L#LLLL#L#
  * #.LLLLLL.L
  * #.#L#L#.##
  *
  * #.#L.L#.##
  * #LLL#LL.L#
  * L.#.L..#..
  * #L##.##.L#
  * #.#L.LL.LL
  * #.#L#L#.##
  * ..L.L.....
  * #L#L##L#L#
  * #.LLLLLL.L
  * #.#L#L#.##
  * </pre>
  *
  * At this point, something interesting happens: the chaos stabilizes and further applications of these rules cause no
  * seats to change state! Once people stop moving around, you count 37 occupied seats.
  *
  * Simulate your seating area by applying the seating rules repeatedly until no seats change state.
  * <b>How many seats end up occupied?</b>
  *
  * <b>Part 2</b>
  * As soon as people start to arrive, you realize your mistake. People don't just care about adjacent seats - they
  * care about the first seat they can see in each of those eight directions!
  *
  * Now, instead of considering just the eight immediately adjacent seats, consider the first seat in each of those
  * eight directions. For example, the empty seat below would see eight occupied seats:
  * <pre>
  * .......#.
  * ...#.....
  * .#.......
  * .........
  * ..#L....#
  * ....#....
  * .........
  * #........
  * ...#.....
  * </pre>
  * The leftmost empty seat below would only see one empty seat, but cannot see any of the occupied ones:
  * <pre>
  * .............
  * .L.L.#.#.#.#.
  * .............
  * </pre>
  * The empty seat below would see no occupied seats:
  * <pre>
  * .##.##.
  * #.#.#.#
  * ##...##
  * ...L...
  * ##...##
  * #.#.#.#
  * .##.##.
  * </pre>
  *
  * Also, people seem to be more tolerant than you expected: it now takes five or more visible occupied seats for an
  * occupied seat to become empty (rather than four or more from the previous rules). The other rules still apply:
  * empty seats that see no occupied seats become occupied, seats matching no rule don't change, and floor never changes.
  *
  * Given the same starting layout as above, these new rules cause the seating area to shift around as follows:
  * <pre>
  * L.LL.LL.LL
  * LLLLLLL.LL
  * L.L.L..L..
  * LLLL.LL.LL
  * L.LL.LL.LL
  * L.LLLLL.LL
  * ..L.L.....
  * LLLLLLLLLL
  * L.LLLLLL.L
  * L.LLLLL.LL
  * </pre>
  *
  * <pre>
  * #.##.##.##
  * #######.##
  * #.#.#..#..
  * ####.##.##
  * #.##.##.##
  * #.#####.##
  * ..#.#.....
  * ##########
  * #.######.#
  * #.#####.##
  * </pre>
  *
  * <pre>
  * #.LL.LL.L#
  * #LLLLLL.LL
  * L.L.L..L..
  * LLLL.LL.LL
  * L.LL.LL.LL
  * L.LLLLL.LL
  * ..L.L.....
  * LLLLLLLLL#
  * #.LLLLLL.L
  * #.LLLLL.L#
  * </pre>
  *
  * <pre>
  * #.L#.##.L#
  * #L#####.LL
  * L.#.#..#..
  * ##L#.##.##
  * #.##.#L.##
  * #.#####.#L
  * ..#.#.....
  * LLL####LL#
  * #.L#####.L
  * #.L####.L#
  * </pre>
  *
  * <pre>
  * #.L#.L#.L#
  * #LLLLLL.LL
  * L.L.L..#..
  * ##LL.LL.L#
  * L.LL.LL.L#
  * #.LLLLL.LL
  * ..L.L.....
  * LLLLLLLLL#
  * #.LLLLL#.L
  * #.L#LL#.L#
  * </pre>
  *
  * <pre>
  * #.L#.L#.L#
  * #LLLLLL.LL
  * L.L.L..#..
  * ##L#.#L.L#
  * L.L#.#L.L#
  * #.L####.LL
  * ..#.#.....
  * LLL###LLL#
  * #.LLLLL#.L
  * #.L#LL#.L#
  * </pre>
  *
  * <pre>
  * #.L#.L#.L#
  * #LLLLLL.LL
  * L.L.L..#..
  * ##L#.#L.L#
  * L.L#.LL.L#
  * #.LLLL#.LL
  * ..#.L.....
  * LLL###LLL#
  * #.LLLLL#.L
  * #.L#LL#.L#
  * </pre>
  *
  * Again, at this point, people stop shifting around and the seating area reaches equilibrium. Once this occurs,
  * you count 26 occupied seats.
  *
  * Given the new visibility method and the rule change for occupied seats becoming empty, once equilibrium is reached,
  * <b>how many seats end up occupied?</b>
  */
class Day11 extends Reader {
  private lazy val input = readLines("/2020/input11.txt")
  private lazy val theSeats = Grid(input)
  private lazy val deltas = Seq((0, -1), (1, -1), (1, 0), (1, 1), (0, 1), (-1, 1), (-1, 0), (-1, -1))

  def findSeats(seats: Grid, occupied: Int = 0, fxn: CLaw => String = look): Long = {
    val next = seats.translate(fxn)
    val seated = next.count("#".equals(_))
    if (seated == occupied) seated else findSeats(next, seated, fxn)
  }

  def look(claw: CLaw): String = {
    claw.cell match {
      case "." => "." // floor
      case "L" => if (claw.surrounding().map(claw.grid.get).count("#".equals) == 0) "#" else "L"
      case "#" => if (claw.surrounding().map(claw.grid.get).count("#".equals) >= 4) "L" else "#"
    }
  }

  def lookUntil(claw: CLaw): String = {
    claw.cell match {
      case "." => "." // floor
      case "L" => if (deltas.map(vector(claw, _)).count("#".equals) == 0) "#" else "L"
      case "#" => if (deltas.map(vector(claw, _)).count("#".equals) >= 5) "L" else "#"
    }
  }

  def around(claw: CLaw): Seq[String] = deltas.map(vector(claw, _))

  def vector(claw: CLaw, delta: (Int, Int), dist: Int = 1): String = {
    val (dx, dy) = (claw.x + (delta._1 * dist), claw.y + (delta._2 * dist))
    if (claw.grid.in(Point(dx, dy))) claw.grid.get(dx, dy) match {
      case "." => vector(claw, delta, dist + 1)
      case "L" => "L"
      case "#" => "#"
    }
    else "."
  }

  def solvePart1: Long = findSeats(theSeats)

  def solvePart2: Long = findSeats(theSeats, 0, lookUntil)
}
