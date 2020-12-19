package io.github.tomplum.aoc.forest.satellite

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.forest.satellite.rule.OrRule
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Test

class MessageReportTest {
    @Test
    fun example() {
        val input = TestInputReader.read<String>("forest/satellite/example-report.txt").asSingleString()
        val report = MessageReader.parse(input)
        assertThat(report.getMessagesMatchingRule(0)).isEqualTo(2)
    }

    @Test
    fun exampleTwo() {
        val input = TestInputReader.read<String>("forest/satellite/example-report-2.txt").asSingleString()
        val report = MessageReader.parse(input)
        report.replaceRules(listOf(8 to OrRule(listOf(listOf(42), listOf(42, 8))), 11 to OrRule(listOf(listOf(42, 31), listOf(42, 11, 31)))).toMap())
        assertThat(report.getMessagesMatchingRule(0)).isEqualTo(12)
    }
}