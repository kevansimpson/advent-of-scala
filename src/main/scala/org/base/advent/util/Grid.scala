package org.base.advent.util

case class Grid(area: Array[Array[String]], height: Int, width: Int) {

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
      case (row, y) => row.split("").zipWithIndex.foreach { case (col, x) => grid.apply(y).update(x, col) }
    }

    Grid(grid, height, width)
  }
}
