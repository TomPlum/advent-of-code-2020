package io.github.tomplum.aoc.solutions

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day18Test {
    @Test
    fun partOne() {
        assertThat(Day18().part1()).isEqualTo(11297104473091)
    }

    @Test
    fun partTwo() {
        assertThat(Day18().part2()).isEqualTo(185348874183674)
    }
}