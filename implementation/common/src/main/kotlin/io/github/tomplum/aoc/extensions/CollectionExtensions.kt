package io.github.tomplum.aoc.extensions

/**
 * For two sets A and B, the Cartesian product of A and B is denoted by A×B and defined as:
 * A×B = { (a,b) | aϵA and bϵB }
 *
 * Put simply, the Cartesian Product is the multiplication of two sets to form the set of all ordered pairs.
 * This function returns the cartesian product of itself and the given set, meaning A and B are [this] and [other].
 *
 * @see cartesianProduct for a variant that returns the product of itself.
 */
fun <S, T> List<S>.cartesianProduct(other: List<T>) = this.flatMap {
    List(other.size){ i -> Pair(it, other[i]) }
}

/**
 * For two sets A and B, the Cartesian product of A and B is denoted by A×B and defined as:
 * A×B = { (a,b) | aϵA and bϵB }
 *
 * Put simply, the Cartesian Product is the multiplication of two sets to form the set of all ordered pairs.
 * This function returns the cartesian product of itself, meaning both A and B are simply [this].
 *
 * @see cartesianProduct for a variant that accepts another set.
 */
fun <T> List<T>.cartesianProduct(): List<Pair<T, T>> = this.flatMap {
    List(this.size){ i -> Pair(it, this[i]) }
}