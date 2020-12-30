package io.github.tomplum.aoc.forest.toboggan

import io.github.tomplum.aoc.extensions.product
import io.github.tomplum.libs.logging.AdventLogger
import io.github.tomplum.libs.math.map.AdventMap2D
import io.github.tomplum.libs.math.point.Point2D

/**
 * Due to the local geology, trees in this area only grow on exact integer coordinates in a grid.
 * These aren't the only trees, though; due to something you read about once involving arboreal genetics and biome
 * stability, the same pattern repeats to the right many times.
 */
class ForestMap(data: List<String>) : AdventMap2D<ForestTile>() {
    private val xMax: Int?
    private val yMax: Int?

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
        xMax = xMax()
        yMax = yMax()
        AdventLogger.info(this)
    }

    /**
     * Tracks the number of trees encountered during the toboggans descent for each of the given [slopes].
     * @return The cumulative product of all of the trees encountered.
     */
    fun trackTobogganTrajectory(vararg slopes: SlopeTrajectory): Long = slopes.map { slopeTrajectory ->
        var currentPosition = Point2D(0, 0)
        var treesEncountered = 0L
        while (currentPosition.y <= yMax!!) {
            val tile = getForestTile(currentPosition)
            if (tile.isTree()) treesEncountered++
            currentPosition = slopeTrajectory.apply(currentPosition)
        }
        treesEncountered
    }.product()

    /**
     * Gets the tile from the map at the given [pos].
     * Should the x-ordinate lie outside the mapped tiles, it is translated as if the map repeated to the right.
     * @throws IllegalArgumentException if the y-ordinate of the given [pos] is off the map.
     */
    private fun getForestTile(pos: Point2D) = getTile(pos, getTile(Point2D(pos.x % (xMax!! + 1), pos.y)))

}