package io.github.tomplum.aoc.solutions

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day23Test {
    @Test
    fun partOne() {
        assertThat(Day23().part1()).isEqualTo("58427369")
    }
}