package io.github.tomplum.aoc.solutions

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day14Test {
    @Test
    fun partOne() {
        assertThat(Day14().part1()).isEqualTo(9967721333886)
    }
}