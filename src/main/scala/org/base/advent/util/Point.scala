package org.base.advent.util

case class Point(x: Int, y: Int) {
  def up(delta: Int = 1): Point = move(0, delta)
  def down(delta: Int = 1): Point = move(0, -delta)
  def left(delta: Int = 1): Point = move(-delta, 0)
  def right(delta: Int = 1): Point = move(delta, 0)

  def move(dx: Int, dy: Int): Point = Point(x + dx, y + dy)

  def cardinal: Seq[Point] = Seq(up(), right(), down(), left())

  def surrounding(dist: Int = 1): Seq[Point] = {
    val (up1, right1, down1, left1): (Point, Point, Point, Point) = (up(dist), right(dist), down(dist), left(dist))
    Seq(up1, up1.right(dist), right1, right1.down(dist), down1, down1.left(dist), left1, left1.up(dist))
  }

  override def toString: String = s"[$x,$y]"
}

object Point {
  def apply(x: Int = 0, y: Int = 0) = new Point(x, y)
}
