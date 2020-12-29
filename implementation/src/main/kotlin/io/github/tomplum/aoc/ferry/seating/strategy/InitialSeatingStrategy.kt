package io.github.tomplum.aoc.ferry.seating.strategy

import io.github.tomplum.aoc.ferry.seating.SeatingLayout
import io.github.tomplum.libs.math.point.Point2D
import java.util.stream.Collectors

/**
 * The first model of [SeatingStrategy].
 *
 * This strategy considers adjacent seats to be the eight seats immediately surrounding the target seat.
 * @see Point2D.adjacent
 *
 * If a seat is empty and there are no occupied seats adjacent to it, the seat becomes occupied.
 * If a seat is occupied and four or more seats adjacent to it are also occupied, the seat becomes empty.
 * Otherwise, the seat's state does not change.
 */
class InitialSeatingStrategy : SeatingStrategy {
    override fun getOccupiedSeatPositions(layout: SeatingLayout): Set<Point2D> {
        return layout.getSeats { seat -> seat.isEmpty() }.keys.parallelStream().filter { pos ->
            layout.getAdjacentSeating(pos).values.none { seat -> seat!!.isOccupied() }
        }.collect(Collectors.toSet())
    }

    override fun getEmptySeatPositions(layout: SeatingLayout): Set<Point2D> {
        return layout.getSeats { seat -> seat.isOccupied() }.keys.parallelStream().filter { pos ->
            layout.getAdjacentSeating(pos).values.count { seat -> seat!!.isOccupied() } >= 4
        }.collect(Collectors.toSet())
    }
}