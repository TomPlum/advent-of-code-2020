package io.github.tomplum.aoc.ferry.navigation

import io.github.tomplum.libs.math.Direction
import io.github.tomplum.libs.math.Point2D
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

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

    fun navigate(): Int {
        instructions.forEach { (action, value) ->
            when (action) {
                is Direction -> ship = ship.shift(action, value)
                is Directive -> when (action) {
                    Directive.LEFT -> when (value) {
                        90 -> shipsDirection = shipsDirection.rotateAntiClockwise()
                        180 -> repeat(2) { shipsDirection = shipsDirection.rotateAntiClockwise() } //TODO: Update Point2D to rotate 90,180,270
                        270 -> repeat(3) { shipsDirection = shipsDirection.rotateAntiClockwise() }
                    }
                    Directive.RIGHT -> when (value) {
                        90 -> shipsDirection = shipsDirection.rotateClockwise90()
                        180 -> repeat(2) { shipsDirection = shipsDirection.rotateClockwise90() }
                        270 -> repeat(3) { shipsDirection = shipsDirection.rotateClockwise90() }
                    }
                    Directive.FORWARD -> ship = ship.shift(shipsDirection, value)
                }
            }
        }
        return Point2D(0, 0).distanceBetween(ship)
    }

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
    }.let { Point2D(0, 0).distanceBetween(ship) }

    private fun rotateWaypoint(angle: Int) {
        waypoint = waypoint.rotateAbout(ship, angle)
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

    private fun Point2D.xRelativeDirection(other: Point2D): Pair<Direction, Int>? {
        val xDelta = x - other.x
        return when {
            xDelta > 0 -> Pair(Direction.RIGHT, xDelta)
            xDelta < 0 -> Pair(Direction.LEFT, abs(xDelta))
            else -> null
        }
    }

    private fun Point2D.yRelativeDirection(other: Point2D): Pair<Direction, Int>? {
        val yDelta = y - other.y
        return when {
            yDelta > 0 -> Pair(Direction.UP, yDelta)
            yDelta < 0 -> Pair(Direction.DOWN, abs(yDelta))
            else -> null
        }
    }

    private fun Point2D.rotateAbout(pivot: Point2D, angle: Int = 90): Point2D {
        val normalisedAngle = if (angle < 0) angle + 360 else angle

        val sin = sin(normalisedAngle.toDouble().toRadians())
        val cos = cos(normalisedAngle.toDouble().toRadians())

        val x1 = x - pivot.x
        val y1 = y - pivot.y

        val x2 = x1 * cos + y1 * sin
        val y2 = -x1 * sin + y1 * cos

        val xNew = x2 + pivot.x
        val yNew = y2 + pivot.y

        return Point2D(xNew.roundToInt(), yNew.roundToInt())
    }

    private fun Double.toRadians() = this / 180 * Math.PI

}