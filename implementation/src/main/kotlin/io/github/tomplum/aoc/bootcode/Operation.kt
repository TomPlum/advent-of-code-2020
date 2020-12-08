package io.github.tomplum.aoc.bootcode

/**
 * A single operation dictating how an [Instruction] behaves.
 */
enum class Operation {
    NO_OPERATION,
    ACCUMULATE,
    JUMP;

    companion object {
        fun fromString(value: String): Operation = when(value) {
            "nop" -> NO_OPERATION
            "acc" -> ACCUMULATE
            "jmp" -> JUMP
            else -> throw IllegalArgumentException("Invalid Operation $value")
        }
    }
}