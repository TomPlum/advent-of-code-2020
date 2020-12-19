package io.github.tomplum.aoc.solutions

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day19Test {
    @Test
    fun partOne() {
        assertThat(Day19().part1()).isEqualTo(171)
    }
}