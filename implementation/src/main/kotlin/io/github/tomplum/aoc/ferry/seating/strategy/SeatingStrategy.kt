package io.github.tomplum.aoc.ferry.seating.strategy

import io.github.tomplum.aoc.ferry.seating.SeatingLayout
import io.github.tomplum.libs.math.Point2D

/**
 * A strategy defining the rules of how people to choose (or abandon) their seat in a [SeatingLayout].
 * The rules are applied to every seat in the layout simultaneously.
 */
interface SeatingStrategy {
    /**
     * Finds the seats that would become occupied after employing the strategy once.
     * @return The positions of the seats.
     */
    fun getOccupiedSeatPositions(layout: SeatingLayout): Set<Point2D>

    /**
     * Finds the seats that would become empty after employing the strategy once.
     * @return The positions of the seats.
     */
    fun getEmptySeatPositions(layout: SeatingLayout): Set<Point2D>
}