package io.github.tomplum.aoc.island.lobby

import io.github.tomplum.libs.math.map.MapTile

data class FloorTile(private val colour: Char): MapTile<Char>(colour) {
    fun flip(): FloorTile = when {
        isWhite() -> black()
        isBlack() -> white()
        else -> throw IllegalArgumentException("Invalid Floor Tile: $colour")
    }

    fun isWhite(): Boolean = colour == 'W'

    fun isBlack(): Boolean = colour == 'B'

    companion object {
        fun white() = FloorTile('W')
        fun black() = FloorTile('B')
    }

    override fun toString(): String = colour.toString()
}