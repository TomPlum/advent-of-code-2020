package io.github.tomplum.aoc.ferry.docking

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Test

class ProgramParserTest {
    @Test
    fun example() {
        val input = TestInputReader.read<String>("mask/example-program.txt")
        val programs = ProgramParser.parse(input.value)
        assertThat(programs.first()).isEqualTo(getExpectedProgram())
    }

    private fun getExpectedProgram(): InitialisationProgram {
        val mask = Mask()
        mask.put(34, 0)
        mask.put(29, 1)
        return InitialisationProgram(mask, listOf(Instruction(8, 11), Instruction(7, 101), Instruction(8, 0)))
    }
}