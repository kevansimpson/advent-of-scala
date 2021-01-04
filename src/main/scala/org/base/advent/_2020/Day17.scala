package org.base.advent._2020

import org.base.advent.Reader._
import org.base.advent.util.MinMax
import org.base.advent.util.Util._

/**
  * <b>Part 1</b>
  * As your flight slowly drifts through the sky, the Elves at the Mythical Information Bureau at the North Pole
  * contact you. They'd like some help debugging a malfunctioning experimental energy source aboard one of their
  * super-secret imaging satellites.
  *
  * The experimental energy source is based on cutting-edge technology: a set of Conway Cubes contained in a pocket
  * dimension! When you hear it's having problems, you can't help but agree to take a look.
  *
  * The pocket dimension contains an infinite 3-dimensional grid. At every integer 3-dimensional coordinate (x,y,z),
  * there exists a single cube which is either active or inactive.
  *
  * In the initial state of the pocket dimension, almost all cubes start inactive. The only exception to this is a
  * small flat region of cubes (your puzzle input); the cubes in this region start in the specified active (#) or
  * inactive (.) state.
  *
  * The energy source then proceeds to boot up by executing six cycles.
  *
  * Each cube only ever considers its neighbors: any of the 26 other cubes where any of their coordinates differ by at
  * most 1. For example, given the cube at x=1,y=2,z=3, its neighbors include the cube at x=2,y=2,z=2, the cube
  * at x=0,y=2,z=3, and so on.
  *
  * During a cycle, all cubes simultaneously change their state according to the following rules:
  * <ul>
  *     <li>If a cube is active and exactly 2 or 3 of its neighbors are also active, the cube remains active.
  *         Otherwise, the cube becomes inactive.</li>
  *     <li>If a cube is inactive but exactly 3 of its neighbors are active, the cube becomes active.
  *         Otherwise, the cube remains inactive.</li>
  * </ul>
  *
  * The engineers responsible for this experimental energy source would like you to simulate the pocket dimension and
  * determine what the configuration of cubes should be at the end of the six-cycle boot process.
  *
  * For example, consider the following initial state:
  * <pre>
  * .#.
  * ..#
  * ###
  * </pre>
  * Even though the pocket dimension is 3-dimensional, this initial state represents a small 2-dimensional slice of it.
  * (In particular, this initial state defines a 3x3x1 region of the 3-dimensional space.)
  *
  * Simulating a few cycles from this initial state produces the following configurations, where the result of each
  * cycle is shown layer-by-layer at each given z coordinate (and the frame of view follows the active cells in each cycle):
  * <pre>
  * Before any cycles:
  *
  * z=0
  * .#.
  * ..#
  * ###
  *
  *
  * After 1 cycle:
  *
  * z=-1
  * #..
  * ..#
  * .#.
  *
  * z=0
  * #.#
  * .##
  * .#.
  *
  * z=1
  * #..
  * ..#
  * .#.
  *
  *
  * After 2 cycles:
  *
  * z=-2
  * .....
  * .....
  * ..#..
  * .....
  * .....
  *
  * z=-1
  * ..#..
  * .#..#
  * ....#
  * .#...
  * .....
  *
  * z=0
  * ##...
  * ##...
  * #....
  * ....#
  * .###.
  *
  * z=1
  * ..#..
  * .#..#
  * ....#
  * .#...
  * .....
  *
  * z=2
  * .....
  * .....
  * ..#..
  * .....
  * .....
  *
  *
  * After 3 cycles:
  *
  * z=-2
  * .......
  * .......
  * ..##...
  * ..###..
  * .......
  * .......
  * .......
  *
  * z=-1
  * ..#....
  * ...#...
  * #......
  * .....##
  * .#...#.
  * ..#.#..
  * ...#...
  *
  * z=0
  * ...#...
  * .......
  * #......
  * .......
  * .....##
  * .##.#..
  * ...#...
  *
  * z=1
  * ..#....
  * ...#...
  * #......
  * .....##
  * .#...#.
  * ..#.#..
  * ...#...
  *
  * z=2
  * .......
  * .......
  * ..##...
  * ..###..
  * .......
  * .......
  * .......
  * </pre>
  *
  * After the full six-cycle boot process completes, 112 cubes are left in the active state.
  *
  * Starting with your given initial configuration, simulate six cycles.
  * <b>How many cubes are left in the active state after the sixth cycle?</b>
  *
  * <b>Part 2</b>
  * For some reason, your simulated results don't match what the experimental energy source engineers expected.
  * Apparently, the pocket dimension actually has four spatial dimensions, not three.
  *
  * The pocket dimension contains an infinite 4-dimensional grid. At every integer 4-dimensional coordinate (x,y,z,w),
  * there exists a single cube (really, a hypercube) which is still either active or inactive.
  *
  * Each cube only ever considers its neighbors: any of the 80 other cubes where any of their coordinates differ by at
  * most 1. For example, given the cube at x=1,y=2,z=3,w=4, its neighbors include the cube at x=2,y=2,z=3,w=3, the cube
  * at x=0,y=2,z=3,w=4, and so on.
  *
  * The initial state of the pocket dimension still consists of a small flat region of cubes. Furthermore, the same
  * rules for cycle updating still apply: during each cycle, consider the number of active neighbors of each cube.
  *
  * For example, consider the same initial state as in the example above. Even though the pocket dimension
  * is 4-dimensional, this initial state represents a small 2-dimensional slice of it. (In particular, this initial
  * state defines a 3x3x1x1 region of the 4-dimensional space.)
  *
  * Simulating a few cycles from this initial state produces the following configurations, where the result of each
  * cycle is shown layer-by-layer at each given z and w coordinate:
  *
  * Before any cycles:
  * <pre>
  * Before any cycles:
  *
  * z=0, w=0
  * .#.
  * ..#
  * ###
  *
  *
  * After 1 cycle:
  *
  * z=-1, w=-1
  * #..
  * ..#
  * .#.
  *
  * z=0, w=-1
  * #..
  * ..#
  * .#.
  *
  * z=1, w=-1
  * #..
  * ..#
  * .#.
  *
  * z=-1, w=0
  * #..
  * ..#
  * .#.
  *
  * z=0, w=0
  * #.#
  * .##
  * .#.
  *
  * z=1, w=0
  * #..
  * ..#
  * .#.
  *
  * z=-1, w=1
  * #..
  * ..#
  * .#.
  *
  * z=0, w=1
  * #..
  * ..#
  * .#.
  *
  * z=1, w=1
  * #..
  * ..#
  * .#.
  *
  *
  * After 2 cycles:
  *
  * z=-2, w=-2
  * .....
  * .....
  * ..#..
  * .....
  * .....
  *
  * z=-1, w=-2
  * .....
  * .....
  * .....
  * .....
  * .....
  *
  * z=0, w=-2
  * ###..
  * ##.##
  * #...#
  * .#..#
  * .###.
  *
  * z=1, w=-2
  * .....
  * .....
  * .....
  * .....
  * .....
  *
  * z=2, w=-2
  * .....
  * .....
  * ..#..
  * .....
  * .....
  *
  * z=-2, w=-1
  * .....
  * .....
  * .....
  * .....
  * .....
  *
  * z=-1, w=-1
  * .....
  * .....
  * .....
  * .....
  * .....
  *
  * z=0, w=-1
  * .....
  * .....
  * .....
  * .....
  * .....
  *
  * z=1, w=-1
  * .....
  * .....
  * .....
  * .....
  * .....
  *
  * z=2, w=-1
  * .....
  * .....
  * .....
  * .....
  * .....
  *
  * z=-2, w=0
  * ###..
  * ##.##
  * #...#
  * .#..#
  * .###.
  *
  * z=-1, w=0
  * .....
  * .....
  * .....
  * .....
  * .....
  *
  * z=0, w=0
  * .....
  * .....
  * .....
  * .....
  * .....
  *
  * z=1, w=0
  * .....
  * .....
  * .....
  * .....
  * .....
  *
  * z=2, w=0
  * ###..
  * ##.##
  * #...#
  * .#..#
  * .###.
  *
  * z=-2, w=1
  * .....
  * .....
  * .....
  * .....
  * .....
  *
  * z=-1, w=1
  * .....
  * .....
  * .....
  * .....
  * .....
  *
  * z=0, w=1
  * .....
  * .....
  * .....
  * .....
  * .....
  *
  * z=1, w=1
  * .....
  * .....
  * .....
  * .....
  * .....
  *
  * z=2, w=1
  * .....
  * .....
  * .....
  * .....
  * .....
  *
  * z=-2, w=2
  * .....
  * .....
  * ..#..
  * .....
  * .....
  *
  * z=-1, w=2
  * .....
  * .....
  * .....
  * .....
  * .....
  *
  * z=0, w=2
  * ###..
  * ##.##
  * #...#
  * .#..#
  * .###.
  *
  * z=1, w=2
  * .....
  * .....
  * .....
  * .....
  * .....
  *
  * z=2, w=2
  * .....
  * .....
  * ..#..
  * .....
  * .....
  * </pre>
  *
  * After the full six-cycle boot process completes, 848 cubes are left in the active state.
  *
  * Starting with your given initial configuration, simulate six cycles in a 4-dimensional space.
  * <b>How many cubes are left in the active state after the sixth cycle?</b>
  */
class Day17 {
  private lazy val input = readLines("/2020/input17.txt")

  def simulate(rows: Seq[String]): Long = {
    show(toPoints(rows))
    (0 until 6).foldLeft(toPoints(rows))((points, _) => cycle(points)).size
  }

  def simulate4(rows: Seq[String]): Long = {
    (0 until 6).foldLeft(to4Points(rows))((points, _) => cycle4(points)).size
  }

  def cycle(points: Seq[P3]): Seq[P3] = {
    val edges = toEdges(points)
    (edges.zEdge.min - 1 to edges.zEdge.max + 1).flatMap(z => {
      (edges.yEdge.min - 1 to edges.yEdge.max + 1).flatMap(y => {
        (edges.xEdge.min - 1 to edges.xEdge.max + 1)
          .map(x => activate(P3(x, y, z), points, cube))
          .filter(pt => "#".equals(pt._2))
          .map(_._1)
      })
    })
  }

  def cycle4(points: Seq[P4]): Seq[P4] = {
    val edges = to4Edges(points)
    (edges.zEdge.min - 1 to edges.zEdge.max + 1).flatMap(z => {
      (edges.yEdge.min - 1 to edges.yEdge.max + 1).flatMap(y => {
        (edges.xEdge.min - 1 to edges.xEdge.max + 1).flatMap(x => {
          (edges.wEdge.min - 1 to edges.wEdge.max + 1)
            .map(w => activate(P4(x, y, z, w), points, cube4d))
            .filter(pt => "#".equals(pt._2))
            .map(_._1)
        })
      })
    })
  }

  def activate[A](here: A, points: Seq[A], surround: A => Seq[A]): (A, String) = {
    (here, surround(here).intersect(points).size match {
      case 3 => "#"
      case 2 => if (points.contains(here)) "#" else "."
      case _ => "."
    })
  }

  def show(points: Seq[P3]): Unit = {
    val edges = toEdges(points)
    (edges.zEdge.min to edges.zEdge.max).foreach(z => {
      println(s"z=$z")
      (edges.yEdge.min to edges.yEdge.max).foreach(y => {
        (edges.xEdge.min to edges.xEdge.max).foreach(x => {
          val here = P3(x, y, z)
          print(if (points.contains(here)) "#" else ".")
        })
        println()
      })
      println()
    })
  }

  def toEdges(points: Seq[P3]): Edges = Edges(minMax(points.map(_.x)), minMax(points.map(_.y)), minMax(points.map(_.z)))

  def to4Edges(points: Seq[P4]): Edges4 =
    Edges4(minMax(points.map(_.x)), minMax(points.map(_.y)), minMax(points.map(_.z)), minMax(points.map(_.w)))

  def cube(pt: P3): Seq[P3] = {
    (-1 to 1)
      .flatMap(z => (-1 to 1).flatMap(y => (-1 to 1).map(x => P3(pt.x + x, pt.y + y, pt.z + z))))
      .filter(!pt.equals(_))
  }

  def cube4d(pt: P4): Seq[P4] = {
    (-1 to 1)
      .flatMap(z =>
        (-1 to 1)
          .flatMap(y => (-1 to 1).flatMap(x => (-1 to 1).map(w => P4(pt.x + x, pt.y + y, pt.z + z, pt.w + w))))
          .filter(!pt.equals(_))
      )
  }

  def toPoints(rows: Seq[String]): Seq[P3] = {
    rows.zipWithIndex.flatMap(rowY => {
      val (row, y) = rowY
      row.split("").zipWithIndex.map(colX => if ("#".equals(colX._1)) P3(colX._2, y, 0) else null).filter(_ != null)
    })
  }

  def to4Points(rows: Seq[String]): Seq[P4] = toPoints(rows).map(pt => P4(pt.x, pt.y, pt.z, 0))

  def solvePart1: Long = simulate(input)

  def solvePart2: Long = simulate4(input)

  case class P3(x: Int, y: Int, z: Int)
  case class P4(x: Int, y: Int, z: Int, w: Int)

  trait BaseEdge {
    def xEdge: MinMax[Int]
    def yEdge: MinMax[Int]
    def zEdge: MinMax[Int]
  }

  case class Edges(xEdge: MinMax[Int], yEdge: MinMax[Int], zEdge: MinMax[Int]) extends BaseEdge

  case class Edges4(xEdge: MinMax[Int], yEdge: MinMax[Int], zEdge: MinMax[Int], wEdge: MinMax[Int]) extends BaseEdge
}
