package io.github.tomplum.aoc.airport.game

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class MemoryGameTest {
    @Test
    fun exampleOne() {
        assertThat(MemoryGame(listOf(0,3,6)).simulate(2020)).isEqualTo(436)
    }

    @Test
    fun exampleTwo() {
        assertThat(MemoryGame(listOf(1,3,2)).simulate(2020)).isEqualTo(1)
    }

    @Test
    fun exampleThree() {
        assertThat(MemoryGame(listOf(2,1,3)).simulate(2020)).isEqualTo(10)
    }

    @Test
    fun exampleFour() {
        assertThat(MemoryGame(listOf(1,2,3)).simulate(2020)).isEqualTo(27)
    }

    @Test
    fun exampleFive() {
        assertThat(MemoryGame(listOf(2,3,1)).simulate(2020)).isEqualTo(78)
    }

    @Test
    fun exampleSix() {
        assertThat(MemoryGame(listOf(3,2,1)).simulate(2020)).isEqualTo(438)
    }

    @Test
    fun exampleSeven() {
        assertThat(MemoryGame(listOf(3,1,2)).simulate(2020)).isEqualTo(1836)
    }
}