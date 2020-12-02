package io.github.tomplum.aoc.solutions

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.solutions.Day1
import org.junit.jupiter.api.Test

class Day1Test {
    @Test
    fun part1() {
        assertThat(Day1().part1()).isEqualTo(802011)
    }

    @Test
    fun part2() {
        assertThat(Day1().part2()).isEqualTo(248607374)
    }
}