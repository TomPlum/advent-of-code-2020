package io.github.tomplum.aoc.island.lobby

import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isEqualTo
import io.github.tomplum.libs.math.Direction.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class TileLayoutParserTest {
    @Test
    fun example() {
        val directions = TileLayoutParser.parse(listOf("seswneswswsenwwnwse"))
        val first = directions.first()
        assertThat(first).containsExactly(BOTTOM_RIGHT, BOTTOM_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_LEFT, BOTTOM_RIGHT,
            TOP_LEFT, LEFT, TOP_LEFT, BOTTOM_RIGHT)
    }

    @Test
    fun invalidNorthDirection() {
        val e = assertThrows<IllegalArgumentException> { TileLayoutParser.parse(listOf("ns")) }
        assertThat(e.message).isEqualTo("ns is not a valid direction.")
    }

    @Test
    fun invalidSouthDirection() {
        val e = assertThrows<IllegalArgumentException> { TileLayoutParser.parse(listOf("sn")) }
        assertThat(e.message).isEqualTo("sn is not a valid direction.")
    }

    @Test
    fun invalidCompassDirection() {
        val e = assertThrows<IllegalArgumentException> { TileLayoutParser.parse(listOf("f")) }
        assertThat(e.message).isEqualTo("f is not a valid direction.")
    }
}