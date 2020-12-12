package io.github.tomplum.aoc.ferry.navigation

import io.github.tomplum.libs.math.Direction
import io.github.tomplum.libs.math.Point2D

class Waypoint {
    var position = Point2D(10, 1)

    fun move(direction: Direction, units: Int) {
        position = position.shift(direction, units)
    }
}