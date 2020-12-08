package io.github.tomplum.aoc.bootcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Test

class BootCodeRepairAgentTest {
    @Test
    fun example() {
        val input = TestInputReader().readInputAsString("bootcode/example-instructions.txt")
        val corruptedProgram = BootCodeParser.parse(input.value)
        val repairedProgram = BootCodeRepairAgent().fix(corruptedProgram)
        assertThat(repairedProgram).isEqualTo(getExpectedRepairedProgram())
    }

    private fun getExpectedRepairedProgram(): BootCodeProgram = BootCodeProgram(
        listOf(
            Instruction(Operation.NO_OPERATION, 0),
            Instruction(Operation.ACCUMULATE, 1),
            Instruction(Operation.JUMP, 4),
            Instruction(Operation.ACCUMULATE, 3),
            Instruction(Operation.JUMP, -3),
            Instruction(Operation.ACCUMULATE, -99),
            Instruction(Operation.ACCUMULATE, 1),
            Instruction(Operation.NO_OPERATION, -4),
            Instruction(Operation.ACCUMULATE, 6),
        )
    )
}