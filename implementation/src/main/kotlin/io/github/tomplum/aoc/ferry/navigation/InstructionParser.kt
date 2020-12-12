package io.github.tomplum.aoc.ferry.navigation

import io.github.tomplum.libs.math.Direction

class InstructionParser private constructor() {
    companion object {
        fun parse(data: List<String>) = data.map { instruction ->
            val value = instruction.drop(1).toInt()
            when (instruction.take(1)) {
                "N" -> Instruction(Direction.UP, value)
                "S" -> Instruction(Direction.DOWN, value)
                "E" -> Instruction(Direction.RIGHT, value)
                "W" -> Instruction(Direction.LEFT, value)
                "L" -> Instruction(Directive.LEFT, value)
                "R" -> Instruction(Directive.RIGHT, value)
                "F" -> Instruction(Directive.FORWARD, value)
                else -> throw IllegalArgumentException("Invalid Instruction $instruction")
            }
        }
    }
}