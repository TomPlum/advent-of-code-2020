package io.github.tomplum.aoc.aircraft.seating

import io.github.tomplum.aoc.map.ForestTile
import io.github.tomplum.libs.logging.AdventLogger
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
        val adjacentPositions = pos.diagonalAdjacent()
        val adjacentSeating = filterPoints(adjacentPositions)
        return@filterKeys adjacentSeating.values.none { it.isOccupied() }
    }.keys

    fun getEmptySeatPositions(): Set<Point2D> = filterTiles { it.isOccupied() }.filterKeys { pos ->
        val adjacentPositions = pos.diagonalAdjacent()
        val adjacentSeating = filterPoints(adjacentPositions)
        return@filterKeys adjacentSeating.values.count { it.isOccupied() } >= 4
    }.keys

    fun snapshot(): SeatingLayout {
        val snapshot = SeatingLayout(emptyList())
        filterTiles { true }.forEach { (pos, tile) -> snapshot.addTile(pos, tile) }
        return snapshot
    }

    fun getOccupiedSeatCount(): Int = filterTiles { it.isOccupied() }.count()


    //TODO: Move to Point2D in libs
    fun Point2D.diagonalAdjacent() = setOf(
        Point2D(x, y + 1), Point2D(x + 1, y), Point2D(x, y - 1), Point2D(x - 1, y), //Orthogonal
        Point2D(x - 1, y - 1), Point2D(x + 1, y - 1), Point2D(x + 1, y + 1), Point2D(x - 1, y + 1)
    )
}