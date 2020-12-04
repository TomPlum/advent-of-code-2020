package io.github.tomplum.aoc.passport.strategy

import io.github.tomplum.aoc.passport.PassportField

interface PassportValidationStrategy {
    fun isValid(info: Map<PassportField, String>): Boolean
}