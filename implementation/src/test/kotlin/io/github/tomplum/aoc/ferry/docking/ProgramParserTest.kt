package io.github.tomplum.aoc.ferry.docking

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Test

class ProgramParserTest {
    @Test
    fun exampleSingleProgram() {
        val input = TestInputReader.read<String>("mask/example-program.txt")
        val program = ProgramParser.parse(input.value)
        assertThat(program).isEqualTo(getExpectedSingleMask())
    }

    @Test
    fun exampleMultiplePrograms() {
        val input = TestInputReader.read<String>("mask/example-multiple-programs.txt")
        val program = ProgramParser.parse(input.value)
        assertThat(program).isEqualTo(getExpectedMultipleMasks())
    }

    private fun getExpectedSingleMask(): InitialisationProgram {
        val mask = Mask(36)
        mask.put(34, 0)
        mask.put(29, 1)
        ((0..28) + (30..33) + 35).forEach { mask.put(it, 2) }
        val instructions = listOf(Instruction(8, 11), Instruction(7, 101), Instruction(8, 0))
        return InitialisationProgram(mapOf(mask to instructions))
    }

    private fun getExpectedMultipleMasks(): InitialisationProgram {
        val programData = mutableMapOf<Mask, List<Instruction>>()

        val firstMask = Mask(36)
        firstMask.put(34, 0)
        firstMask.put(29, 1)
        ((0..28) + (30..33) + 35).forEach { firstMask.put(it, 2) }

        val firstInstructions = listOf(Instruction(8,11), Instruction(6,0))
        programData[firstMask] = firstInstructions

        val secondMask = Mask(36)
        secondMask.put(29, 0)
        secondMask.put(35, 1)
        ((0..28) + (30..34)).forEach { secondMask.put(it, 2) }

        val secondInstructions = listOf(Instruction(1,5), Instruction(23,56), Instruction(4,1))
        programData[secondMask] = secondInstructions

        return InitialisationProgram(programData)
    }
}