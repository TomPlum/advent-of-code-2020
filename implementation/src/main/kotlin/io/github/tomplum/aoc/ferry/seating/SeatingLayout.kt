package io.github.tomplum.aoc.ferry.seating

import io.github.tomplum.aoc.ferry.navigation.Ferry
import io.github.tomplum.libs.math.Direction
import io.github.tomplum.libs.math.map.AdventMap2D
import io.github.tomplum.libs.math.point.Point2D

/**
 * A map of the seating layout in the waiting area of the [Ferry].
 * @param data A list of rows of seating data.
 */
class SeatingLayout(data: List<String>): AdventMap2D<Seat>() {
    init {
        var x = 0
        var y = 0
        data.forEach { row ->
            row.forEach { column ->
                addTile(Point2D(x, y), Seat(column))
                x++
            }
            x = 0
            y++
        }
    }

    /**
     * Finds all the seats that matching the given [predicate].
     * @param predicate A boolean predicate from the [Seat] class.
     * @return A list of the positions of seats matching the predicate.
     */
    fun getSeats(predicate: (Seat) -> Boolean): List<Point2D> = filterTiles { seat -> predicate(seat) }.keys.toList()

    /**
     * Finds all the seats that are adjacent to the given [position].
     * @param position The position of the seat to search around.
     * @return The seats adjacent to the given position.
     */
    fun getAdjacentSeating(position: Point2D): List<Seat> = position.adjacent()
        .map { pos -> getTile(pos, Seat.floor()) }

    /**
     * Sets the state of the seats at the given [positions] to occupied.
     * @param positions The positions of the seats to update.
     */
    fun occupy(positions: List<Point2D>) = positions.forEach { pos -> addTile(pos, Seat.occupied()) }

    /**
     * Sets the state of the seats at the given [positions] to empty.
     * @param positions The positions of the seats to update.
     */
    fun evict(positions: List<Point2D>) = positions.forEach { pos -> addTile(pos, Seat.empty()) }

    /**
     * Creates a copy of the current [SeatingLayout].
     * @return A copy of the layout in its current state.
     */
    fun snapshotCurrentState(): SeatingLayout {
        val snapshot = SeatingLayout(emptyList())
        snapshot.overwriteData(getDataMap().toMutableMap())
        return snapshot
    }

    /**
     * Finds all the seats that are current occupied.
     * @return The number of occupied seats.
     */
    fun getOccupiedSeatCount(): Int = filterTiles { seat -> seat.isOccupied() }.count()

    /**
     * Finds the eight adjacent seats that can be seen from the given seat [position].
     *
     * Instead of considering just the eight immediately adjacent seats, it finds the first seat in each
     * [Direction] that is NOT floor space. That is to say, the first occupied or empty seat in view.
     *
     * If the vision reaches the edge of the [SeatingLayout] without having found a seat, floor space is returned
     * for that direction.
     *
     * @param position The seating position to look around from.
     * @return A list of the first adjacent seat in every direction.
     */
    fun getFirstAdjacent(position: Point2D): List<Seat> {
        return Direction.values().map { direction -> position.getFirstSeat(direction) }
    }

    /**
     * Finds the first occupied or empty seating in the given [direction].
     * If a there is only floor space all the way to the edge of the [SeatingLayout], floor is returned.
     * @param direction The direction to look down from the current position.
     * @return The first seat seen, else floor space.
     */
    private fun Point2D.getFirstSeat(direction: Direction): Seat {
        var pos = this.shift(direction)
        var candidate = getTile(pos, Seat.floor())
        while (candidate.isFloor()) {
            if (!hasRecorded(pos)) return Seat.floor()
            pos = pos.shift(direction)
            candidate = getTile(pos, Seat.floor())
        }
        return candidate
    }

}