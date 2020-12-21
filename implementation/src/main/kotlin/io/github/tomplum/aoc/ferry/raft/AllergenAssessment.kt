package io.github.tomplum.aoc.ferry.raft

class AllergenAssessment(private val ingredients: IngredientList) {
    fun getNonAllergenicFoods(): Int {
        val allergenicFoods = ingredients.getAllergenicFoods().flatMap { it.value }.distinct()
        val nonAllergenicFoods = ingredients.getDistinctFoods() - allergenicFoods
        return nonAllergenicFoods.sumBy { food -> ingredients.getReferenceCount(food) }
    }

    fun getCanonicalDangerousIngredientList(): String {
        val allergenicFoodCandidates = ingredients.getAllergenicFoods().toMutableMap()
        val isolatedAllergies = FoodAllergies()
        var allergenicFoods = allergenicFoodCandidates.flatMap { it.value }

        while(allergenicFoodCandidates.isNotEmpty()) {
            val food = allergenicFoods.find { food -> allergenicFoods.count { it == food } == 1 }!!
            val allergen = allergenicFoodCandidates.entries.find { (_, foods) -> foods.contains(food) }!!.key
            isolatedAllergies.add(food, allergen)
            allergenicFoodCandidates.remove(allergen)
            allergenicFoods = allergenicFoodCandidates.flatMap { it.value }.toMutableList()
        }

        return isolatedAllergies.getIngredientList()
    }
}