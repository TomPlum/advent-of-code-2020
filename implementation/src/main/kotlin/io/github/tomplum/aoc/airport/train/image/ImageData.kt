package io.github.tomplum.aoc.airport.train.image

import io.github.tomplum.libs.math.map.MapTile

data class ImageData(val chroming: Char): MapTile<Char>(chroming) {
    override fun toString(): String = chroming.toString()
}