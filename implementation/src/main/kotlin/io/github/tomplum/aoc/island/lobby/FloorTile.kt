package io.github.tomplum.aoc.island.lobby

import io.github.tomplum.libs.math.map.MapTile

/**
 * A single floor tile in a [LobbyFloor].
 * @param colour The colour of the tile. Black or White.
 */
data class FloorTile(private val colour: Char): MapTile<Char>(colour) {
    /**
     * Flips the tile over to the other colour.
     * Tiles are black on on side and white on the other.
     * @throws IllegalArgumentException if the floor tile has an invalid colour.
     * @return The tile in its new state.
     */
    fun flip(): FloorTile = when {
        isWhite() -> black()
        isBlack() -> white()
        else -> throw IllegalArgumentException("Invalid Floor Tile: $colour")
    }

    /**
     * Checks if the tile has its white side facing up.
     * @return true if white is up, else false.
     */
    fun isWhite(): Boolean = colour == 'W'

    /**
     * Checks if the tile has its black side facing up.
     * @return true if black is up, else false.
     */
    fun isBlack(): Boolean = colour == 'B'

    companion object {
        /**
         * Creates a tile with the white side facing up.
         */
        fun white() = FloorTile('W')

        /**
         * Creates a tile with the black side facing up.
         */
        fun black() = FloorTile('B')
    }

    override fun toString(): String = colour.toString()
}