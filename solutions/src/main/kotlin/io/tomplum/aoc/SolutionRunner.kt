package io.tomplum.aoc

import kotlin.system.measureTimeMillis

class SolutionRunner {
    companion object {
        fun run(solution: Solution<*>) {
            //TODO: Replace println w/Logger once imported
            //TODO: Output the runtime of each solution? Change this to varargs
            println("${solution.javaClass.simpleName} Solutions:\n")
            val firstTime = measureTimeMillis {
                val first = solution.part1()
                println("Part 1: $first")
            }
            println(formatExecutionTime(firstTime))

            println("")

            val secondTime = measureTimeMillis {
                val second = solution.part2()
                println("Part 2: $second")
            }
            println(formatExecutionTime(secondTime))

            println("\nTotal ${formatExecutionTime(firstTime + secondTime)}")
        }

        private fun formatExecutionTime(milliseconds: Long): String {
            val s = milliseconds / 1000
            val ms = milliseconds % 1000
            return "Execution Time: ${s}s ${ms}ms"
        }

    }
}