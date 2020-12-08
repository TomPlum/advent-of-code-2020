package io.github.tomplum.aoc.solutions

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day8Test {
    @Test
    fun partOne() {
        assertThat(Day8().part1()).isEqualTo(1832)
    }
}