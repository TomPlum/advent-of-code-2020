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

    fun getSeats(isType: (SeatingPosition) -> Boolean): Map<Point2D, SeatingPosition> = filterTiles { isType(it) }

    fun getAdjacentSeating(position: Point2D) = adjacentTiles(setOf(position), null).filterValues { seat -> seat != null }

    fun occupy(positions: Set<Point2D>) = positions.forEach { pos -> addTile(pos, SeatingPosition.occupied()) }

    fun evict(positions: Set<Point2D>) = positions.forEach { pos -> addTile(pos, SeatingPosition.empty()) }

    fun snapshotCurrentState(): SeatingLayout {
        val snapshot = SeatingLayout(emptyList())
        snapshot.overwriteData(getDataMap())
        return snapshot
    }

    fun getOccupiedSeatCount(): Int = filterTiles { seat -> seat.isOccupied() }.count()

    fun getFirstAdjacent(pos: Point2D): Map<Point2D, SeatingPosition> {
        return Direction.values().mapNotNull { direction -> pos.getFirst(direction) }.toMap()
    }

    private fun Point2D.getFirst(direction: Direction): Pair<Point2D, SeatingPosition>? {
        var pos = this.shift(direction)
        var candidate = getTile(pos, SeatingPosition.floor())
        while (candidate.isFloor()) {
            if (!hasRecorded(pos)) return null
            pos = pos.shift(direction)
            candidate = getTile(pos, SeatingPosition.floor())
        }
        return Pair(pos, candidate)
    }

}