package io.github.tomplum.aoc

import io.github.tomplum.libs.logging.AdventLogger
import kotlin.system.measureTimeMillis

class SolutionRunner {
    companion object {
        //TODO: Serialise runtimes and then read to report deltas
        fun run(vararg solutions: Solution<*>) {
            AdventLogger.error("- Advent of Code 2020 Solution Report -\n")

            val runtime = solutions.map { solution ->
                AdventLogger.error("[${solution.javaClass.simpleName}]")
                runPart(solution, Part.ONE) + runPart(solution, Part.TWO)
            }.sum()


            AdventLogger.error("Total ${formatExecutionTime(runtime)}")
        }

        private fun runPart(solution: Solution<*>, part: Part): Long {
            val runtime = measureTimeMillis {
                val answer = when(part) {
                    Part.ONE -> solution.part1()
                    Part.TWO -> solution.part2()
                }
                AdventLogger.error("Part ${part.value}: $answer")
            }
            AdventLogger.error(formatExecutionTime(runtime))
            return runtime
        }

        private fun formatExecutionTime(milliseconds: Long): String {
            val s = milliseconds / 1000
            val ms = milliseconds % 1000
            if (s > 0) {
                return "Execution Time: ${s}s ${ms}ms\n"
            }
            return "Execution Time: ${ms}ms\n"

        }

        private enum class Part(val value: String) { ONE("1"), TWO("2") }
    }
}