package io.github.tomplum.aoc.solutions

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.solutions.Day2
import org.junit.jupiter.api.Test

class Day2Test {
    @Test
    fun part1() {
        assertThat(Day2().part1()).isEqualTo(660)
    }

    @Test
    fun part2() {
        assertThat(Day2().part2()).isEqualTo(530)
    }
}