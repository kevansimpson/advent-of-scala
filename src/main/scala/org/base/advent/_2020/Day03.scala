package org.base.advent._2020

import org.base.advent.Reader._
import org.base.advent.util.{Grid, Point}

/**
  * <b>Part 1</b>
  * With the toboggan login problems resolved, you set off toward the airport. While travel by toboggan might be easy,
  * it's certainly not safe: there's very minimal steering and the area is covered in trees. You'll need to see which
  * angles will take you near the fewest trees.
  *
  * Due to the local geology, trees in this area only grow on exact integer coordinates in a grid. You make a map
  * (your puzzle input) of the open squares (.) and trees (#) you can see. For example:
  * <pre>
  * ..##.......
  * #...#...#..
  * .#....#..#.
  * ..#.#...#.#
  * .#...##..#.
  * ..#.##.....
  * .#.#.#....#
  * .#........#
  * #.##...#...
  * #...##....#
  * .#..#...#.#
  * </pre>
  *
  * These aren't the only trees, though; due to something you read about once involving arboreal genetics and biome
  * stability, the same pattern repeats to the right many times:
  * <pre>
  * ..##.........##.........##.........##.........##.........##.......  --->
  * #...#...#..#...#...#..#...#...#..#...#...#..#...#...#..#...#...#..
  * .#....#..#..#....#..#..#....#..#..#....#..#..#....#..#..#....#..#.
  * ..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#
  * .#...##..#..#...##..#..#...##..#..#...##..#..#...##..#..#...##..#.
  * ..#.##.......#.##.......#.##.......#.##.......#.##.......#.##.....  --->
  * .#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#
  * .#........#.#........#.#........#.#........#.#........#.#........#
  * #.##...#...#.##...#...#.##...#...#.##...#...#.##...#...#.##...#...
  * #...##....##...##....##...##....##...##....##...##....##...##....#
  * .#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#  --->
  * </pre>
  *
  * You start on the open square (.) in the top-left corner and need to reach the bottom
  * (below the bottom-most row on your map).
  *
  * The toboggan can only follow a few specific slopes (you opted for a cheaper model that prefers rational numbers);
  * start by counting all the trees you would encounter for the slope right 3, down 1:
  *
  * From your starting position at the top-left, check the position that is right 3 and down 1. Then, check the position
  * that is right 3 and down 1 from there, and so on until you go past the bottom of the map.
  *
  * The locations you'd check in the above example are marked here with O where there was an open square and X where
  * there was a tree:
  * <pre>
  * ..##.........##.........##.........##.........##.........##.......  --->
  * #..O#...#..#...#...#..#...#...#..#...#...#..#...#...#..#...#...#..
  * .#....X..#..#....#..#..#....#..#..#....#..#..#....#..#..#....#..#.
  * ..#.#...#O#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#
  * .#...##..#..X...##..#..#...##..#..#...##..#..#...##..#..#...##..#.
  * ..#.##.......#.X#.......#.##.......#.##.......#.##.......#.##.....  --->
  * .#.#.#....#.#.#.#.O..#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#
  * .#........#.#........X.#........#.#........#.#........#.#........#
  * #.##...#...#.##...#...#.X#...#...#.##...#...#.##...#...#.##...#...
  * #...##....##...##....##...#X....##...##....##...##....##...##....#
  * .#..#...#.#.#..#...#.#.#..#...X.#.#..#...#.#.#..#...#.#.#..#...#.#  --->
  * </pre>
  * In this example, traversing the map using this slope would cause you to encounter 7 trees.
  *
  * Starting at the top-left corner of your map and following a slope of right 3 and down 1,
  * how many trees would you encounter?
  *
  * <b>Part 2</b>
  * Time to check the rest of the slopes - you need to minimize the probability of a sudden arboreal stop, after all.
  *
  * Determine the number of trees you would encounter if, for each of the following slopes, you start at the top-left
  * corner and traverse the map all the way to the bottom:
  * <ul>
  *     <li>Right 1, down 1.</li>
  *     <li>Right 3, down 1. (This is the slope you already checked.)</li>
  *     <li>Right 5, down 1.</li>
  *     <li>Right 7, down 1.</li>
  *     <li>Right 1, down 2.</li>
  * </ul>
  * In the above example, these slopes would find 2, 7, 3, 4, and 2 tree(s) respectively; multiplied together, these
  * produce the answer 336.
  *
  * What do you get if you multiply together the number of trees encountered on each of the listed slopes?
  */
class Day03 {
  private lazy val input = readLines("/2020/input03.txt")
  private lazy val theForest = Grid(input)
  private lazy val slopes = Seq((1, 1), (3, 1), (5, 1), (7, 1), (1, 2))

  def compareSlopes(forest: Grid): Long = slopes.foldLeft(1L)(_ * tobogganTrees(forest, _))

  def tobogganTrees(forest: Grid, slope: (Int, Int), point: Point = Point.ORIGIN, treesHit: Long = 0L): Long = {
    if (point.y >= forest.height) treesHit
    else {
      val next = point.move(slope._1, slope._2)
      val hit = if (isTree(forest, next.x % forest.width, next.y % forest.height)) 1 else 0
      tobogganTrees(forest, slope, next, treesHit + hit)
    }
  }

  def isTree(forest: Grid, x: Int, y: Int): Boolean = "#".equals(forest.get(x, y))

  def solvePart1: Long = tobogganTrees(theForest, (3, 1))

  def solvePart2: Long = compareSlopes(theForest)
}
