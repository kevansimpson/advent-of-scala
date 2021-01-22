package org.base.advent.util

case class Grid(area: Array[Array[String]], height: Int, width: Int) {

  def get(x: Int, y: Int): String = row(y)(x)

  def get(pt: Point): String = get(pt.x, pt.y)

  def row(y: Int): Array[String] = area(y)

  def column(x: Int): Array[String] = (0 until height).map(row).map(_(x)).toArray

  def in(pt: Point): Boolean = pt.x >= 0 && pt.x < width && pt.y >= 0 && pt.y < height

  def count(fxn: String => Boolean): Int = area.flatMap(_.toList).count(fxn)

  def translate(fxn: CLaw => String): Grid = {
    val grid = Array.ofDim[String](height, width)
    for ((row, y) <- area.zipWithIndex)
      for ((cell, x) <- row.zipWithIndex) grid.apply(y).update(x, fxn(CLaw(this, x, y, cell)))
    new Grid(grid, height, width)
  }

  def show(): Unit = {
    println(s"h: $height, w: $width")
    for (row <- area) {
      for (cell <- row) print(cell)
      println("")
    }
  }
}

object Grid {
  def apply(rows: Seq[String]): Grid = {
    val height = rows.length
    val width = rows.head.length
    val grid = Array.ofDim[String](height, width)
    rows.zipWithIndex.foreach {
      case (row, y) => row.split("").zipWithIndex.foreach { case (col, x) => grid(y).update(x, col) }
    }

    Grid(grid, height, width)
  }
}

// Conway's Law helper class
case class CLaw(grid: Grid, x: Int, y: Int, cell: String) {
  def surrounding(dist: Int = 1): Seq[Point] = Point(x, y).surrounding(dist).filter(grid.in)
}
