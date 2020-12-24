package io.github.tomplum.aoc.island.lobby

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Test

class ArtExhibitSimulatorTest {
    @Test
    fun example() {
        val input = TestInputReader.read<String>("island/lobby/example.txt")
        val layout = TileLayoutParser.parse(input.value)
        assertThat(ArtExhibitSimulator(HexGrid(layout)).simulate(100)).isEqualTo(2208)
    }
}