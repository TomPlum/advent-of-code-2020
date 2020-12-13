package io.github.tomplum.aoc.bus

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class BusSchedulerTest {
    @Nested
    inner class GetEarliestBus {
        @Test
        fun example() {
            val input = TestInputReader.read<String>("bus/example-notes.txt")
            val scheduler = BusScheduler(BusTimetable.fromNotes(input.value))
            assertThat(scheduler.getEarliestBus()).isEqualTo(295)
        }
    }

    @Nested
    inner class GetOffsetDepartureTimestamp {
        @Test
        fun exampleOne() {
            val input = TestInputReader.read<String>("bus/example-notes.txt")
            val scheduler = BusScheduler(BusTimetable.fromNotes(input.value))
            assertThat(scheduler.getOffsetDepartureTime()).isEqualTo(1068781)
        }

        @Test
        fun exampleTwo() {
            val input = TestInputReader.read<String>("bus/example-notes-2.txt")
            val scheduler = BusScheduler(BusTimetable.fromNotes(input.value))
            assertThat(scheduler.getOffsetDepartureTime()).isEqualTo(3417)
        }

        @Test
        fun exampleThree() {
            val input = TestInputReader.read<String>("bus/example-notes-3.txt")
            val scheduler = BusScheduler(BusTimetable.fromNotes(input.value))
            assertThat(scheduler.getOffsetDepartureTime()).isEqualTo(754018)
        }

        @Test
        fun exampleFour() {
            val input = TestInputReader.read<String>("bus/example-notes-4.txt")
            val scheduler = BusScheduler(BusTimetable.fromNotes(input.value))
            assertThat(scheduler.getOffsetDepartureTime()).isEqualTo(779210)
        }

        @Test
        fun exampleFive() {
            val input = TestInputReader.read<String>("bus/example-notes-5.txt")
            val scheduler = BusScheduler(BusTimetable.fromNotes(input.value))
            assertThat(scheduler.getOffsetDepartureTime()).isEqualTo(1261476)
        }

        @Test
        fun exampleSix() {
            val input = TestInputReader.read<String>("bus/example-notes-6.txt")
            val scheduler = BusScheduler(BusTimetable.fromNotes(input.value))
            assertThat(scheduler.getOffsetDepartureTime()).isEqualTo(1202161486)
        }
    }
}