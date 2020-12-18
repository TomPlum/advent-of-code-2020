package io.github.tomplum.aoc.aircraft.homework

enum class Operator: Token {
    ADD {
        override fun apply(t1: Int, t2: Int): Int {
            return t1 + t2
        }
    },
    MULTIPLY {
        override fun apply(t1: Int, t2: Int): Int {
            return t1 * t2
        }
    };

    abstract fun apply(t1: Int, t2: Int): Int
}