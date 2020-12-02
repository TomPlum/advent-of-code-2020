package io.github.tomplum.aoc.password

interface CorporatePolicy {
    fun apply(password: String): Boolean
}