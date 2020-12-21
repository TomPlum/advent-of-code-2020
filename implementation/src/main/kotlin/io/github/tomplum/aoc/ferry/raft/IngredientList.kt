package io.github.tomplum.aoc.ferry.raft

data class IngredientList(val ingredients: List<FoodEntry>) {
    fun getDistinctFoods(): List<String> = ingredients.flatMap { it.foods }.distinct()

    fun getDistinctAllergens(): Set<String> = ingredients.flatMap { it.allergens }.distinct().toSet()

    fun getEntriesWithAllergen(allergen: String): List<FoodEntry> = ingredients.filter {
            entry -> entry.allergens.contains(allergen)
    }

    fun getAllergenicFoods(): Map<String, List<String>> {
        return getDistinctAllergens().map { allergen ->
            val relevantFoods = getEntriesWithAllergen(allergen).map { it.foods }
            val foods = getDistinctFoods().filter { food -> relevantFoods.all { foodList -> foodList.contains(food) } }
            allergen to foods
        }.toMap()
    }

    fun getReferenceCount(food: String) = ingredients.flatMap { entry -> entry.foods }.count { it == food }
}