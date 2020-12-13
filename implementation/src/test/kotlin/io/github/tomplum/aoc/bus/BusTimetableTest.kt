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

    @Nested
    inner class GetBusesWithOffsets {
        @Test
        fun exampleOne() {
            val notes = TestInputReader.read<String>("bus/example-notes.txt")
            val offsets = BusTimetable.fromNotes(notes.value).getBusesWithOffsets()
            assertThat(offsets).containsOnly(Pair(0,7), Pair(1,13), Pair(4,59), Pair(6,31), Pair(7,19))
        }

        @Test
        fun exampleTwo() {
            val notes = TestInputReader.read<String>("bus/example-notes-2.txt")
            val offsets = BusTimetable.fromNotes(notes.value).getBusesWithOffsets()
            assertThat(offsets).containsOnly(Pair(0,17), Pair(2,13), Pair(3,19))
        }

        @Test
        fun exampleThree() {
            val notes = TestInputReader.read<String>("bus/example-notes-3.txt")
            val offsets = BusTimetable.fromNotes(notes.value).getBusesWithOffsets()
            assertThat(offsets).containsOnly(Pair(0,67), Pair(1,7), Pair(2,59), Pair(3,61))
        }

        @Test
        fun exampleFour() {
            val notes = TestInputReader.read<String>("bus/example-notes-4.txt")
            val offsets = BusTimetable.fromNotes(notes.value).getBusesWithOffsets()
            assertThat(offsets).containsOnly(Pair(0,67), Pair(2,7), Pair(3,59), Pair(4,61))
        }

        @Test
        fun exampleFive() {
            val notes = TestInputReader.read<String>("bus/example-notes-5.txt")
            val offsets = BusTimetable.fromNotes(notes.value).getBusesWithOffsets()
            assertThat(offsets).containsOnly(Pair(0,67), Pair(1,7), Pair(3,59), Pair(4,61))
        }

        @Test
        fun exampleSix() {
            val notes = TestInputReader.read<String>("bus/example-notes-6.txt")
            val offsets = BusTimetable.fromNotes(notes.value).getBusesWithOffsets()
            assertThat(offsets).containsOnly(Pair(0,1789), Pair(1,37), Pair(2,47), Pair(3,1889))
        }
    }
}