package io.github.tomplum.aoc.ferry.docking

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Test

class DecoderEmulatorTest {
    @Test
    fun example() {
        val input = TestInputReader.read<String>("mask/example-program.txt")
        val program = ProgramParser.parse(input.value)
        assertThat(DecoderEmulator(program).execute()).isEqualTo(165)
    }
}