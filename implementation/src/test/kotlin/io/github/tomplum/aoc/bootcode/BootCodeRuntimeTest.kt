package io.github.tomplum.aoc.bootcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class BootCodeRuntimeTest {
    @Nested
    inner class RunOnce {
        @Test
        fun example() {
            val input = TestInputReader().readInputAsString("bootcode/example-instructions.txt")
            val program = BootCodeParser.parse(input.value)
            assertThat(BootCodeRuntime(program).runOnce()).isEqualTo(5)
        }
    }
}