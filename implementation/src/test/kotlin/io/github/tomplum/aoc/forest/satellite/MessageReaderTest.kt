package io.github.tomplum.aoc.forest.satellite

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Test

class MessageReaderTest {
    @Test
    fun example() {
        val input = TestInputReader.read<String>("forest/satellite/example-report.txt").asSingleString()
        val report = MessageReader.parse(input)
        assertThat(report).isEqualTo(getExpectedMessageReport())
    }
    private fun getExpectedMessageReport(): MessageReport {
        val rule5 = MatchRule('b')
        val rule4 = MatchRule('a')
        val rule3 = OrRule(listOf(listOf(4, 5), listOf(5, 4)))
        val rule2 = OrRule(listOf(listOf(4, 4), listOf(5, 5)))
        val rule1 = OrRule(listOf(listOf(2, 3), listOf(3, 2)))
        val rule0 = LinearRule(listOf(4, 1, 5))

        val messages = listOf("ababbb", "bababa", "abbbab", "aaabbb", "aaaabbb").map { Message(it) }

        return MessageReport(listOf(0 to rule0, 1 to rule1, 2 to rule2, 3 to rule3, 4 to rule4, 5 to rule5).toMap().toMutableMap(), messages)
    }
}