package io.github.tomplum.aoc.ferry.navigation

import io.github.tomplum.libs.math.Direction
import io.github.tomplum.libs.math.point.Point2D

/**
 * A ship designed to transport people to a tropical island.
 */
class Ferry {
    private var position = Point2D.origin()
    private var direction = Direction.RIGHT
    val waypoint = Waypoint()

    /**
     * Calculates the manhattan-distance between the journey start point and current position.
     * @return The number of units travelled at the current point in time.
     */
    fun distanceTravelled(): Int = Point2D.origin().distanceBetween(position)

    /**
     * Rotates the ferry by the given [angle] relative to its current [position].
     * @param angle The degree by which to rotate the ship.
     */
    fun rotate(angle: Int) {
        direction = direction.rotate(angle)
    }

    /**
     * Moves the ferry and its [waypoint] in the given [direction] by the given number of [units].
     * This does not change the [direction] of the ship. It will be facing the same way after moving.
     * If the direction is null then the ship and its waypoint do not move.
     *
     * @param direction The direction to move in.
     * @param units The number of units to move by.
     */
    fun move(direction: Direction?, units: Int) {
        if (direction != null) {
            position = position.shift(direction, units)
            waypoint.move(direction, units)
        }
    }

    /**
     * Moves the ship by the given number of [units] in the [direction] it is currently facing.
     */
    fun advance(units: Int) {
        position = position.shift(direction, units)
    }

    /**
     * Rotates the [waypoint] about the ships current [position] by the given [angle].
     * The ship does not move.
     * @param angle The degree by which to rotate the waypoint.
     */
    fun rotateWaypoint(angle: Int) {
        waypoint.position = waypoint.position.rotateAbout(position, angle)
    }

    /**
     * Moves the the ship to its [waypoint] the given number of [times].
     * Whenever the ship moves, its [waypoint] also moves with it meaning the positional delta between the ship
     * and its [waypoint] don't change.
     * @param times The number of times to move to the ship to its [waypoint].
     */
    fun moveToWaypoint(times: Int) {
        val (xDirection, xUnits) = waypoint.position.xRelativeDirection(position) ?: (null to 0)
        val (yDirection, yUnits) = waypoint.position.yRelativeDirection(position) ?: (null to 0)
        move(xDirection, xUnits * times)
        move(yDirection, yUnits * times)
    }
}