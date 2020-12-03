package io.github.tomplum.aoc.map

import io.github.tomplum.aoc.extensions.product
import io.github.tomplum.libs.logging.AdventLogger
import io.github.tomplum.libs.map.AdventMap2D
import io.github.tomplum.libs.math.Direction
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

    fun trackTobogganTrajectory(): Int {
        var currentPosition = Point2D(0, 0)
        var treesEncountered = 0
        while (currentPosition.y <= yMax()) {
            AdventLogger.debug("Toboggan Position: $currentPosition")
            val tile = getForestTile(currentPosition)
            if (tile.isTree()) treesEncountered++
            currentPosition = currentPosition.nextTobogganPosition()
        }
        return treesEncountered
    }

    fun trackTobogganTrajectory(slopes: List<SlopeTrajectory>): Int = slopes.map { slopeTrajectory ->
        var currentPosition = Point2D(0, 0)
        var treesEncountered = 0
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

    //TODO: Change shift() in libs so it takes an integer but default its value to 1 to save needing to overload
    private fun Point2D.nextTobogganPosition(): Point2D =
        this.shift(Direction.RIGHT).shift(Direction.RIGHT).shift(Direction.RIGHT).shift(Direction.UP)
}