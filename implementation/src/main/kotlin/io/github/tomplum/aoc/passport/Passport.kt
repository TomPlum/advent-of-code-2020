package io.github.tomplum.aoc.passport

data class Passport(val info: Map<PassportField, String>)  {
    fun isValid(): Boolean = false
}