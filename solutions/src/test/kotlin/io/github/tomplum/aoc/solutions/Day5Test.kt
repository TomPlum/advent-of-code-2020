package io.github.tomplum.aoc.solutions

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day5Test {
    @Test
    fun partOne() {
        assertThat(Day5().part1()).isEqualTo(904)
    }

    @Test
    fun partTwo() {
        assertThat(Day5().part2()).isEqualTo(669)
    }
}