package io.github.tomplum.aoc.aircraft.homework

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

    override fun getLength(): Int = 1

    abstract fun apply(t1: Number, t2: Number): Number
}