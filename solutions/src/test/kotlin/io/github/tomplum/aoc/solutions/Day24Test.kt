package io.github.tomplum.aoc.solutions

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day24Test {
    @Test
    fun partOne() {
        assertThat(Day24().part1()).isEqualTo(326)
    }

    @Test
    fun partTwo() {
        assertThat(Day24().part2()).isEqualTo(3979)
    }
}