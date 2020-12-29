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
    override fun getOccupiedSeatPositions(layout: SeatingLayout): List<Point2D> {
        return layout.getSeats { seat -> seat.isEmpty() }.parallelStream().filter { position ->
            layout.getFirstAdjacent(position).none { seat -> seat.isOccupied() }
        }.collect(Collectors.toList())
    }

    override fun getEmptySeatPositions(layout: SeatingLayout): List<Point2D> {
        return layout.getSeats { seat -> seat.isOccupied() }.parallelStream().filter { position ->
            layout.getFirstAdjacent(position).count { seat -> seat.isOccupied() } >= 5
        }.collect(Collectors.toList())
    }
}