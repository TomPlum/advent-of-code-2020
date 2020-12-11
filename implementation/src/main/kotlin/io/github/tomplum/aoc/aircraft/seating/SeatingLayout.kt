package io.github.tomplum.aoc.aircraft.seating

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

    fun occupy(positions: Set<Point2D>) = positions.forEach { addTile(it, SeatingPosition('#')) }

    fun evict(positions: Set<Point2D>) = positions.forEach { addTile(it, SeatingPosition('L')) }

    fun getOccupiedSeatPositions(): Set<Point2D> = filterTiles { it.isEmpty() }.filterKeys { pos ->
        pos.getFirstAdjacent().values.none { it.isOccupied() }
    }.keys

    fun getEmptySeatPositions(): Set<Point2D> = filterTiles { it.isOccupied() }.filterKeys { pos ->
        pos.getFirstAdjacent().values.count { it.isOccupied() } >= 5
    }.keys

    fun snapshot(): SeatingLayout {
        val snapshot = SeatingLayout(emptyList())
        filterTiles { true }.forEach { (pos, tile) -> snapshot.addTile(pos, tile) }
        return snapshot
    }

    fun getOccupiedSeatCount(): Int = filterTiles { it.isOccupied() }.count()

    private fun Point2D.getFirstAdjacent(): Map<Point2D, SeatingPosition> {
        return Direction.values().mapNotNull { direction -> getFirst(direction) }.toMap()
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