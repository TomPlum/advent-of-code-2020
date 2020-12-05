package io.github.tomplum.aoc.airport.passport

import io.github.tomplum.aoc.airport.passport.strategy.PassportValidationStrategy

class PassportScanner(private val strategy: PassportValidationStrategy) {
    fun scan(passports: List<Passport>): Int = passports.filter { it.isValid(strategy) }.count()
}