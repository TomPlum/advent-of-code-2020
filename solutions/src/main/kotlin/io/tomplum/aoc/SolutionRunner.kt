package io.tomplum.aoc

class SolutionRunner {
    companion object {
        fun run(solution: Solution<*>) {
            //TODO: Replace println w/Logger once imported
            //TODO: Output the runtime of each solution? Change this to varargs
            println("${solution.javaClass.simpleName} Solutions:")
            val first = solution.part1()
            println("Part 1: $first")
            val second = solution.part2()
            println("Part 2: $second")
        }
    }
}