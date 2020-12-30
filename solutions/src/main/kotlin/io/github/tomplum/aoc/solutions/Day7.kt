package io.github.tomplum.aoc.solutions

import io.github.tomplum.aoc.Solution
import io.github.tomplum.aoc.airport.luggage.LuggageProcessor
import io.github.tomplum.aoc.airport.luggage.LuggageRuleParser
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day7 : Solution<Int, Int> {
    private val input = InputReader.read<String>(Day(7))
    private val luggage = LuggageRuleParser.parse(input.value)
    private val processor = LuggageProcessor(luggage)

    override fun part1(): Int {
        return processor.getBagColoursContaining("shiny gold")
    }

    override fun part2(): Int {
        return processor.getBagRequirement("shiny gold")
    }
}