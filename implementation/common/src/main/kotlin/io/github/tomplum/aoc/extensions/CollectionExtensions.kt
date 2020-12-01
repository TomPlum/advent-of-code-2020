package io.github.tomplum.aoc.extensions

fun <S, T> List<S>.cartesianProduct(other: List<T>) = this.flatMap {
    List(other.size){ i -> Pair(it, other[i]) }
}

fun <T> List<T>.cartesianProduct(): List<Pair<T, T>> = this.flatMap {
    List(this.size){ i -> Pair(it, this[i]) }
}