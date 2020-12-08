package io.github.tomplum.aoc.bootcode

enum class BootCode {
    NO_OPERATION,
    ACCUMULATE,
    JUMP;

    companion object {
        fun fromString(value: String): BootCode = when(value) {
            "nop" -> NO_OPERATION
            "acc" -> ACCUMULATE
            "jmp" -> JUMP
            else -> throw IllegalArgumentException("Invalid BootCode $value")
        }
    }
}