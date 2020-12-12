package io.github.tomplum.aoc.ferry.seating.strategy

import io.github.tomplum.aoc.ferry.seating.SeatingLayout
import io.github.tomplum.libs.math.Point2D

class InitialSeatingStrategy : SeatingStrategy {
    override fun getOccupiedSeatPositions(layout: SeatingLayout): Set<Point2D> {
        return layout.getSeats { it.isEmpty() }.filterKeys { pos ->
            layout.getAdjacentSeating(pos).values.none { it!!.isOccupied() }
        }.keys
    }

    override fun getEmptySeatPositions(layout: SeatingLayout): Set<Point2D> {
        return layout.getSeats { it.isOccupied() }.filterKeys { pos ->
            layout.getAdjacentSeating(pos).values.count { it!!.isOccupied() } >= 4
        }.keys
    }
}