package io.github.tomplum.aoc.solutions

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day11Test {
    @Test
    fun partOne() {
        assertThat(Day11().part1()).isEqualTo(2427)
    }
}