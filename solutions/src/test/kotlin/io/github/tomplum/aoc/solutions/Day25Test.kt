package io.github.tomplum.aoc.solutions

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day25Test {
    @Test
    fun partOne() {
        assertThat(Day25().part1()).isEqualTo(19774660)
    }
}