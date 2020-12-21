package io.github.tomplum.aoc.ferry.raft

/**
 * Reads a list of ingredients and their possible allergens and produces an [IngredientList].
 */
class IngredientListReader private constructor() {
    companion object {
        fun read(data: List<String>): IngredientList = data.map { line ->
            val info = line.split(" (contains ")
            val foods = info[0].split(" ")
            val allergens = info[1].dropLast(1).split(", ")
            FoodEntry(foods, allergens)
        }.let { foods -> IngredientList(foods) }
    }
}