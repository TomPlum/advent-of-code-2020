package io.github.tomplum.aoc.airport.train.image

import io.github.tomplum.libs.math.map.MapTile
import io.github.tomplum.libs.math.point.Point2D

/**
 * A single section in an [Image] wrapping the underlying [ImageTile].
 * @param tile The section of the image.
 */
class ImageSection(private val tile: ImageTile): MapTile<ImageTile>(tile) {
    /**
     * Removes the edges from the [tile].
     * @return The trimmed tile.
     */
    fun trim(): ImageSection {
        val trimmedTile = tile.removeEdges()
        return ImageSection(trimmedTile)
    }

    /**
     * Retrieves the width of the [tile].
     * @see [ImageTile.width]
     * @return The width of tile.
     */
    fun getWidth(): Int = tile.width

    /**
     * Retrieves a single pixel from the [tile].
     * @param position The position in the [ImageTile].
     * @return The [ImageTileData] from the tile.
     */
    fun getData(position: Point2D): ImageTileData = tile.getData(position)
}