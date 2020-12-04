package io.github.tomplum.aoc.report

/**
 * The Elves in accounting need to fix your expense report; apparently, something isn't quite adding up.
 * Specifically, they need to find n entries that sum to 2020 and then multiply those two numbers together.
 *
 * @param data The values of the expenses in the report.
 */
class ExpenseReport(private val data: List<Int>) {
    /**
     * Finds the product of the two [data] values whose sum is equal to the target value.
     * @see isTargetSum
     */
    fun validateContainsTwoEntries(): Int = data.flatMap { i ->
        data.map { j -> if ((i + j).isTargetSum()) i * j else null }
    }.filterNotNull().first()

    /**
     * Finds the product of the three [data] values whose sum is equal to the target value.
     * @see isTargetSum
     */
    fun validateContainsThreeEntries(): Int = data.flatMap { i -> data.flatMap { j ->
        data.map { k -> if ((i + j + k).isTargetSum()) i * j * k else null
    }}}.filterNotNull().first()

    private fun Int.isTargetSum(): Boolean = this == 2020
}