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
        val rule0 = LinearRule(0, listOf(4, 1, 5))
        val rule1 = OrRule(1, Pair(2, 3), Pair(3,2))
        val rule2 = OrRule(2, Pair(4, 4), Pair(5, 5))
        val rule3 = OrRule(3, Pair(4, 5), Pair(5, 4))
        val rule4 = BaseRule(4, 'a')
        val rule5 = BaseRule(5, 'b')

        val messages = listOf("ababbb", "bababa", "abbbab", "aaabbb", "aaaabbb").map { Message(it) }

        return MessageReport(listOf(rule0, rule1, rule2, rule3, rule4, rule5), messages)
    }
}