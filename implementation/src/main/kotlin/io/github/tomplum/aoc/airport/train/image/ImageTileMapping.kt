package io.github.tomplum.aoc.airport.train.image

import io.github.tomplum.libs.math.map.AdventMap2D
import io.github.tomplum.libs.math.point.Point2D

class ImageTileMapping: AdventMap2D<ImageSection>() {
    fun addSection(pos: Point2D, tile: ImageTile) = addTile(pos, ImageSection(tile))

    fun trimSectionsForAssembly(): ImageTileMapping {
        data.keys.forEach { position ->
            val tile = getTile(position)
            val trimmed = tile.trim()
            addTile(position, trimmed)
        }
        return this
    }

    fun getRows(): List<Collection<ImageSection>> = (0..(yMax() ?: 0)).map { y ->
        data.filter { (pos, _) -> pos.y == y }.toSortedMap(compareBy { pos -> pos.x }).values
    }

    fun getSectionWidth(): Int = getTile(Point2D.origin()).tile.width

}