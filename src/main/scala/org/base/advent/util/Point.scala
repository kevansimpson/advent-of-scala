package org.base.advent.util

case class Point(x: Int, y: Int) {

  def up(delta: Int = 1): Point = move(0, delta)
  def down(delta: Int = 1): Point = move(0, -delta)
  def left(delta: Int = 1): Point = move(-delta, 0)
  def right(delta: Int = 1): Point = move(delta, 0)

  def move(dx: Int = 1, dy: Int = 1): Point = Point(x + dx, y + dy)

  def moveDir(direction: String, delta: Int = 1): Point = direction match {
    case "U" | "N" => up(delta)
    case "R" | "E" => right(delta)
    case "D" | "S" => down(delta)
    case "L" | "W" => left(delta)
    case _ => this
  }

  def cardinal: Seq[Point] = Seq(up(), right(), down(), left())

  def manhattanDistance: Int = Point.manhattan(this)

  def surrounding(dist: Int = 1): Seq[Point] = {
    val (up1, right1, down1, left1): (Point, Point, Point, Point) = (up(dist), right(dist), down(dist), left(dist))
    Seq(up1, up1.right(dist), right1, right1.down(dist), down1, down1.left(dist), left1, left1.up(dist))
  }

  override def toString: String = s"[$x,$y]"
}

object Point {
  final val ORIGIN = Point(0, 0)

  def apply(x: Int, y: Int) = new Point(x, y)

  def manhattan(pt1: Point, pt2: Point = ORIGIN): Int = (pt1.x - pt2.x).abs + (pt1.y - pt2.y).abs
}
