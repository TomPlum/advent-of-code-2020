package io.github.tomplum.aoc.ferry.navigation

import io.github.tomplum.libs.math.Direction
import io.github.tomplum.libs.math.Point2D

class NavigationSystem(data: List<String>) {
    private val instructions: List<Instruction<*>>
    private var shipsDirection = Direction.RIGHT
    private var position = Point2D(0,0)

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
                is Direction -> position = position.shift(instruction.action, instruction.value)
                is Directive -> when(instruction.action) {
                    Directive.LEFT -> when(instruction.value) {
                        90 -> shipsDirection = shipsDirection.rotateAntiClockwise()
                        180 -> repeat(2) { shipsDirection = shipsDirection.rotateAntiClockwise() }
                        270 -> repeat(3) { shipsDirection = shipsDirection.rotateAntiClockwise() }
                    }
                    Directive.RIGHT -> when(instruction.value) {
                        90 -> shipsDirection = shipsDirection.rotateClockwise90()
                        180 -> repeat(2) { shipsDirection = shipsDirection.rotateClockwise90() }
                        270 -> repeat(3) { shipsDirection = shipsDirection.rotateClockwise90() }
                    }
                    Directive.FORWARD -> position = position.shift(shipsDirection, instruction.value)
                }
            }
        }
        return Point2D(0,0).distanceBetween(position)
    }
}