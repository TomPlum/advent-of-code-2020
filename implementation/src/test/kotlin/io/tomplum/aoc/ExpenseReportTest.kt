package io.tomplum.aoc

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Test

class ExpenseReportTest {
    @Test
    fun example() {
        val input = TestInputReader().readInputAsInt("report/example.txt").value
        val report = ExpenseReport(input)
        assertThat(report.repair()).isEqualTo(514579)
    }

    @Test
    fun inputContainsTwoValidNumbersShouldReturnTheirProduct() {
        val input = listOf(523, 1000, 12, 1020, 5)
        val report = ExpenseReport(input)
        assertThat(report.repair()).isEqualTo(1020000)
    }
}