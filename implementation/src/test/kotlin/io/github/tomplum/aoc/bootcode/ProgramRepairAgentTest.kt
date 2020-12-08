package io.github.tomplum.aoc.bootcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ProgramRepairAgentTest {
    @Test
    fun example() {
        val input = TestInputReader().readInputAsString("bootcode/example-instructions.txt")
        val corruptedProgram = BootCodeParser.parse(input.value)
        val repairedProgram = ProgramRepairAgent().fix(corruptedProgram)
        assertThat(repairedProgram).isEqualTo(getExpectedRepairedProgram())
    }

    @Test
    fun validProgram() {
        val input = TestInputReader().readInputAsString("bootcode/example-repaired.txt")
        val program = BootCodeParser.parse(input.value)
        val e = assertThrows<IllegalArgumentException> { ProgramRepairAgent().fix(program) }
        assertThat(e.message).isEqualTo("Program is valid and cannot be repaired.")
    }

    private fun getExpectedRepairedProgram(): Program = Program(
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