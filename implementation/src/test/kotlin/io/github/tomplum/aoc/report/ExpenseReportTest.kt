package io.github.tomplum.aoc.report

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Test

class ExpenseReportTest {
    @Test
    fun validateContainsTwoEntriesExampleInput() {
        val input = TestInputReader.read<Int>("report/example.txt").value
        val report = ExpenseReport(input)
        assertThat(report.validateContainsTwoEntries()).isEqualTo(514579)
    }

    @Test
    fun validateContainsThreeEntriesExampleInput() {
        val input = TestInputReader.read<Int>("report/example.txt").value
        val report = ExpenseReport(input)
        assertThat(report.validateContainsThreeEntries()).isEqualTo(241861950)
    }
}