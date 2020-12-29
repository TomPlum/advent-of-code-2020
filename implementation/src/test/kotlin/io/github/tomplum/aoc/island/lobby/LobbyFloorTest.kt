package io.github.tomplum.aoc.island.lobby

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import io.github.tomplum.libs.math.Direction
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LobbyFloorTest {
    @Test
    fun example() {
        val input = TestInputReader.read<String>("island/lobby/example.txt")
        val layout = TileLayoutParser.parse(input.value)
        assertThat(LobbyFloor(layout).getBlackTileCount()).isEqualTo(10)
    }

    @Test
    fun invalidTileDirection() {
        val e = assertThrows<IllegalArgumentException> { LobbyFloor(listOf(listOf(Direction.UP))) }
        assertThat(e.message).isEqualTo("UP is not a valid hexagonal direction!")
    }
}