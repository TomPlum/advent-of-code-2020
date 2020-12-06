package io.github.tomplum.aoc.solutions

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day6Test {
    @Test
    fun partOne() {
        assertThat(Day6().part1()).isEqualTo(6911)
    }

    @Test
    fun partTwo() {
        assertThat(Day6().part2()).isEqualTo(3473)
    }
}