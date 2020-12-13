package io.github.tomplum.aoc.solutions

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day13Test {
    @Test
    fun partOne() {
        assertThat(Day13().part1()).isEqualTo(102)
    }
}