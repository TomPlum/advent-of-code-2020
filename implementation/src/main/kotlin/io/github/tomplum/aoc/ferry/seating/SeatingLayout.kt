package io.github.tomplum.aoc.ferry.seating

import io.github.tomplum.libs.math.Direction
import io.github.tomplum.libs.math.Point2D
import io.github.tomplum.libs.math.map.AdventMap2D

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

    fun getSeats(positions: Set<Point2D>): Map<Point2D, SeatingPosition> = filterPoints(positions)

    fun occupy(positions: Set<Point2D>) = positions.forEach { addTile(it, SeatingPosition('#')) }

    fun evict(positions: Set<Point2D>) = positions.forEach { addTile(it, SeatingPosition('L')) }

    fun snapshot(): SeatingLayout {
        val snapshot = SeatingLayout(emptyList())
        filterTiles { true }.forEach { (pos, tile) -> snapshot.addTile(pos, tile) }
        return snapshot
    }

    fun getOccupiedSeatCount(): Int = filterTiles { it.isOccupied() }.count()

    fun getFirstAdjacent(pos: Point2D): Map<Point2D, SeatingPosition> {
        return Direction.values().mapNotNull { direction -> pos.getFirst(direction) }.toMap()
    }

    private fun Point2D.getFirst(direction: Direction): Pair<Point2D, SeatingPosition>? {
        var pos = this.shift(direction)
        var candidate = getTile(pos, SeatingPosition('.'))
        while (candidate.isFloor()) {
            if (!hasRecorded(pos)) return null
            pos = pos.shift(direction)
            candidate = getTile(pos, SeatingPosition('.'))
        }
        return Pair(pos, candidate)
    }

}