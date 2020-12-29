package io.github.tomplum.aoc.raft.food

/**
 * An assessment of food-stuffs to be taken on the raft to the vacation island.
 * @param ingredients A list of ingredients and some of their allergens.
 */
class AllergenAssessment(private val ingredients: IngredientList) {
    /**
     * Determines which food cannot possibly contains any of the allergens in the [ingredients].
     * @return The number of times all the non-allergenic foods appear in the [ingredients].
     */
    fun getNonAllergenicFoods(): Int {
        val allergenicFoods = ingredients.getAllergenicFoods().flatMap { it.value }.distinct()
        val nonAllergenicFoods = ingredients.getDistinctFoods() - allergenicFoods
        return nonAllergenicFoods.sumBy { food -> ingredients.getReferenceCount(food) }
    }

    /**
     * Determines which foods have which allergens.
     * @return A canonical list of all allergenic foods.
     */
    fun getCanonicalDangerousIngredientList(): String {
        val allergenicFoodCandidates = ingredients.getAllergenicFoods().toMutableMap()
        val isolatedAllergies = FoodAllergies()
        var allergenicFoods = allergenicFoodCandidates.flatMap { it.value }

        while(allergenicFoodCandidates.isNotEmpty()) {
            val food = allergenicFoods.find { food -> allergenicFoods.count { it == food } == 1 }!!
            val allergen = allergenicFoodCandidates.entries.find { (_, foods) -> foods.contains(food) }!!.key
            isolatedAllergies.add(allergen, food)
            allergenicFoodCandidates.remove(allergen)
            allergenicFoods = allergenicFoodCandidates.flatMap { it.value }.toMutableList()
        }

        return isolatedAllergies.getIngredientList()
    }
}