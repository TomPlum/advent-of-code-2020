package io.github.tomplum.aoc.solutions

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day9Test {
    @Test
    fun partOne() {
        assertThat(Day9().part1()).isEqualTo(20874512)
    }

    @Test
    fun partTwo() {
        assertThat(Day9().part2()).isEqualTo(3012420)
    }
}