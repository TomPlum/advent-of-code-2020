package io.github.tomplum.aoc.solutions

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day15Test {
    @Test
    fun partOne() {
        assertThat(Day15().part1()).isEqualTo(1259)
    }

    @Test
    fun partTwo() {
        assertThat(Day15().part2()).isEqualTo(689)
    }
}