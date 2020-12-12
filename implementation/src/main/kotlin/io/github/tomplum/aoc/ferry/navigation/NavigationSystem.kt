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
    private var shipsPosition = Point2D(0,0)
    private var waypointPosition = Point2D(10, 1)

    init {
        instructions = data.map {
            when(it.take(1)) {
                "N" -> Instruction(Direction.UP, it.drop(1).toInt())
                "S" -> Instruction(Direction.DOWN, it.drop(1).toInt())
                "E" -> Instruction(Direction.RIGHT, it.drop(1).toInt())
                "W" -> Instruction(Direction.LEFT, it.drop(1).toInt())
                "L" -> Instruction(Directive.LEFT, it.drop(1).toInt())
                "R" -> Instruction(Directive.RIGHT, it.drop(1).toInt())
                "F" -> Instruction(Directive.FORWARD, it.drop(1).toInt())
                else -> throw IllegalArgumentException("Invalid Instruction $it")
            }
        }
    }

    fun navigate(): Int {
        instructions.forEach { instruction ->
            when(instruction.action) {
                is Direction -> shipsPosition = shipsPosition.shift(instruction.action, instruction.value)
                is Directive -> when(instruction.action) {
                    Directive.LEFT -> when(instruction.value) {
                        90 -> shipsDirection = shipsDirection.rotateAntiClockwise()
                        180 -> repeat(2) { shipsDirection = shipsDirection.rotateAntiClockwise() } //TODO: Update Point2D to rotate 90,180,270
                        270 -> repeat(3) { shipsDirection = shipsDirection.rotateAntiClockwise() }
                    }
                    Directive.RIGHT -> when(instruction.value) {
                        90 -> shipsDirection = shipsDirection.rotateClockwise90()
                        180 -> repeat(2) { shipsDirection = shipsDirection.rotateClockwise90() }
                        270 -> repeat(3) { shipsDirection = shipsDirection.rotateClockwise90() }
                    }
                    Directive.FORWARD -> shipsPosition = shipsPosition.shift(shipsDirection, instruction.value)
                }
            }
        }
        return Point2D(0,0).distanceBetween(shipsPosition)
    }

    fun navigateViaWaypoint(): Int {
        instructions.forEach { instruction ->
            when(instruction.action) {
                is Direction -> waypointPosition = waypointPosition.shift(instruction.action, instruction.value)
                is Directive -> when(instruction.action) {
                    Directive.LEFT -> {
                        waypointPosition = waypointPosition.rotateAbout(shipsPosition, -instruction.value)
                    }
                    Directive.RIGHT -> {
                        waypointPosition = waypointPosition.rotateAbout(shipsPosition, instruction.value)
                    }
                    Directive.FORWARD -> {
                        val xDelta = waypointPosition.x - shipsPosition.x
                        val yDelta = waypointPosition.y - shipsPosition.y
                        repeat(instruction.value) {
                            when {
                                xDelta > 0 -> {
                                    shipsPosition = shipsPosition.shift(Direction.RIGHT, xDelta)
                                    waypointPosition = waypointPosition.shift(Direction.RIGHT, xDelta)
                                }
                                xDelta < 0 -> {
                                    shipsPosition = shipsPosition.shift(Direction.LEFT, abs(xDelta))
                                    waypointPosition = waypointPosition.shift(Direction.LEFT, abs(xDelta))
                                }
                            }
                            when {
                                yDelta > 0 -> {
                                    shipsPosition = shipsPosition.shift(Direction.UP, yDelta)
                                    waypointPosition = waypointPosition.shift(Direction.UP, yDelta)
                                }
                                yDelta < 0 -> {
                                    shipsPosition = shipsPosition.shift(Direction.DOWN, abs(yDelta))
                                    waypointPosition = waypointPosition.shift(Direction.DOWN, abs(yDelta))
                                }
                            }
                        }
                    }
                }
            }
        }
        return Point2D(0,0).distanceBetween(shipsPosition)
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