package io.github.tomplum.aoc.benchmark

class DefaultBenchmarkReport(private val result: BenchmarkResult) : BenchmarkReport() {
    override fun toString(): String {
        val s = StringBuilder("- Advent of Code 2020 Solution Report -\n\n")
        val solutions =  result.results.joinToString("\n") { result ->
            val builder = StringBuilder("[Day ${result.day}]\n")

            builder.append("Part 1: ${result.answer1}\n")
            builder.append(formatExecutionTime(result.runtime1))
            builder.append("\n")
            builder.append("Part 2: ${result.answer2}\n")
            builder.append(formatExecutionTime(result.runtime2))

            builder.toString()
        }
        s.append(solutions).append("\n")

        s.append("Average ${formatExecutionTime(result.getAverageExecutionTime())}")
        s.append("Total ${formatExecutionTime(result.getTotalExecutionTime())}")
        return s.toString()
    }

    private fun formatExecutionTime(runtime: Long): String {
        return "Execution Time: ${formatTime(runtime)}\n"
    }
}