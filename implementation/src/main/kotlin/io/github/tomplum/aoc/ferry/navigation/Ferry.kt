package io.github.tomplum.aoc.ferry.navigation

import io.github.tomplum.libs.math.Direction
import io.github.tomplum.libs.math.Point2D

class Ferry {
    private var position = Point2D.origin()
    private var direction = Direction.RIGHT
    val waypoint = Waypoint()

    fun distanceTravelled(): Int = Point2D.origin().distanceBetween(position)

    fun rotate(angle: Int) {
        direction = direction.rotate(angle)
    }

    fun move(direction: Direction?, units: Int) {
        if (direction != null) {
            position = position.shift(direction, units)
            waypoint.move(direction, units)
        }
    }

    fun rotateWaypoint(angle: Int) {
        waypoint.position = waypoint.position.rotateAbout(position, angle)
    }

    fun advance(units: Int) {
        position = position.shift(direction, units)
    }

    fun moveToWaypoint(times: Int) {
        val (xDirection, xUnits) = waypoint.position.xRelativeDirection(position) ?: (null to 0)
        val (yDirection, yUnits) = waypoint.position.yRelativeDirection(position) ?: (null to 0)
        move(xDirection, xUnits * times)
        move(yDirection, yUnits * times)
    }
}