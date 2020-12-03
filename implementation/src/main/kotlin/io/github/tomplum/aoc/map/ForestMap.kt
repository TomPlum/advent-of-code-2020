package io.github.tomplum.aoc.map

import io.github.tomplum.aoc.extensions.product
import io.github.tomplum.libs.logging.AdventLogger
import io.github.tomplum.libs.map.AdventMap2D
import io.github.tomplum.libs.math.Point2D

class ForestMap(data: List<String>) : AdventMap2D<ForestTile>() {
    init {
        var x = 0
        var y = 0
        data.forEach { row ->
            row.forEach { column ->
                addTile(Point2D(x, y), ForestTile(column))
                x++
            }
            x = 0
            y++
        }
        AdventLogger.info(this)
    }

    fun trackTobogganTrajectory(slopes: List<SlopeTrajectory>): Long = slopes.map { slopeTrajectory ->
        var currentPosition = Point2D(0, 0)
        var treesEncountered = 0L
        while (currentPosition.y <= yMax()) {
            AdventLogger.debug("Toboggan Position: $currentPosition")
            val tile = getForestTile(currentPosition)
            if (tile.isTree()) treesEncountered++
            currentPosition = slopeTrajectory.apply(currentPosition)
        }
        treesEncountered
    }.product()

    private fun getForestTile(pos: Point2D): ForestTile {
        return if (hasRecorded(pos)) {
            getTile(pos)
        } else {
            if (pos.y > yMax()) throw IllegalArgumentException("$pos is off the map!")
            val width = xMax() + 1
            getTile(Point2D(pos.x % width, pos.y))
        }
    }
}