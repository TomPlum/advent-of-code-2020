package io.github.tomplum.aoc.solutions

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day20Test {
    @Test
    fun partOne() {
        assertThat(Day20().part1()).isEqualTo(7901522557967)
    }
}