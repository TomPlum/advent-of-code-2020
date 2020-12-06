package io.github.tomplum.aoc.benchmark.utility

import io.github.tomplum.aoc.benchmark.data.BenchmarkComparison
import io.github.tomplum.aoc.benchmark.data.BenchmarkResult
import io.github.tomplum.aoc.benchmark.report.BenchmarkCompactReport
import io.github.tomplum.aoc.benchmark.report.BenchmarkComparisonReport
import io.github.tomplum.aoc.benchmark.report.BenchmarkDefaultReport
import io.github.tomplum.aoc.benchmark.report.ReportingMode
import io.github.tomplum.libs.logging.AdventLogger

class BenchmarkUtility {
    private val reader = BenchmarkReader()
    private val writer = BenchmarkWriter()

    fun log(lastRun: BenchmarkResult) {
        val previousRun = reader.read()
        if (previousRun != null) {
            val comparison = BenchmarkComparison(previousRun, lastRun)
            val mode = when(System.getProperty("report")) {
                "verbose" -> ReportingMode.VERBOSE
                "compact" -> ReportingMode.COMPACT
                else -> ReportingMode.DEFAULT
            }

            when(mode) {
                ReportingMode.VERBOSE -> AdventLogger.error(BenchmarkComparisonReport(comparison))
                ReportingMode.COMPACT -> AdventLogger.error(BenchmarkCompactReport(comparison))
                ReportingMode.DEFAULT -> AdventLogger.error(BenchmarkComparisonReport(comparison))
            }

        } else {
            AdventLogger.error(BenchmarkDefaultReport(lastRun))
        }
        writer.write(lastRun)
    }
}