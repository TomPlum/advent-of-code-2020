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
            BootCodeInstruction(BootCode.NO_OPERATION, 0),
            BootCodeInstruction(BootCode.ACCUMULATE, 1),
            BootCodeInstruction(BootCode.JUMP, 4),
            BootCodeInstruction(BootCode.ACCUMULATE, 3),
            BootCodeInstruction(BootCode.JUMP, -3),
            BootCodeInstruction(BootCode.ACCUMULATE, -99),
            BootCodeInstruction(BootCode.ACCUMULATE, 1),
            BootCodeInstruction(BootCode.NO_OPERATION, -4),
            BootCodeInstruction(BootCode.ACCUMULATE, 6),
        )
    )
}