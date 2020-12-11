package io.github.tomplum.aoc.ferry.seating.strategy

import io.github.tomplum.aoc.ferry.seating.SeatingLayout
import io.github.tomplum.libs.math.Point2D

class InitialSeatingStrategy : SeatingStrategy {
    override fun getOccupiedSeatPositions(layout: SeatingLayout): Set<Point2D> {
        return layout.getSeats { it.isEmpty() }.filterKeys { pos ->
            val adjacentPositions = pos.adjacent().toSet()
            val adjacentSeating = layout.getSeats(adjacentPositions)
            adjacentSeating.values.none { it.isOccupied() }
        }.keys
    }

    override fun getEmptySeatPositions(layout: SeatingLayout): Set<Point2D> {
        return layout.getSeats { it.isOccupied() }.filterKeys { pos ->
            val adjacentPositions = pos.adjacent().toSet()
            val adjacentSeating = layout.getSeats(adjacentPositions)
            adjacentSeating.values.count { it.isOccupied() } >= 4
        }.keys
    }
}