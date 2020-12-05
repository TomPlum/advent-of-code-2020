package io.github.tomplum.aoc.benchmark.report

abstract class BenchmarkReport {
    protected fun formatTime(time: Long): String {
        val s = time / 1000
        val ms = time % 1000
        return if (s > 0) "${s}s ${ms}ms" else "${ms}ms"
    }
}