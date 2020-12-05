package io.github.tomplum.aoc.benchmark

import io.github.tomplum.libs.logging.AdventLogger

class BenchmarkUtility {
    private val reader = BenchmarkReader()
    private val writer = BenchmarkWriter()

    fun log(lastRun: BenchmarkResult) {
        val previousRun = reader.read()
        if (previousRun != null) {
            val comparison = BenchmarkComparison(previousRun, lastRun)
            AdventLogger.error(BenchmarkComparisonReport(comparison))
        } else {
            AdventLogger.error(DefaultBenchmarkReport(lastRun))
        }
        writer.write(lastRun)
    }
}