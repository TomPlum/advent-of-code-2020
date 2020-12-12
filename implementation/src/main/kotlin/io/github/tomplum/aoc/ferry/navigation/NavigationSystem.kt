package io.github.tomplum.aoc.ferry.navigation

import io.github.tomplum.libs.math.Direction
import io.github.tomplum.libs.math.Point2D

class NavigationSystem(data: List<String>) {
    private val instructions: List<Instruction<*>>
    private var shipsDirection = Direction.RIGHT
    private var ship = Point2D(0, 0)
    private var waypoint = Point2D(10, 1)

    init {
        instructions = data.map {
            val value = it.drop(1).toInt()
            when (it.take(1)) {
                "N" -> Instruction(Direction.UP, value)
                "S" -> Instruction(Direction.DOWN, value)
                "E" -> Instruction(Direction.RIGHT, value)
                "W" -> Instruction(Direction.LEFT, value)
                "L" -> Instruction(Directive.LEFT, value)
                "R" -> Instruction(Directive.RIGHT, value)
                "F" -> Instruction(Directive.FORWARD, value)
                else -> throw IllegalArgumentException("Invalid Instruction $it")
            }
        }
    }

    fun navigate(): Int = instructions.forEach { (action, value) ->
        when (action) {
            is Direction -> ship = ship.shift(action, value)
            is Directive -> when (action) {
                Directive.LEFT -> rotateShip(-value)
                Directive.RIGHT -> rotateShip(value)
                Directive.FORWARD -> ship = ship.shift(shipsDirection, value)
            }
        }
    }.let { distanceTravelled() }

    fun navigateViaWaypoint(): Int = instructions.forEach { (action, value) ->
        when (action) {
            is Direction -> moveWaypoint(action, value)
            is Directive -> when (action) {
                Directive.LEFT -> rotateWaypoint(-value)
                Directive.RIGHT -> rotateWaypoint(value)
                Directive.FORWARD -> {
                    val (xDirection, xUnits) = waypoint.xRelativeDirection(ship) ?: (null to 0)
                    val (yDirection, yUnits) = waypoint.yRelativeDirection(ship) ?: (null to 0)
                    moveShip(xDirection, xUnits * value)
                    moveShip(yDirection, yUnits * value)
                }
            }
        }
    }.let { distanceTravelled() }

    private fun distanceTravelled(): Int = Point2D.origin().distanceBetween(ship)

    private fun rotateWaypoint(angle: Int) {
        waypoint = waypoint.rotateAbout(ship, angle)
    }

    private fun rotateShip(angle: Int) {
        shipsDirection = shipsDirection.rotate(angle)
    }

    private fun moveShip(direction: Direction?, units: Int) {
        if (direction != null) {
            ship = ship.shift(direction, units)
            moveWaypoint(direction, units)
        }
    }

    private fun moveWaypoint(direction: Direction, units: Int) {
        waypoint = waypoint.shift(direction, units)
    }
}