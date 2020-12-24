package io.github.tomplum.aoc.island.lobby

import assertk.assertThat
import assertk.assertions.containsExactly
import io.github.tomplum.libs.math.Direction.*
import org.junit.jupiter.api.Test

class TileLayoutParserTest {
    @Test
    fun example() {
        val directions = TileLayoutParser.parse(listOf("kseswneswswsenwwnwse"))
        val first = directions.first()
        assertThat(first).containsExactly(BOTTOM_RIGHT, BOTTOM_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_LEFT, BOTTOM_RIGHT,
            TOP_LEFT, LEFT, TOP_LEFT, BOTTOM_RIGHT)
    }
}