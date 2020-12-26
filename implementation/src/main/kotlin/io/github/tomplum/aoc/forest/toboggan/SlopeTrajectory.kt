package io.github.tomplum.aoc.forest.toboggan

import io.github.tomplum.libs.math.Direction
import io.github.tomplum.libs.math.point.Point2D

/**
 * The trajectory of the toboggan when descending through the forest as outlined in the [ForestMap].
 * @param right The number of tiles moved to the right each movement.
 * @param down The number of tiles moved down each movement.
 */
data class SlopeTrajectory(private val right: Int, private val down: Int) {
    fun apply(position: Point2D): Point2D {
        var translated = position
        repeat(right) { translated = translated.shift(Direction.RIGHT) }
        repeat(down) { translated = translated.shift(Direction.UP) }
        return translated
    }
}