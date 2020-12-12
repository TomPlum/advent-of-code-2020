package io.github.tomplum.aoc.ferry.navigation

import io.github.tomplum.libs.math.Direction

class InstructionParser private constructor() {
    companion object {
        fun parse(data: List<String>) = data.map {
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
}