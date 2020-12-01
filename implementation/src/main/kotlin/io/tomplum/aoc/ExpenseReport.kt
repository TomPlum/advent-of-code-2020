package io.tomplum.aoc

class ExpenseReport(private val data: List<Int>) {
    fun repair(): Int {
        data.forEachIndexed { i, first ->
            data.drop(i).forEach { second ->
                if (first + second == 2020) {
                    return first * second
                }
            }
        }
        throw IllegalStateException("The report is already valid.")
    }
}