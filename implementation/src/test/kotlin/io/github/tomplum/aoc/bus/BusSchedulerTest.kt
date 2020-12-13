package io.github.tomplum.aoc.bus

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Test

class BusSchedulerTest {
    @Test
    fun example() {
        val input = TestInputReader.read<String>("bus/example-notes.txt")
        val scheduler = BusScheduler(ScheduleNotes.fromString(input.value))
        assertThat(scheduler.getEarliestBus()).isEqualTo(295)
    }
}