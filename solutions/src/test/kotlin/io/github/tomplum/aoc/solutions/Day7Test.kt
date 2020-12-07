package io.github.tomplum.aoc.solutions

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day7Test {
    @Test
    fun partOne() {
        assertThat(Day7().part1()).isEqualTo(300)
    }

    @Test
    fun partTwo() {
        assertThat(Day7().part2()).isEqualTo(8030)
    }
}