package io.github.tomplum.aoc.bus

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Test

class ScheduleNotesTest {
    @Test
    fun parseExampleInput() {
        val input = TestInputReader.read<String>("bus/example-notes.txt")
        val notes = ScheduleNotes.fromString(input.value)
        assertThat(notes).isEqualTo(ScheduleNotes(939, listOf(7, 13, 59, 31, 19)))
    }

}