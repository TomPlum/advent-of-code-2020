package io.github.tomplum.aoc.aircraft.homework.types

/**
 * A single mathematical operator in an [Expression].
 */
enum class Operator: Token {
    ADD {
        override fun apply(t1: Number, t2: Number): Number {
            return Number(t1.value + t2.value)
        }

        override fun toString(): String = "+"
    },
    MULTIPLY {
        override fun apply(t1: Number, t2: Number): Number {
            return Number(t1.value * t2.value)
        }

        override fun toString(): String = "*"
    };

    /**
     * The length of an operator with respect to its status
     * as a [Token] in an [Expression] is always 1.
     */
    override fun getLength(): Int = 1

    /**
     * Applies the mathematical function of the given operator on the numbers either side of it.
     * @param t1 The first term
     * @param t2 The second term
     * @return The result
     */
    abstract fun apply(t1: Number, t2: Number): Number
}