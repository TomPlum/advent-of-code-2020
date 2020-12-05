package io.github.tomplum.aoc.benchmark.data

class BenchmarkComparison(private val previousRun: BenchmarkResult, val lastRun: BenchmarkResult) {
    fun getDeltas(): List<BenchmarkDelta> = previousRun.results.zip(lastRun.results).map { (previous, last) ->
        BenchmarkDelta(previous.day, last.runtime1 - previous.runtime1, last.runtime2 - previous.runtime2)
    }

    fun getAverageRuntimeDelta(): Long = lastRun.getAverageExecutionTime() - previousRun.getAverageExecutionTime()

    fun getTotalExecutionTimeDelta(): Long = lastRun.getTotalExecutionTime() - previousRun.getTotalExecutionTime()
}