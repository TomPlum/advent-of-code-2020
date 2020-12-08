package io.github.tomplum.aoc.bootcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Test

class BootCodeParserTest {
    @Test
    fun example() {
        val input = TestInputReader().readInputAsString("bootcode/example-instructions.txt")
        val program = BootCodeParser.parse(input.value)
        assertThat(program).isEqualTo(getExpectedProgram())
    }

    private fun getExpectedProgram(): BootCodeProgram = BootCodeProgram(
        listOf(
            Instruction(Operation.NO_OPERATION, 0),
            Instruction(Operation.ACCUMULATE, 1),
            Instruction(Operation.JUMP, 4),
            Instruction(Operation.ACCUMULATE, 3),
            Instruction(Operation.JUMP, -3),
            Instruction(Operation.ACCUMULATE, -99),
            Instruction(Operation.ACCUMULATE, 1),
            Instruction(Operation.JUMP, -4),
            Instruction(Operation.ACCUMULATE, 6),
        )
    )
}