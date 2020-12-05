package io.github.tomplum.aoc.benchmark.report

import io.github.tomplum.aoc.benchmark.data.BenchmarkComparison

class BenchmarkComparisonReport(private val comparison: BenchmarkComparison) : BenchmarkReport() {
    private val red = "\u001B[31m"
    private val green = "\u001B[32m"
    private val reset = "\u001B[0m"

    override fun toString(): String {
        val s = StringBuilder("- Advent of Code 2020 Solution Report -\n\n")
        val solutions =  comparison.getDeltas().joinToString("\n") { delta ->
            val builder = StringBuilder("[Day ${delta.day}]\n")

            val lastRun = comparison.lastRun.get(delta.day)

            builder.append("Part 1: ${lastRun.answer1}\n")
            builder.append(formatExecutionTime(lastRun.runtime1, delta.p1))
            builder.append("\n")
            builder.append("Part 2: ${lastRun.answer2}\n")
            builder.append(formatExecutionTime(lastRun.runtime2, delta.p2))

            builder.toString()
        }
        s.append(solutions).append("\n")

        val lastRun = comparison.lastRun
        s.append("Average ${formatExecutionTime(lastRun.getAverageExecutionTime(), comparison.getAverageRuntimeDelta())}")
        s.append("Total ${formatExecutionTime(lastRun.getTotalExecutionTime(), comparison.getTotalExecutionTimeDelta())}")
        return s.toString()
    }

    private fun formatExecutionTime(runtime: Long, delta: Long): String {
        return "Execution Time: ${formatTime(runtime)} (${formatDelta(delta)})\n"
    }

    private fun formatDelta(time: Long): String = when {
        time > 0 -> "$red+${formatTime(time)}$reset"
        time < 0 -> "${green}${formatTime(time)}$reset"
        else -> "No Change"
    }

}