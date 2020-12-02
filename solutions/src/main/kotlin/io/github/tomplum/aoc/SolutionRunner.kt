package io.github.tomplum.aoc

import kotlin.system.measureTimeMillis

class SolutionRunner {
    companion object {
        //TODO: Replace println w/Logger once imported
        fun run(vararg solutions: Solution<*>) {
            println("- Advent of Code 2020 Solution Report -\n")

            val runtime = solutions.map { solution ->
                println("[${solution.javaClass.simpleName}]")
                val firstTime = measureTimeMillis {
                    val first = solution.part1()
                    println("Part 1: $first")
                }
                println(formatExecutionTime(firstTime))

                val secondTime = measureTimeMillis {
                    val second = try {
                        "Part 2: ${solution.part2()}"
                    } catch (e: UnsupportedOperationException) {
                        e.message
                    }
                    println(second)
                }
                println(formatExecutionTime(secondTime))

                firstTime + secondTime
            }.sum()


            println("Total ${formatExecutionTime(runtime)}")
        }

        private fun formatExecutionTime(milliseconds: Long): String {
            val s = milliseconds / 1000
            val ms = milliseconds % 1000
            return "Execution Time: ${s}s ${ms}ms\n"
        }

    }
}