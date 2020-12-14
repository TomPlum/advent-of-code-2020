package io.github.tomplum.aoc.ferry.docking

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.ferry.docking.strategy.SeaPortDecoderV1
import io.github.tomplum.aoc.ferry.docking.strategy.SeaPortDecoderV2
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class DecoderEmulatorTest {
    @Nested
    inner class V1 {
        @Test
        fun example() {
            val input = TestInputReader.read<String>("mask/example-program.txt")
            val program = ProgramParser.parse(input.value)
            assertThat(DecoderEmulator(program).execute(SeaPortDecoderV1())).isEqualTo(165)
        }
    }

    @Nested
    inner class V2 {
        @Test
        fun example() {
            val input = TestInputReader.read<String>("mask/example-program-v2.txt")
            val program = ProgramParser.parse(input.value)
            assertThat(DecoderEmulator(program).execute(SeaPortDecoderV2())).isEqualTo(208)
        }
    }
}