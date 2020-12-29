package io.github.tomplum.aoc.solutions

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class Day11Test {
    @Test
    fun partOne() {
        assertThat(Day11().part1()).isEqualTo(2427)
    }

    @Test
    fun partTwo() {
        assertThat(Day11().part2()).isEqualTo(2199)
    }
}