package io.github.tomplum.aoc.map

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Test

class ForestMapTest {
    @Test
    fun example() {
        val data = TestInputReader().readInputAsString("map/forest/forest-map.txt")
        val map = ForestMap(data.value)
        assertThat(map.trackTobogganTrajectory()).isEqualTo(7)
    }
}