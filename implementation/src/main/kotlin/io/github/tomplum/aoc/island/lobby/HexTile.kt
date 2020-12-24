package io.github.tomplum.aoc.island.lobby

import io.github.tomplum.libs.math.map.MapTile

data class HexTile(val colour: Char): MapTile<Char>(colour) {
    fun flip(): HexTile = when {
        isWhite() -> black()
        isBlack() -> white()
        else -> throw IllegalArgumentException("$this has no colour. Is it recorded?")
    }

    fun isWhite(): Boolean = colour == 'W'

    fun isBlack(): Boolean = colour == 'B'

    companion object {
        fun white() = HexTile('W')
        fun black() = HexTile('B')
    }

    override fun toString(): String = colour.toString()
}