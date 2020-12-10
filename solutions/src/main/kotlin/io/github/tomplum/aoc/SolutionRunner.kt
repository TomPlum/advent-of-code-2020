package io.github.tomplum.aoc

import io.github.tomplum.aoc.benchmark.data.Benchmark
import io.github.tomplum.aoc.benchmark.data.BenchmarkResult
import io.github.tomplum.aoc.benchmark.utility.BenchmarkUtility
import io.github.tomplum.libs.logging.AdventLogger
import kotlin.system.measureTimeMillis

/**
 * A companion utility class designs to run multiple [Solution] implementations.
 * [AdventLogger.error] is used for logging to prevent lower level logs from disrupting the report.
 */
class SolutionRunner private constructor() {
    companion object {
        fun run(vararg solutions: Solution<*, *>) {
            val result = BenchmarkResult()

            solutions.map { solution ->
                val p1 = runPart(solution, Part.ONE)
                val p2 = runPart(solution, Part.TWO)
                val benchmark = Benchmark(solution.javaClass.dayNumber(), p1.answer, p2.answer, p1.runtime, p2.runtime)
                result.add(benchmark)
            }

            BenchmarkUtility().log(result)
        }

        private fun runPart(solution: Solution<*, *>, part: Part): Answer {
            var answer: Any?
            val runtime = measureTimeMillis {
                answer = when(part) {
                    Part.ONE -> solution.part1()
                    Part.TWO -> solution.part2()
                }
            }
            return Answer(answer, runtime)
        }

        private fun Class<*>.dayNumber() = simpleName.last().toString().toInt()

        private class Answer(val answer: Any?, val runtime: Long)

        private enum class Part { ONE, TWO }
    }
}