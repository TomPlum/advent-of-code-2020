package io.github.tomplum.aoc.airport.train.image

import io.github.tomplum.libs.math.Point2D
import io.github.tomplum.libs.math.map.AdventMap2D


class Image(private val width: Int): AdventMap2D<ImageTile>() {
    fun addImageTile(pos: Point2D, tile: ImageTile) = addTile(pos, tile)

    override fun toString(): String {
        val yMin = yMin() ?: 0
        val yMax = yMax() ?: 0
        val xMin = xMin() ?: 0
        val xMax = xMax() ?: 0
        val gap = (0..width).joinToString("") { " " }
        val emptyTile = (0..width).joinToString("\n") { gap }
        return (yMin..yMax).joinToString("\n") { y ->
            (xMin..xMax).joinToString(" ") { x ->
                snapshot().getOrDefault(Point2D(x, y), emptyTile).toString()
            }
        }.plus("\n")
    }
}