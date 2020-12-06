package io.github.tomplum.aoc.benchmark.report

abstract class DeltaReport : BenchmarkReport() {
    protected fun formatExecutionTime(runtime: Long, delta: Long): String {
        return "${formatTime(runtime)} (${formatDelta(delta)})\n"
    }

    private fun formatDelta(time: Long): String = when {
        time > 0 -> "+${formatTime(time).coloured(Colour.RED)}"
        time < 0 -> formatTime(time).coloured(Colour.GREEN)
        else -> "No Change"
    }
}