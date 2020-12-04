package io.github.tomplum.aoc.solutions

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day4Test {
    @Test
    fun partOne() {
        assertThat(Day4().part1()).isEqualTo(192)
    }

    @Test
    fun partTwo() {
        assertThat(Day4().part2()).isEqualTo(101)
    }
}