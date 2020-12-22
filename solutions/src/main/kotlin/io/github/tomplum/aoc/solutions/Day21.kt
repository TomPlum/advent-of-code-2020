package io.github.tomplum.aoc.solutions

import io.github.tomplum.aoc.Solution
import io.github.tomplum.aoc.raft.food.AllergenAssessment
import io.github.tomplum.aoc.raft.food.IngredientListReader
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day21 : Solution<Int, String> {
    private val input = InputReader.read<String>(Day(21))
    private val ingredients = IngredientListReader.read(input.value)

    override fun part1(): Int {
        return AllergenAssessment(ingredients).getNonAllergenicFoods()
    }

    override fun part2(): String {
        return AllergenAssessment(ingredients).getCanonicalDangerousIngredientList()
    }
}