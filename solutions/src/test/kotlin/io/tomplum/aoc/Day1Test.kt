package io.tomplum.aoc

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day1Test {
    @Test
    fun part1() {
        assertThat(Day1().part1()).isEqualTo(802011)
    }
}