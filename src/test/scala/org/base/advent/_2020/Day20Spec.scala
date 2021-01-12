package org.base.advent._2020

import org.base.advent.AdventSpec
import org.base.advent._2020.Day20Spec.Environment
import org.base.advent.util.Util._
import org.mockito.scalatest.IdiomaticMockito

object Day20Spec extends IdiomaticMockito {
  trait Environment {
    val day = new Day20
    val example1: Seq[String] = split("""Tile 2311:
                                        |..##.#..#.
                                        |##..#.....
                                        |#...##..#.
                                        |####.#...#
                                        |##.##.###.
                                        |##...#.###
                                        |.#.#.#..##
                                        |..#....#..
                                        |###...#.#.
                                        |..###..###
                                        |
                                        |Tile 1951:
                                        |#.##...##.
                                        |#.####...#
                                        |.....#..##
                                        |#...######
                                        |.##.#....#
                                        |.###.#####
                                        |###.##.##.
                                        |.###....#.
                                        |..#.#..#.#
                                        |#...##.#..
                                        |
                                        |Tile 1171:
                                        |####...##.
                                        |#..##.#..#
                                        |##.#..#.#.
                                        |.###.####.
                                        |..###.####
                                        |.##....##.
                                        |.#...####.
                                        |#.##.####.
                                        |####..#...
                                        |.....##...
                                        |
                                        |Tile 1427:
                                        |###.##.#..
                                        |.#..#.##..
                                        |.#.##.#..#
                                        |#.#.#.##.#
                                        |....#...##
                                        |...##..##.
                                        |...#.#####
                                        |.#.####.#.
                                        |..#..###.#
                                        |..##.#..#.
                                        |
                                        |Tile 1489:
                                        |##.#.#....
                                        |..##...#..
                                        |.##..##...
                                        |..#...#...
                                        |#####...#.
                                        |#..#.#.#.#
                                        |...#.#.#..
                                        |##.#...##.
                                        |..##.##.##
                                        |###.##.#..
                                        |
                                        |Tile 2473:
                                        |#....####.
                                        |#..#.##...
                                        |#.##..#...
                                        |######.#.#
                                        |.#...#.#.#
                                        |.#########
                                        |.###.#..#.
                                        |########.#
                                        |##...##.#.
                                        |..###.#.#.
                                        |
                                        |Tile 2971:
                                        |..#.#....#
                                        |#...###...
                                        |#.#.###...
                                        |##.##..#..
                                        |.#####..##
                                        |.#..####.#
                                        |#..#.#..#.
                                        |..####.###
                                        |..#.#.###.
                                        |...#.#.#.#
                                        |
                                        |Tile 2729:
                                        |...#.#.#.#
                                        |####.#....
                                        |..#.#.....
                                        |....#..#.#
                                        |.##..##.#.
                                        |.#.####...
                                        |####.#.#..
                                        |##.####...
                                        |##..#.##..
                                        |#.##...##.
                                        |
                                        |Tile 3079:
                                        |#.#.#####.
                                        |.#..######
                                        |..#.......
                                        |######....
                                        |####.#..#.
                                        |.#...#.##.
                                        |#.#####.##
                                        |..#.###...
                                        |..#.......
                                        |..#.###...""".stripMargin)
  }
}

class Day20Spec extends AdventSpec {
  "Day20" when {
    "practicing examples" should {
      "make image" in new Environment {
        day.makeImage(example1) shouldEqual 20899048083289L
      }
      "find sea monsters" in new Environment {
//        day.foo shouldEqual 1138
      }
    }

    "solving" should {
      "answer part 1" in new Environment {
        day.solvePart1 shouldEqual 21599955909991L
      }
      "answer part 2" in new Environment {
//        day.solvePart2 shouldEqual 130933530
      }
    }
  }
}
