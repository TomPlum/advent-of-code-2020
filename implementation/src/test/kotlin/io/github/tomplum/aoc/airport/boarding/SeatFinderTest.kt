package io.github.tomplum.aoc.airport.boarding

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class SeatFinderTest {
    @Nested
    inner class SanityCheck {
        @Test
        fun emptyBoardingPassList() {
            assertThrows<NoSuchElementException> { SeatFinder(emptyList()).sanityCheck() }
        }

        @Test
        fun singlePassShouldReturnsItsID() {
            assertThat(SeatFinder(listOf(BoardingPass(2, 4))).sanityCheck()).isEqualTo(20)
        }

        @Test
        fun multiplePassesShouldReturnHighestSeatID() {
            val passes = listOf(BoardingPass(2, 4), BoardingPass(10, 5))
            val id = SeatFinder(passes).sanityCheck()
            assertThat(id).isEqualTo(85)
        }
    }

    @Nested
    inner class MissingSeats {
        @Test
        fun onlyMissingSeatShouldBeReturned() {
            val passes = listOf(BoardingPass(1,1), BoardingPass(1,2), BoardingPass(1,4), BoardingPass(1,5))
            val missing = SeatFinder(passes).getMissingSeat()
            assertThat(missing).isEqualTo(11)
        }

        @Test
        fun frontSeatShouldBeOmittedFromSearch() {
            val passes = listOf(BoardingPass(1,1), BoardingPass(1,3), BoardingPass(1,5), BoardingPass(1,6))
            val missing = SeatFinder(passes).getMissingSeat()
            assertThat(missing).isEqualTo(12)
        }

        @Test
        fun backSeatShouldBeOmittedFromSearch() {
            val passes = listOf(BoardingPass(1,1), BoardingPass(1,2), BoardingPass(1,4), BoardingPass(1,6))
            val missing = SeatFinder(passes).getMissingSeat()
            assertThat(missing).isEqualTo(11)
        }

        @Test
        fun noMissingSeats() {
            val passes = listOf(BoardingPass(1,1), BoardingPass(1,2), BoardingPass(1,3), BoardingPass(1,6))
            val e = assertThrows<IllegalStateException> { SeatFinder(passes).getMissingSeat() }
            assertThat(e.message).isEqualTo("There are no missing seats!")
        }
    }
}