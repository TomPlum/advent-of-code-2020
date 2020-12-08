package io.github.tomplum.aoc.bootcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Test

class BootCodeRepairAgentTest {
    @Test
    fun example() {
        val input = TestInputReader().readInputAsString("bootcode/example-instructions.txt")
        val corruptedProgram = BootCodeParser.parse(input.value)
        val repairedProgram = BootCodeRepairAgent().fix(corruptedProgram)
        assertThat(BootCodeRuntime(repairedProgram).run()).isEqualTo(8)
    }
}