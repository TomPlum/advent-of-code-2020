package io.tomplum.aoc

import io.github.tomplum.aoc.extensions.cartesianProductQuadratic
import io.github.tomplum.aoc.extensions.product
import io.github.tomplum.aoc.extensions.sum

class ExpenseReport(private val data: List<Int>) {
    fun validateContainsTwoEntries(): Int = data
            .cartesianProductQuadratic()
            .find { it.sum().isTargetSum()}?.product()
            ?: throw IllegalStateException("The report is already valid.")

    fun validateContainsThreeEntries(): Int = data.cartesianProductQuadratic()
            .find { it.sum().isTargetSum()}?.product()
            ?: throw IllegalStateException("The report is already valid.")

    private fun Int.isTargetSum(): Boolean = this == 2020
}