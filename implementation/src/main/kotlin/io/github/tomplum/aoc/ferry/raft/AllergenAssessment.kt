package io.github.tomplum.aoc.ferry.raft

class AllergenAssessment(val ingredientList: IngredientList) {
    fun getNonAllergenicFoods(): Int {
        val foods = ingredientList.getDistinctFoods()
        val allergens = ingredientList.getDistinctAllergens()

        val allergenicFoods = allergens.flatMap { allergen ->
            val relevantFoods = ingredientList.ingredients.filter { entry -> entry.allergens.contains(allergen) }.map { it.foods }
            foods.mapNotNull { food ->
                if (relevantFoods.all { foodList -> foodList.contains(food) }) food else null
            }
        }.distinct()

        val nonAllergenicFoods = foods - allergenicFoods
        return nonAllergenicFoods.sumBy { food -> ingredientList.getReferenceCount(food) }
    }
}