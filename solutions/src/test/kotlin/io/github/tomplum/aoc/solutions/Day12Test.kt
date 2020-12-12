package io.github.tomplum.aoc.solutions

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day12Test {
    @Test
    fun partOne() {
        assertThat(Day12().part1()).isEqualTo(319)
    }
}