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
            listOf(BusID("7"), BusID("13"), BusID("x"), BusID("x"), BusID("59"), BusID("x"), BusID("31"), BusID("19"))
        )
    }

    @Nested
    inner class GetWorkingBuses {
        @Test
        fun onlyWorkingBuses() {
            val timetable = BusTimetable(940, listOf(BusID("45"), BusID("12"), BusID("4")))
            val working = timetable.getWorkingBuses()
            assertThat(working).containsOnly(BusID("45"), BusID("12"), BusID("4"))
        }

        @Test
        fun onlyOutOfServiceBuses() {
            val timetable = BusTimetable(940, listOf(BusID("x"), BusID("x"), BusID("x")))
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