package io.github.tomplum.aoc.solutions

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day3Test {
    @Test
    fun partOne() {
        assertThat(Day3().part1()).isEqualTo(169)
    }
}