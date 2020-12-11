package io.github.tomplum.aoc.aircraft.seating.strategy

import io.github.tomplum.aoc.aircraft.seating.SeatingLayout
import io.github.tomplum.libs.math.Point2D

class RevisedSeatingStrategy : SeatingStrategy {
    override fun getOccupiedSeatPositions(layout: SeatingLayout): Set<Point2D> {
        return layout.getSeats { it.isEmpty() }.filterKeys { pos ->
            layout.getFirstAdjacent(pos).values.none { it.isOccupied() }
        }.keys
    }

    override fun getEmptySeatPositions(layout: SeatingLayout): Set<Point2D> {
        return layout.getSeats { it.isOccupied() }.filterKeys { pos ->
            layout.getFirstAdjacent(pos).values.count { it.isOccupied() } >= 5
        }.keys
    }
}