package io.github.tomplum.aoc.ferry.seating.strategy

import io.github.tomplum.aoc.ferry.seating.SeatingLayout
import io.github.tomplum.libs.math.Point2D
import java.util.stream.Collectors

class InitialSeatingStrategy : SeatingStrategy {
    override fun getOccupiedSeatPositions(layout: SeatingLayout): Set<Point2D> {
        return layout.getSeats { it.isEmpty() }.keys.parallelStream().filter { pos ->
            layout.getAdjacentSeating(pos).values.none { it!!.isOccupied() }
        }.collect(Collectors.toSet())
    }

    override fun getEmptySeatPositions(layout: SeatingLayout): Set<Point2D> {
        return layout.getSeats { it.isOccupied() }.keys.parallelStream().filter { pos ->
            layout.getAdjacentSeating(pos).values.count { it!!.isOccupied() } >= 4
        }.collect(Collectors.toSet())
    }
}