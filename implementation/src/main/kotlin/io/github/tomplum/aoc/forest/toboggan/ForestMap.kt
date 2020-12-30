package io.github.tomplum.aoc.forest.toboggan

import io.github.tomplum.aoc.extensions.product
import io.github.tomplum.libs.logging.AdventLogger
import io.github.tomplum.libs.math.map.AdventMap2D
import io.github.tomplum.libs.math.point.Point2D

/**
 * Due to the local geology, trees in this area only grow on exact integer coordinates in a grid.
 * These aren't the only trees, though; due to something you read about once involving arboreal genetics and biome
 * stability, the same pattern repeats to the right many times.
 *
 * @param data A list of rows of the forest map data before it repeats.
 */
class ForestMap(data: List<String>) : AdventMap2D<ForestTile>() {
    private var xMax: Int = 0
    private var yMax: Int = 0

    init {
        var x = 0
        var y = 0
        data.forEach { row ->
            row.forEach { column ->
                addTile(Point2D(x, y), ForestTile(column))
                x++
                xMax = x
            }
            x = 0
            y++
        }
        xMax -= 1
        yMax = y - 1
        AdventLogger.info(this)
    }

    /**
     * Tracks the number of trees encountered during the toboggans descent for each of the given [slopes].
     * @param slopes a list of slope trajectories to track.
     * @return The cumulative product of all of the trees encountered.
     */
    fun trackTobogganTrajectory(vararg slopes: SlopeTrajectory): Long = slopes.map { slopeTrajectory ->
        var position = Point2D(0, 0)
        var treesEncountered = 0L
        while (position.y <= yMax) {
            val tile = getForestTile(position)
            if (tile.isTree()) treesEncountered++
            position = slopeTrajectory.apply(position)
        }
        treesEncountered
    }.product()

    /**
     * Gets the tile from the map at the given [pos].
     * Should the x-ordinate lie outside the mapped tiles, it is translated as if the map repeated to the right.
     * @param pos The logical position to get from the map.
     * @throws IllegalArgumentException if the y-ordinate of the given [pos] is off the map.
     * @return The tile at the given position.
     */
    private fun getForestTile(pos: Point2D): ForestTile {
        return getTile(pos, getTile(Point2D(pos.x % (xMax + 1), pos.y)))
    }
}