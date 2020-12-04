package org.base.advent.util

class Point(val x: Int, val y: Int) {
  def up(delta: Int): Point = move(0, delta)
  def down(delta: Int): Point = move(0, -delta)
  def left(delta: Int): Point = move(-delta, 0)
  def right(delta: Int): Point = move(delta, 0)

  def move(dx: Int, dy: Int): Point = new Point(x + dx, y + dy)

  def cardinal: Seq[Point] = Seq(up(1), right(1), down(1), left(1))

  override def toString: String = s"[$x,$y]"
}
