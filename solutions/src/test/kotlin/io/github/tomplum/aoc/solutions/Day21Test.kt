package io.github.tomplum.aoc.solutions

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day21Test {
    @Test
    fun partOne() {
        assertThat(Day21().part1()).isEqualTo(2315)
    }
}