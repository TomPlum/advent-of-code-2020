package io.github.tomplum.aoc.ferry.raft

class FoodAllergies {
    private val data = mutableMapOf<String, String>()

    fun add(food: String, allergen: String) {
        data[allergen] = food
    }

    fun getIngredientList(): String = data.toSortedMap().values.joinToString(",")
}