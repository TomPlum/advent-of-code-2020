package io.github.tomplum.aoc.ferry.raft

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