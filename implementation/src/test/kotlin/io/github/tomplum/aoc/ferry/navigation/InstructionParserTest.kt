package io.github.tomplum.aoc.ferry.navigation

import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import io.github.tomplum.libs.math.Direction
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class InstructionParserTest {
    @Test
    fun north() {
        assertThat(InstructionParser.parse(listOf("N2"))).containsExactly(Instruction(Direction.UP, 2))
    }

    @Test
    fun south() {
        assertThat(InstructionParser.parse(listOf("S5"))).containsExactly(Instruction(Direction.DOWN, 5))
    }

    @Test
    fun east() {
        assertThat(InstructionParser.parse(listOf("E10"))).containsExactly(Instruction(Direction.RIGHT, 10))
    }

    @Test
    fun west() {
        assertThat(InstructionParser.parse(listOf("W6"))).containsExactly(Instruction(Direction.LEFT, 6))
    }

    @Test
    fun left() {
        assertThat(InstructionParser.parse(listOf("L90"))).containsExactly(Instruction(Directive.LEFT, 90))
    }

    @Test
    fun right() {
        assertThat(InstructionParser.parse(listOf("R180"))).containsExactly(Instruction(Directive.RIGHT, 180))
    }

    @Test
    fun forward() {
        assertThat(InstructionParser.parse(listOf("F11"))).containsExactly(Instruction(Directive.FORWARD, 11))
    }

    @ParameterizedTest
    @ValueSource(strings = ["J1", "H10", "T1"])
    fun invalidValue(value: String) {
        val e = assertThrows<IllegalArgumentException> { InstructionParser.parse(listOf(value)) }
        assertThat(e.message).isEqualTo("Invalid Instruction $value")
    }

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