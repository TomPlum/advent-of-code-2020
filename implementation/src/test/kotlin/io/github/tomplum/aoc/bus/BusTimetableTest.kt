package io.github.tomplum.aoc.bus

import assertk.assertThat
import assertk.assertions.containsOnly
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class BusTimetableTest {
    @Nested
    inner class FromNotes {
        @Test
        fun parseExampleInput() {
            val input = TestInputReader.read<String>("bus/example-notes.txt")
            val timetable = BusTimetable.fromNotes(input.value)
            assertThat(timetable).isEqualTo(getExpectedTimetable())
        }

        private fun getExpectedTimetable() = BusTimetable(
            939,
            listOf(Bus("7"), Bus("13"), Bus("x"), Bus("x"), Bus("59"), Bus("x"), Bus("31"), Bus("19"))
        )
    }

    @Nested
    inner class GetWorkingBuses {
        @Test
        fun onlyWorkingBuses() {
            val timetable = BusTimetable(940, listOf(Bus("45"), Bus("12"), Bus("4")))
            val working = timetable.getWorkingBuses()
            assertThat(working).containsOnly(Bus("45"), Bus("12"), Bus("4"))
        }

        @Test
        fun onlyOutOfServiceBuses() {
            val timetable = BusTimetable(940, listOf(Bus("x"), Bus("x"), Bus("x")))
            val working = timetable.getWorkingBuses()
            assertThat(working).isEmpty()
        }

        @Test
        fun noBuses() {
            val timetable = BusTimetable(940, emptyList())
            val working = timetable.getWorkingBuses()
            assertThat(working).isEmpty()
        }
    }
}