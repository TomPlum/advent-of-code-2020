package io.github.tomplum.aoc.solutions

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day3Test {
    @Test
    fun partOne() {
        assertThat(Day3().part1()).isEqualTo(169)
    }

    @Test
    fun partTwo() {
        assertThat(Day3().part2()).isEqualTo(7560370818)
    }
}