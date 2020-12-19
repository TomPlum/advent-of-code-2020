package io.github.tomplum.aoc.forest.satellite

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Test

class MessageReportTest {
    @Test
    fun example() {
        val input = TestInputReader.read<String>("forest/satellite/example-report.txt").asSingleString()
        val report = MessageReader.parse(input)
        assertThat(report.getMessagesMatchingRule(0)).isEqualTo(2)
    }
}