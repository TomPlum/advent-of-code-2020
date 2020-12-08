package io.github.tomplum.aoc.bootcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BootCodeRuntimeTest {
    @Nested
    inner class RunOnce {
        @Test
        fun exampleCorruptProgram() {
            val input = TestInputReader().readInputAsString("bootcode/example-instructions.txt")
            val program = BootCodeParser.parse(input.value)
            assertThat(BootCodeRuntime(program).runOnce()).isEqualTo(5)
        }

        @Test
        fun exampleValidProgram() {
            val input = TestInputReader().readInputAsString("bootcode/example-repaired.txt")
            val program = BootCodeParser.parse(input.value)
            assertThat(BootCodeRuntime(program).runOnce()).isEqualTo(8)
        }
    }

    @Nested
    inner class Run {
        @Test
        fun exampleCorruptProgram() {
            val input = TestInputReader().readInputAsString("bootcode/example-instructions.txt")
            val program = BootCodeParser.parse(input.value)
            assertThrows<CorruptBootCodeProgram> { BootCodeRuntime(program).run() }
        }

        @Test
        fun exampleValidProgram() {
            val input = TestInputReader().readInputAsString("bootcode/example-repaired.txt")
            val program = BootCodeParser.parse(input.value)
            assertThat(BootCodeRuntime(program).run()).isEqualTo(8)
        }
    }
}