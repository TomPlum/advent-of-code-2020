package io.github.tomplum.aoc.bootcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class RuntimeTest {
    @Nested
    inner class RunOnce {
        @Test
        fun exampleCorruptProgram() {
            val input = TestInputReader.read<String>("bootcode/example-instructions.txt")
            val program = BootCodeParser.parse(input.value)
            assertThat(Runtime(program).runOnce()).isEqualTo(5)
        }

        @Test
        fun exampleValidProgram() {
            val input = TestInputReader.read<String>("bootcode/example-repaired.txt")
            val program = BootCodeParser.parse(input.value)
            assertThat(Runtime(program).runOnce()).isEqualTo(8)
        }
    }

    @Nested
    inner class Run {
        @Test
        fun exampleCorruptProgram() {
            val input = TestInputReader.read<String>("bootcode/example-instructions.txt")
            val program = BootCodeParser.parse(input.value)
            assertThrows<CorruptProgram> { Runtime(program).run() }
        }

        @Test
        fun exampleValidProgram() {
            val input = TestInputReader.read<String>("bootcode/example-repaired.txt")
            val program = BootCodeParser.parse(input.value)
            assertThat(Runtime(program).run()).isEqualTo(8)
        }
    }
}