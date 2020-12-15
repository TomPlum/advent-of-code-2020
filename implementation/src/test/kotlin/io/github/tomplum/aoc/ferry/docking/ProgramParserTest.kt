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
        assertThat(program).isEqualTo(getExpectedSingleRoutine())
    }

    @Test
    fun exampleMultiplePrograms() {
        val input = TestInputReader.read<String>("mask/example-multiple-programs.txt")
        val program = ProgramParser.parse(input.value)
        assertThat(program).isEqualTo(getExpectedMultipleRoutines())
    }

    private fun getExpectedSingleRoutine(): InitProgram {
        val mask = BitMask("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X")
        val instructions = listOf(Instruction(8, 11), Instruction(7, 101), Instruction(8, 0))
        return InitProgram(mapOf(mask to instructions))
    }

    private fun getExpectedMultipleRoutines(): InitProgram {
        val programData = mutableMapOf<BitMask, List<Instruction>>()

        val firstMask = BitMask("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X")
        val firstInstructions = listOf(Instruction(8,11), Instruction(6,0))
        programData[firstMask] = firstInstructions

        val secondMask = BitMask("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX0XXXXX1")
        val secondInstructions = listOf(Instruction(1,5), Instruction(23,56), Instruction(4,1))
        programData[secondMask] = secondInstructions

        return InitProgram(programData)
    }
}
