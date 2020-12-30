package io.github.tomplum.aoc.airport.game

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class MemoryGameTest {
   @Nested
   inner class TwentyTwenty {
       @Test
       fun exampleOne() {
           assertThat(MemoryGame("0,3,6", 2020).simulate()).isEqualTo(436)
       }

       @Test
       fun exampleTwo() {
           assertThat(MemoryGame("1,3,2", 2020).simulate()).isEqualTo(1)
       }

       @Test
       fun exampleThree() {
           assertThat(MemoryGame("2,1,3", 2020).simulate()).isEqualTo(10)
       }

       @Test
       fun exampleFour() {
           assertThat(MemoryGame("1,2,3", 2020).simulate()).isEqualTo(27)
       }

       @Test
       fun exampleFive() {
           assertThat(MemoryGame("2,3,1", 2020).simulate()).isEqualTo(78)
       }

       @Test
       fun exampleSix() {
           assertThat(MemoryGame("3,2,1", 2020).simulate()).isEqualTo(438)
       }

       @Test
       fun exampleSeven() {
           assertThat(MemoryGame("3,1,2", 2020).simulate()).isEqualTo(1836)
       }
   }

    @Nested
    inner class ThirtyMillionth {
        @Test
        fun exampleOne() {
            assertThat(MemoryGame("0,3,6", 30000000).simulate()).isEqualTo(175594)
        }

        @Test
        fun exampleTwo() {
            assertThat(MemoryGame("1,3,2", 30000000).simulate()).isEqualTo(2578)
        }

        @Test
        fun exampleThree() {
            assertThat(MemoryGame("2,1,3", 30000000).simulate()).isEqualTo(3544142)
        }

        @Test
        fun exampleFour() {
            assertThat(MemoryGame("1,2,3", 30000000).simulate()).isEqualTo(261214)
        }

        @Test
        fun exampleFive() {
            assertThat(MemoryGame("2,3,1", 30000000).simulate()).isEqualTo(6895259)
        }

        @Test
        fun exampleSix() {
            assertThat(MemoryGame("3,2,1", 30000000).simulate()).isEqualTo(18)
        }

        @Test
        fun exampleSeven() {
            assertThat(MemoryGame("3,1,2", 30000000).simulate()).isEqualTo(362)
        }
    }
}