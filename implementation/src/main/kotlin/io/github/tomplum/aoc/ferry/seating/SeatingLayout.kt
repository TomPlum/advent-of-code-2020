package io.github.tomplum.aoc.ferry.seating

import io.github.tomplum.libs.math.Direction
import io.github.tomplum.libs.math.point.Point2D
import io.github.tomplum.libs.math.map.AdventMap2D
import io.github.tomplum.aoc.ferry.navigation.Ferry

/**
 * A map of the seating layout in the waiting area of the [Ferry].
 * @param data A list of rows of seating data.
 */
class SeatingLayout(data: List<String>): AdventMap2D<SeatingPosition>() {
    init {
        var x = 0
        var y = 0
        data.forEach { row ->
            row.forEach { column ->
                addTile(Point2D(x, y), SeatingPosition(column))
                x++
            }
            x = 0
            y++
        }
    }

    fun getSeats(predicate: (SeatingPosition) -> Boolean): List<Point2D> = filterTiles { seat -> predicate(seat) }.keys.toList()

    fun getAdjacentSeating(position: Point2D): List<SeatingPosition> = position.adjacent()
        .map { pos -> getTile(pos, SeatingPosition.floor()) }

    fun occupy(positions: List<Point2D>) = positions.forEach { pos -> addTile(pos, SeatingPosition.occupied()) }

    fun evict(positions: List<Point2D>) = positions.forEach { pos -> addTile(pos, SeatingPosition.empty()) }

    fun snapshotCurrentState(): SeatingLayout {
        val snapshot = SeatingLayout(emptyList())
        snapshot.overwriteData(getDataMap().toMutableMap())
        return snapshot
    }

    fun getOccupiedSeatCount(): Int = filterTiles { seat -> seat.isOccupied() }.count()

    fun getFirstAdjacent(pos: Point2D): List<SeatingPosition> {
        return Direction.values().map { direction -> pos.getFirstSeat(direction) }
    }

    private fun Point2D.getFirstSeat(direction: Direction): SeatingPosition {
        var pos = this.shift(direction)
        var candidate = getTile(pos, SeatingPosition.floor())
        while (candidate.isFloor()) {
            if (!hasRecorded(pos)) return SeatingPosition.floor()
            pos = pos.shift(direction)
            candidate = getTile(pos, SeatingPosition.floor())
        }
        return candidate
    }

}