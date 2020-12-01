package io.tomplum.aoc

import io.github.tomplum.aoc.extensions.cartesianProduct
import io.github.tomplum.aoc.extensions.product
import io.github.tomplum.aoc.extensions.sum

private const val TARGET_SUM = 2020

class ExpenseReport(private val data: List<Int>) {

    fun repair(): Int = data
            .cartesianProduct()
            .find { it.sum() == TARGET_SUM }?.product()
            ?: throw IllegalStateException("The report is already valid.")
}