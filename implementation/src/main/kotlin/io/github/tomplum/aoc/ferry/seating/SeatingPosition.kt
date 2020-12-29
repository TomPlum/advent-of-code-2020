package io.github.tomplum.aoc.ferry.seating

import io.github.tomplum.libs.math.map.MapTile

/**
 * A single seating position in a [SeatingLayout].
 * @param status The status of the position.
 */
class SeatingPosition(private val status: Char): MapTile<Char>(status) {
    /**
     * Checks if the position has no seat and subsequently is just floor space.
     * Seats cannot move. Therefore, a floor position will always remain so.
     * @return true if the position is floor space, else false.
     */
    fun isFloor(): Boolean = status == '.'

    /**
     * Checks if the position has a seat and is empty.
     * @return true if the seat is empty, else false.
     */
    fun isEmpty(): Boolean = status == 'L'

    /**
     * Checks if the position has a seat and is currently occupied.
     * @return true if the seat is occupied, else false.
     */
    fun isOccupied(): Boolean = status == '#'

    /**
     * Static factory constructors for creating seating positions types.
     */
    companion object {
        fun floor() = SeatingPosition('.')
        fun occupied() = SeatingPosition('#')
        fun empty() = SeatingPosition('L')
    }
}