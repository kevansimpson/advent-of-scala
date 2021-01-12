package org.base.advent._2020

import org.base.advent.Reader._
import org.base.advent.util.Grid
import org.base.advent.util.Util._

/**
  * <b>Part 1</b>
  * The high-speed train leaves the forest and quickly carries you south. You can even see a desert in the distance!
  * Since you have some spare time, you might as well see if there was anything interesting in the image the Mythical
  * Information Bureau satellite captured.
  *
  * After decoding the satellite messages, you discover that the data actually contains many small images created by
  * the satellite's camera array. The camera array consists of many cameras; rather than produce a single square image,
  * they produce many smaller square image tiles that need to be reassembled back into a single image.
  *
  * Each camera in the camera array returns a single monochrome image tile with a random unique ID number. The tiles
  * (your puzzle input) arrived in a random order.
  *
  * Worse yet, the camera array appears to be malfunctioning: each image tile has been rotated and flipped to a random
  * orientation. Your first task is to reassemble the original image by orienting the tiles so they fit together.
  *
  * To show how the tiles should be reassembled, each tile's image data includes a border that should line up exactly
  * with its adjacent tiles. All tiles have this border, and the border lines up exactly when the tiles are both
  * oriented correctly. Tiles at the edge of the image also have this border, but the outermost edges won't line up
  * with any other tiles.
  *
  * For example, suppose you have the following nine tiles:
  * <pre>
  * Tile 2311:
  * ..##.#..#.
  * ##..#.....
  * #...##..#.
  * ####.#...#
  * ##.##.###.
  * ##...#.###
  * .#.#.#..##
  * ..#....#..
  * ###...#.#.
  * ..###..###
  *
  * Tile 1951:
  * #.##...##.
  * #.####...#
  * .....#..##
  * #...######
  * .##.#....#
  * .###.#####
  * ###.##.##.
  * .###....#.
  * ..#.#..#.#
  * #...##.#..
  *
  * Tile 1171:
  * ####...##.
  * #..##.#..#
  * ##.#..#.#.
  * .###.####.
  * ..###.####
  * .##....##.
  * .#...####.
  * #.##.####.
  * ####..#...
  * .....##...
  *
  * Tile 1427:
  * ###.##.#..
  * .#..#.##..
  * .#.##.#..#
  * #.#.#.##.#
  * ....#...##
  * ...##..##.
  * ...#.#####
  * .#.####.#.
  * ..#..###.#
  * ..##.#..#.
  *
  * Tile 1489:
  * ##.#.#....
  * ..##...#..
  * .##..##...
  * ..#...#...
  * #####...#.
  * #..#.#.#.#
  * ...#.#.#..
  * ##.#...##.
  * ..##.##.##
  * ###.##.#..
  *
  * Tile 2473:
  * #....####.
  * #..#.##...
  * #.##..#...
  * ######.#.#
  * .#...#.#.#
  * .#########
  * .###.#..#.
  * ########.#
  * ##...##.#.
  * ..###.#.#.
  *
  * Tile 2971:
  * ..#.#....#
  * #...###...
  * #.#.###...
  * ##.##..#..
  * .#####..##
  * .#..####.#
  * #..#.#..#.
  * ..####.###
  * ..#.#.###.
  * ...#.#.#.#
  *
  * Tile 2729:
  * ...#.#.#.#
  * ####.#....
  * ..#.#.....
  * ....#..#.#
  * .##..##.#.
  * .#.####...
  * ####.#.#..
  * ##.####...
  * ##..#.##..
  * #.##...##.
  *
  * Tile 3079:
  * #.#.#####.
  * .#..######
  * ..#.......
  * ######....
  * ####.#..#.
  * .#...#.##.
  * #.#####.##
  * ..#.###...
  * ..#.......
  * ..#.###...
  * </pre>
  *
  * By rotating, flipping, and rearranging them, you can find a square arrangement that causes all adjacent borders to
  * line up:
  * <pre>
  * #...##.#.. ..###..### #.#.#####.
  * ..#.#..#.# ###...#.#. .#..######
  * .###....#. ..#....#.. ..#.......
  * ###.##.##. .#.#.#..## ######....
  * .###.##### ##...#.### ####.#..#.
  * .##.#....# ##.##.###. .#...#.##.
  * #...###### ####.#...# #.#####.##
  * .....#..## #...##..#. ..#.###...
  * #.####...# ##..#..... ..#.......
  * #.##...##. ..##.#..#. ..#.###...
  *
  * #.##...##. ..##.#..#. ..#.###...
  * ##..#.##.. ..#..###.# ##.##....#
  * ##.####... .#.####.#. ..#.###..#
  * ####.#.#.. ...#.##### ###.#..###
  * .#.####... ...##..##. .######.##
  * .##..##.#. ....#...## #.#.#.#...
  * ....#..#.# #.#.#.##.# #.###.###.
  * ..#.#..... .#.##.#..# #.###.##..
  * ####.#.... .#..#.##.. .######...
  * ...#.#.#.# ###.##.#.. .##...####
  *
  * ...#.#.#.# ###.##.#.. .##...####
  * ..#.#.###. ..##.##.## #..#.##..#
  * ..####.### ##.#...##. .#.#..#.##
  * #..#.#..#. ...#.#.#.. .####.###.
  * .#..####.# #..#.#.#.# ####.###..
  * .#####..## #####...#. .##....##.
  * ##.##..#.. ..#...#... .####...#.
  * #.#.###... .##..##... .####.##.#
  * #...###... ..##...#.. ...#..####
  * ..#.#....# ##.#.#.... ...##.....
  * </pre>
  * For reference, the IDs of the above tiles are:
  * <pre>
  * 1951    2311    3079
  * 2729    1427    2473
  * 2971    1489    1171
  * </pre>
  *
  * To check that you've assembled the image correctly, multiply the IDs of the four corner tiles together. If you do
  * this with the assembled tiles from the example above, you get 1951 * 3079 * 2971 * 1171 = 20899048083289.
  *
  * Assemble the tiles into an image. <b>What do you get if you multiply together the IDs of the four corner tiles?</b>
  *
  * <b>Part 2</b>
  * Now, you're ready to check the image for sea monsters.
  *
  * The borders of each tile are not part of the actual image; start by removing them.
  *
  * In the example above, the tiles become:
  * <pre>
  * .#.#..#. ##...#.# #..#####
  * ###....# .#....#. .#......
  * ##.##.## #.#.#..# #####...
  * ###.#### #...#.## ###.#..#
  * ##.#.... #.##.### #...#.##
  * ...##### ###.#... .#####.#
  * ....#..# ...##..# .#.###..
  * .####... #..#.... .#......
  *
  * #..#.##. .#..###. #.##....
  * #.####.. #.####.# .#.###..
  * ###.#.#. ..#.#### ##.#..##
  * #.####.. ..##..## ######.#
  * ##..##.# ...#...# .#.#.#..
  * ...#..#. .#.#.##. .###.###
  * .#.#.... #.##.#.. .###.##.
  * ###.#... #..#.##. ######..
  *
  * .#.#.### .##.##.# ..#.##..
  * .####.## #.#...## #.#..#.#
  * ..#.#..# ..#.#.#. ####.###
  * #..####. ..#.#.#. ###.###.
  * #####..# ####...# ##....##
  * #.##..#. .#...#.. ####...#
  * .#.###.. ##..##.. ####.##.
  * ...###.. .##...#. ..#..###
  * </pre>
  * Remove the gaps to form the actual image:
  * <pre>
  * .#.#..#.##...#.##..#####
  * ###....#.#....#..#......
  * ##.##.###.#.#..######...
  * ###.#####...#.#####.#..#
  * ##.#....#.##.####...#.##
  * ...########.#....#####.#
  * ....#..#...##..#.#.###..
  * .####...#..#.....#......
  * #..#.##..#..###.#.##....
  * #.####..#.####.#.#.###..
  * ###.#.#...#.######.#..##
  * #.####....##..########.#
  * ##..##.#...#...#.#.#.#..
  * ...#..#..#.#.##..###.###
  * .#.#....#.##.#...###.##.
  * ###.#...#..#.##.######..
  * .#.#.###.##.##.#..#.##..
  * .####.###.#...###.#..#.#
  * ..#.#..#..#.#.#.####.###
  * #..####...#.#.#.###.###.
  * #####..#####...###....##
  * #.##..#..#...#..####...#
  * .#.###..##..##..####.##.
  * ...###...##...#...#..###
  * </pre>
  *
  * Now, you're ready to search for sea monsters! Because your image is monochrome, a sea monster will look like this:
  * <pre>
  *                   #
  * #    ##    ##    ###
  *  #  #  #  #  #  #
  * </pre>
  * When looking for this pattern in the image, the spaces can be anything; only the # need to match. Also, you might
  * need to rotate or flip your image before it's oriented correctly to find sea monsters. In the above image, after
  * flipping and rotating it to the appropriate orientation, there are two sea monsters (marked with O):
  * <pre>
  * .####...#####..#...###..
  * #####..#..#.#.####..#.#.
  * .#.#...#.###...#.##.O#..
  * #.O.##.OO#.#.OO.##.OOO##
  * ..#O.#O#.O##O..O.#O##.##
  * ...#.#..##.##...#..#..##
  * #.##.#..#.#..#..##.#.#..
  * .###.##.....#...###.#...
  * #.####.#.#....##.#..#.#.
  * ##...#..#....#..#...####
  * ..#.##...###..#.#####..#
  * ....#.##.#.#####....#...
  * ..##.##.###.....#.##..#.
  * #...#...###..####....##.
  * .#.##...#.##.#.#.###...#
  * #.###.#..####...##..#...
  * #.###...#.##...#.##O###.
  * .O##.#OO.###OO##..OOO##.
  * ..O#.O..O..O.#O##O##.###
  * #.#..##.########..#..##.
  * #.#####..#.#...##..#....
  * #....##..#.#########..##
  * #...#.....#..##...###.##
  * #..###....##.#...##.##.#
  * </pre>
  *
  * Determine how rough the waters are in the sea monsters' habitat by counting the number of # that are not part of a
  * sea monster. In the above example, the habitat's water roughness is 273.
  *
  * <b>How many # are not part of a sea monster?</b>
  */
class Day20 {
  private lazy val input = readLines("/2020/input20.txt")

  private val TileId = "Tile (\\d+):".r

  def makeImage(satScans: Seq[String]): Long = {
    val edgeMap = toEdgeMap(satScans)
    edgeMap // count the number of matching edges by tile id
      .map(idEdges => idEdges._1 -> idEdges._2.intersect(edgeMap.filter(_._1 != idEdges._1).flatMap(_._2).toSeq))
      .filter(_._2.size == 4) // corners have 2 matches each un/flipped
      .map(_._1.toLong)
      .product
  }

  def findMonsters(satScans: Seq[String]): Long = {
    val edgeMap = toEdgeMap(satScans)
    val xref = edgeMap.map(idEdges => {
      val (id, edgeList) = idEdges // (1489,List(848, 18, 948, 565, 43, 288, 183, 689))
      id -> edgeList.zipWithIndex
        .map(edgeIndex => {
          val (edge, index) = edgeIndex // (848, 0) - (18, 1) - (948, 2) - (565, 3)
          edgeMap
            .filter(em => em._1 != id && em._2.contains(edge))
            .foldLeft(Map.empty[Int, (Int, Int)])((result, next) =>
              result + (index -> ((next._1, next._2.indexOf(edge))))
            )
        })
        .filter(_.nonEmpty)
        .foldLeft(Map.empty[Int, (Int, Int)])((last, next) => last ++ next)
    })

    xref.foreach(println)
    println(xref.size)

    // filter side indices >= 4, corners only have two entries, convert ids to long prior to multiplication
    xref.map(idMap => idMap._1 -> idMap._2.filter(_._1 < 4)).filter(_._2.size == 2).keys.map(_.toLong).product
  }

  def toEdgeMap(images: Seq[String]): Map[Int, Seq[Long]] = {
    val tiles = readTiles(images)
    val edgeMap = tiles.map(idTile => {
      val edgeStr = edges(idTile._2)
      idTile._1 -> (edgeStr.map(bits2long) ++ edgeStr.map(_.reverse).map(bits2long))
    })
    edgeMap.foreach(println)
    edgeMap
  }

  def readTiles(images: Seq[String], tiles: Map[Int, Tile] = Map.empty[Int, Tile]): Map[Int, Tile] = {
    val (next, rest) = images.span(_.nonEmpty)
    val TileId(id) = next.head
    val newTiles = tiles + (id.toInt -> Tile(id.toInt, Grid(next.tail)))
    if (rest.isEmpty) newTiles else readTiles(rest.tail, newTiles)
  }

  def edges(tile: Tile): Seq[String] =
    Seq(
      tile.grid.row(0), // top
      tile.grid.column(tile.grid.width - 1), // right
      tile.grid.row(tile.grid.height - 1), // bottom
      tile.grid.column(0) // left
    ).map(_.mkString).map(_.replaceAll("\\.", "0").replaceAll("#", "1"))

  def solvePart1: Long = makeImage(input)

  def solvePart2: Long = makeImage(input)
}

case class Tile(id: Int, grid: Grid)
