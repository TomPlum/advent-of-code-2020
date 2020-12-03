package io.github.tomplum.aoc.map

import io.github.tomplum.libs.math.Direction
import io.github.tomplum.libs.math.Point2D

data class SlopeTrajectory(val right: Int, val down: Int) {
    fun apply(position: Point2D): Point2D {
        var translated = position
        repeat(right) { translated = translated.shift(Direction.RIGHT) }
        repeat(down) { translated = translated.shift(Direction.UP) }
        return translated
    }
}