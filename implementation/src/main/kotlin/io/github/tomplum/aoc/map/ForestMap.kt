package io.github.tomplum.aoc.map

import io.github.tomplum.aoc.extensions.product
import io.github.tomplum.libs.logging.AdventLogger
import io.github.tomplum.libs.map.AdventMap2D
import io.github.tomplum.libs.math.Point2D

/**
 * Due to the local geology, trees in this area only grow on exact integer coordinates in a grid.
 * These aren't the only trees, though; due to something you read about once involving arboreal genetics and biome
 * stability, the same pattern repeats to the right many times.
 */
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

    /**
     * Tracks the number of trees encountered during the toboggans descent for each of the given [slopes].
     * @return The cumulative product of all of the trees encountered.
     */
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

    /**
     * Gets the tile from the map at the given [position].
     * Should the x-ordinate lie outside the mapped tiles, it is translated as if the map repeated to the right.
     * @throws IllegalArgumentException if the y-ordinate of the given [position] is off the map.
     */
    private fun getForestTile(position: Point2D): ForestTile {
        return if (hasRecorded(position)) {
            getTile(position)
        } else {
            if (position.y > yMax()) throw IllegalArgumentException("$position is off the map!")
            val width = xMax() + 1
            getTile(Point2D(position.x % width, position.y))
        }
    }
}