package io.github.tomplum.aoc.benchmark.utility

import io.github.tomplum.aoc.benchmark.data.BenchmarkComparison
import io.github.tomplum.aoc.benchmark.data.BenchmarkResult
import io.github.tomplum.aoc.benchmark.report.BenchmarkComparisonReport
import io.github.tomplum.aoc.benchmark.report.BenchmarkDefaultReport
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
            AdventLogger.error(BenchmarkDefaultReport(lastRun))
        }
        writer.write(lastRun)
    }
}