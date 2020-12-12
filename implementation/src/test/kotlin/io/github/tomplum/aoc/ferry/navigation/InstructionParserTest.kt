package io.github.tomplum.aoc.ferry.navigation

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import io.github.tomplum.libs.math.Direction
import org.junit.jupiter.api.Test

class InstructionParserTest {
    @Test
    fun example() {
        val data = TestInputReader.read<String>("ferry/navigation/instructions.txt").value
        val instructions = InstructionParser.parse(data)
        assertThat(instructions).isEqualTo(getExpectedInstructions())
    }

    private fun getExpectedInstructions(): List<Instruction<*>> = listOf(
        Instruction(Directive.FORWARD, 10),
        Instruction(Direction.UP, 3),
        Instruction(Directive.FORWARD, 7),
        Instruction(Directive.RIGHT, 90),
        Instruction(Directive.FORWARD, 11)
    )
}