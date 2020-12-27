package io.github.tomplum.aoc.airport.train.image

import io.github.tomplum.libs.math.map.AdventMap
import io.github.tomplum.libs.math.map.AdventMap2D
import io.github.tomplum.libs.math.point.Point2D

class Image(private val width: Int): AdventMap2D<ImageSection>() {
    private val tiles = mutableListOf<MutableList<ImageTile>>()

    fun addSection(pos: Point2D, tile: ImageTile) = addTile(pos, ImageSection(tile))
}