package io.github.tomplum.aoc.ferry.seating.strategy

import io.github.tomplum.aoc.ferry.seating.SeatingLayout
import io.github.tomplum.libs.math.point.Point2D
import java.util.stream.Collectors

/**
 * The revised [SeatingStrategy] with the correct adjacency logic.
 *
 * This strategy considers adjacent seats to be the eight seats of which can be seen from the target seat.
 * One cannot see through empty or occupied seats, only floor space.
 * @see Point2D.adjacent
 *
 * The same default rules apply from the [InitialSeatingStrategy].
 */
class RevisedSeatingStrategy : SeatingStrategy {
    override fun getOccupiedSeatPositions(layout: SeatingLayout): Set<Point2D> {
        return layout.getSeats { it.isEmpty() }.keys.parallelStream().filter { pos ->
            layout.getFirstAdjacent(pos).values.none { it.isOccupied() }
        }.collect(Collectors.toSet())
    }

    override fun getEmptySeatPositions(layout: SeatingLayout): Set<Point2D> {
        return layout.getSeats { it.isOccupied() }.keys.parallelStream().filter { pos ->
            layout.getFirstAdjacent(pos).values.count { it.isOccupied() } >= 5
        }.collect(Collectors.toSet())
    }
}