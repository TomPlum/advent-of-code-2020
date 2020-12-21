package io.github.tomplum.aoc.ferry.raft

data class IngredientList(private val ingredients: List<FoodEntry>) {
    fun getDistinctFoods(): List<String> = getFoods().distinct()

    fun getAllergenicFoods(): Map<String, List<String>> {
        return getDistinctAllergens().map { allergen ->
            val relevantFoods = getEntriesWithAllergen(allergen).map { it.foods }
            val foods = getDistinctFoods().filter { food -> relevantFoods.all { foodList -> foodList.contains(food) } }
            allergen to foods
        }.toMap()
    }

    fun getReferenceCount(food: String) = getFoods().count { it == food }

    private fun getFoods(): List<String> = ingredients.flatMap { entry -> entry.foods }

    private fun getDistinctAllergens(): Set<String> = ingredients.flatMap { it.allergens }.distinct().toSet()

    private fun getEntriesWithAllergen(allergen: String): List<FoodEntry> = ingredients.filter { entry ->
        entry.allergens.contains(allergen)
    }
}