package io.github.tomplum.aoc.extensions

/**
 * Returns the product of all of the integers in the given list.
 */
fun List<Int>.product(): Int = if (isNotEmpty()) reduce { product, next -> product * next } else 0

/**
 * For two sets A and B, the Cartesian product of A and B is denoted by A×B and defined as:
 * A×B = { (a,b) | aϵA and bϵB }
 *
 * Put simply, the Cartesian Product is the multiplication of two sets to form the set of all ordered pairs.
 * This function returns the cartesian product of itself and the given set, meaning A and B are [this] and [other].
 *
 * @see cartesianProductQuadratic for a variant that returns the product of itself.
 */
fun <S, T> List<S>.cartesianProduct(other: List<T>): List<Pair<S, T>> = this.flatMap {
    List(other.size){ i -> Pair(it, other[i]) }
}

/**
 * For two sets A and B, the Cartesian product of A and B is denoted by A×B and defined as:
 * A×B = { (a,b) | aϵA and bϵB }
 *
 * Put simply, the Cartesian Product is the multiplication of two sets to form the set of all ordered pairs.
 * This function returns the cartesian product of itself, meaning both A and B are simply [this].
 *
 * @see cartesianProductCubic for a variant that accepts another set.
 */
fun <T> List<T>.cartesianProductQuadratic(): List<Pair<T, T>> = this.flatMap {
    List(this.size){ i -> Pair(it, this[i]) }
}

/**
 * For three sets A, B and C, the Cartesian product of A, B and C is denoted by A×B×C and defined as:
 * A×B×C = { (p, q, r) | pϵA and qϵB and rϵC }
 *
 * Put simply, the Cartesian Product is the multiplication of three sets to form the set of all ordered pairs.
 * This function returns the cartesian product of itself and the given sets, meaning that A, B & C are all [this].
 *
 * @see cartesianProductQuadratic for a variation that simply finds the product of itself.
 */
fun <T> List<T>.cartesianProductCubic(): List<Triple<T, T, T>> = cartesianProduct(this, this, this).map {
    Triple(it[0], it[1], it[2])
}

/**
 * For three sets A, B and C, the Cartesian product of A, B and C is denoted by A×B×C and defined as:
 * A×B×C = { (p, q, r) | pϵA and qϵB and rϵC }
 *
 * Put simply, the Cartesian Product is the multiplication of three sets to form the set of all ordered pairs.
 * This function returns the cartesian product of itself and the given sets, meaning both A, B and C are [this],
 * [second] and [third] respectively.
 */
fun <T> List<T>.cartesianProductCubic(second: List<T>, third: List<T>): List<Triple<T, T, T>> =
        cartesianProduct(this, second, third).map { Triple(it[0], it[1], it[2]) }

/**
 * Finds the Cartesian Product of any number of given [sets].
 */
private fun <T> cartesianProduct(vararg sets: List<T>): List<List<T>> = sets.fold(listOf(listOf())) { acc, set ->
    acc.flatMap { list -> set.map { element -> list + element } }
}