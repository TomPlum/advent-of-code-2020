package io.github.tomplum.aoc.airport.train.image

import io.github.tomplum.libs.math.map.MapTile

data class ImageTileData(val chroming: Char): MapTile<Char>(chroming) {

    fun isWave(): Boolean = chroming == '#'

    fun isSeaMonster(): Boolean = chroming == 'O'

    fun toBinary(): Char = if (chroming == '#') '1' else '0'

    override fun toString(): String = chroming.toString()

}