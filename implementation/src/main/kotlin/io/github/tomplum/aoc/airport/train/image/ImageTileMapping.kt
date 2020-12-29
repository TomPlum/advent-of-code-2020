package io.github.tomplum.aoc.airport.train.image

import io.github.tomplum.libs.math.map.AdventMap2D
import io.github.tomplum.libs.math.point.Point2D

/**
 * Maps [ImageTile] objects together in with their edges correctly aligned.
 * The tiles are stored in a cartesian grid.
 */
class ImageTileMapping: AdventMap2D<ImageSection>() {

    /**
     * Adds a single section of the image to the mapping.
     * @param position The position on the mapping to place the tile.
     * @param tile The section of the image to add.
     */
    fun addSection(position: Point2D, tile: ImageTile) = addTile(position, ImageSection(tile))

    /**
     * Removes the edges from all the tiles in the mapping.
     * Each tile is treated individually and trimmed, then put back together.
     * @return The trimmed mapping.
     */
    fun trimSectionsForAssembly(): ImageTileMapping {
        data.keys.forEach { position ->
            val tile = getTile(position)
            val trimmed = tile.trim()
            addTile(position, trimmed)
        }
        return this
    }

    /**
     * Retrieves a list of rows of tiles. Each row is a sub-list of the tiles in that row.
     * @return A list of row sub-lists of tiles.
     */
    fun getRows(): List<Collection<ImageSection>> = (0..yMax()!!).map { y ->
        data.filter { (pos, _) -> pos.y == y }.toSortedMap(compareBy { pos -> pos.x }).values
    }

    /**
     * Retrieves the width of one of the mapped sections.
     * @see ImageTile.width
     * @return The width of the section at the origin.
     */
    fun getSectionWidth(): Int = getTile(Point2D.origin()).getWidth()

}