package io.github.tomplum.aoc.raft.food

/**
 * An assessment of food-stuffs to be taken on the raft to the vacation island.
 * @param ingredients A list of ingredients and some of their allergens.
 */
class AllergenAssessment(private val ingredients: IngredientList) {
    /**
     * Determines which food cannot possibly contain any of the allergens in the [ingredients].
     * @return The number of times all the non-allergenic foods appear in the [ingredients].
     */
    fun getNonAllergenicFoods(): Int {
        val allergenicFoods = ingredients.getAllergenicFoods().values.flatten().distinct()
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
        var allergenicFoods = allergenicFoodCandidates.values.flatten()

        while(allergenicFoodCandidates.isNotEmpty()) {
            val food = allergenicFoods.find { target -> allergenicFoods.count { food -> food == target } == 1 }!!
            val allergen = allergenicFoodCandidates.entries.find { (_, foods) -> foods.contains(food) }!!.key
            isolatedAllergies.add(allergen, food)
            allergenicFoodCandidates.remove(allergen)
            allergenicFoods = allergenicFoodCandidates.values.flatten()
        }

        return isolatedAllergies.getIngredientList()
    }
}