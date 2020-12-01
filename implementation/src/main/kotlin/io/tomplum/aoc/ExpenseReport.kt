package io.tomplum.aoc

import io.github.tomplum.aoc.extensions.cartesianProductCubic
import io.github.tomplum.aoc.extensions.cartesianProductQuadratic
import io.github.tomplum.aoc.extensions.product
import io.github.tomplum.aoc.extensions.sum

/**
 * The Elves in accounting need to fix your expense report; apparently, something isn't quite adding up.
 * Specifically, they need to find n entries that sum to 2020 and then multiply those two numbers together.
 */
class ExpenseReport(private val data: List<Int>) {
    /**
     * Finds the product of the two [data] values whose sum is equal to the target value.
     * @see isTargetSum
     */
    fun validateContainsTwoEntries(): Int = data
            .cartesianProductQuadratic()
            .find { it.sum().isTargetSum()}?.product()
            ?: throw IllegalStateException("The report is already valid.")

    /**
     * Finds the product of the three [data] values whose sum is equal to the target value.
     * @see isTargetSum
     */
    fun validateContainsThreeEntries(): Int = data.cartesianProductCubic()
            .find { it.sum().isTargetSum()}?.product()
            ?: throw IllegalStateException("The report is already valid.")

    private fun Int.isTargetSum(): Boolean = this == 2020
}