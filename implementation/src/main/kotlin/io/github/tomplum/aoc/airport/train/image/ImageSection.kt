package io.github.tomplum.aoc.airport.train.image

import io.github.tomplum.libs.math.map.MapTile
import io.github.tomplum.libs.math.point.Point2D

class ImageSection(private val tile: ImageTile): MapTile<ImageTile>(tile) {
    fun trim(): ImageSection {
        val trimmedTile = tile.removeEdges()
        return ImageSection(trimmedTile)
    }

    fun getWidth(): Int = tile.width

    fun getData(position: Point2D): ImageTileData = tile.getData(position)
}