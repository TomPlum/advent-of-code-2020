package io.github.tomplum.aoc.airport.train.image

import io.github.tomplum.libs.math.map.MapTile

class ImageSection(val tile: ImageTile): MapTile<ImageTile>(tile) {
    fun trim() = ImageSection(tile.removeEdges())
}