package io.github.tomplum.aoc.solutions

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day19Test {
    @Test
    fun partOne() {
        assertThat(Day19().part1()).isEqualTo(171)
    }

    @Test
    fun partTwo() {
        assertThat(Day19().part2()).isEqualTo(369)
    }
}