package io.github.tomplum.aoc.ferry.raft

data class IngredientList(val ingredients: List<FoodEntry>) {
    fun getDistinctFoods(): List<String> = ingredients.flatMap { it.foods }.distinct()

    fun getDistinctAllergens(): Set<String> = ingredients.flatMap { it.allergens }.distinct().toSet()

    fun getAllergenCandidates(): Map<String, List<String>> {
        val map = mutableMapOf<String, List<String>>()
        ingredients.forEach { (foods, allergens) ->
            foods.forEach { food ->
                if (!map.containsKey(food)) {
                    map[food] = allergens
                } else {
                    map[food] = map[food]!!.toMutableList() + allergens
                }
            }
        }
        return map
    }

    fun getFoodCandidates(): Map<String, List<String>> {
        val map = mutableMapOf<String, List<String>>()
        ingredients.forEach { (foods, allergens) ->
            allergens.forEach { allergen ->
                if (!map.containsKey(allergen)) {
                    map[allergen] = foods.toList()
                } else {
                    map[allergen] = map[allergen]!!.toMutableList() + foods
                }
            }
        }
        return map
    }

    fun getReferenceCount(food: String) = ingredients.flatMap { entry -> entry.foods }.count { it == food }
}