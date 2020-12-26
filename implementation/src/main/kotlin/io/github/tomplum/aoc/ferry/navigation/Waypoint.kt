package io.github.tomplum.aoc.ferry.navigation

import io.github.tomplum.libs.math.Direction
import io.github.tomplum.libs.math.point.Point2D

/**
 * A logical entity belonging to a [Ferry] that is utilised its [NavigationSystem].
 * Whenever the [Ferry] changes its position, the waypoint moves with it.
 */
class Waypoint {
    var position = Point2D(10, 1)

    /**
     * Moves the waypoint in the given [direction] by the given number of [units].
     */
    fun move(direction: Direction, units: Int) {
        position = position.shift(direction, units)
    }
}