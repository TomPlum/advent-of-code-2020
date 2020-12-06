package io.github.tomplum.aoc.solutions

import io.github.tomplum.aoc.Solution
import io.github.tomplum.aoc.airport.customs.Questionnaire
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day6 : Solution<Int> {
    override fun part1(): Int {
        val input = InputReader.read<String>(Day(6)).asSingleString()
        val questionnaire = Questionnaire(input)
        return questionnaire.getCorrectQuestionCount()
    }
}