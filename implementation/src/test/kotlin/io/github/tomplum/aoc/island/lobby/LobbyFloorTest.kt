package io.github.tomplum.aoc.island.lobby

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Test

class LobbyFloorTest {
    @Test
    fun example() {
        val input = TestInputReader.read<String>("island/lobby/example.txt")
        val layout = TileLayoutParser.parse(input.value)
        assertThat(LobbyFloor(layout).getBlackTileCount()).isEqualTo(10)
    }
}