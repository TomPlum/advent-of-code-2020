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
        val rule5 = BaseRule(5, 'b')
        val rule4 = BaseRule(4, 'a')
        val rule3 = OrRule(3, AndRule(rule4, rule5), AndRule(rule5, rule4))
        val rule2 = OrRule(2, AndRule(rule4, rule4), AndRule(rule5, rule5))
        val rule1 = OrRule(1, AndRule(rule2, rule3), AndRule(rule3, rule2))
        val rule0 = LinearRule(0, listOf(rule4, rule1, rule5))

        val messages = listOf("ababbb", "bababa", "abbbab", "aaabbb", "aaaabbb").map { Message(it) }

        return MessageReport(listOf(rule0, rule1, rule2, rule3, rule4, rule5), messages)
    }
}