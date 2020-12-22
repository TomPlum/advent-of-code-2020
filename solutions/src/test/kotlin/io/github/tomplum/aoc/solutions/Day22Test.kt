package io.github.tomplum.aoc.solutions

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day22Test {
    @Test
    fun part1() {
        assertThat(Day22().part1()).isEqualTo(32102)
    }
}