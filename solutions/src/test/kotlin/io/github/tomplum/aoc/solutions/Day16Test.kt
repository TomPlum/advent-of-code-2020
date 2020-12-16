package io.github.tomplum.aoc.solutions

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day16Test {
    @Test
    fun partOne() {
        assertThat(Day16().part1()).isEqualTo(28882)
    }
}