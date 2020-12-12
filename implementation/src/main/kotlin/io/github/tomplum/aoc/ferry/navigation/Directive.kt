package io.github.tomplum.aoc.ferry.navigation

/**
 * A navigational directive to move a [Ferry] or [Waypoint].
 *
 * @property FORWARD Moves the [Ferry] forward in its current direction or the ship to its [Waypoint].
 * @property RIGHT Turns the [Ferry] right or rotates the [Waypoint] clockwise around the ship.
 * @property LEFT Turns the [Ferry] left or rotates the [Waypoint] ant-clockwise around the ship.
 */
enum class Directive {
    FORWARD, RIGHT, LEFT
}