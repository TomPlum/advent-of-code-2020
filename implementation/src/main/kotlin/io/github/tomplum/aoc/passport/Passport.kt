package io.github.tomplum.aoc.passport

import io.github.tomplum.aoc.passport.strategy.PassportValidationStrategy

data class Passport(private val info: Map<PassportField, String>)  {
    fun isValid(strategy: PassportValidationStrategy): Boolean = strategy.isValid(info)
}