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
        assertThat(map.trackTobogganTrajectory(listOf(SlopeTrajectory(3,1)))).isEqualTo(7)
    }

    @Test
    fun multipleSlopeExample() {
        val data = TestInputReader().readInputAsString("map/forest/forest-map.txt")
        val map = ForestMap(data.value)
        val slopes = listOf(SlopeTrajectory(1,1), SlopeTrajectory(3,1), SlopeTrajectory(5,1), SlopeTrajectory(7,1), SlopeTrajectory(1,2))
        assertThat(map.trackTobogganTrajectory(slopes)).isEqualTo(336)
    }
}