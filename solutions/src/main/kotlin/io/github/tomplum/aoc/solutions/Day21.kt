package io.github.tomplum.aoc.solutions

import io.github.tomplum.aoc.Solution
import io.github.tomplum.aoc.ferry.raft.AllergenAssessment
import io.github.tomplum.aoc.ferry.raft.IngredientListReader
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day21 : Solution<Int, Int> {
    private val input = InputReader.read<String>(Day(21))

    override fun part1(): Int {
        val ingredients = IngredientListReader.read(input.value)
        val assessment = AllergenAssessment(ingredients)
        return assessment.getNonAllergenicFoods()
    }
}