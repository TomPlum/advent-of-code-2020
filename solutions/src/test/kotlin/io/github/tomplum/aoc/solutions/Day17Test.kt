package io.github.tomplum.aoc.solutions

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day17Test {
    @Test
    fun partOne() {
        assertThat(Day17().part1()).isEqualTo(269)
    }
}