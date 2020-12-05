package io.github.tomplum.aoc.airport.passport.strategy

import io.github.tomplum.aoc.airport.passport.PassportField

class RelaxedValidation : PassportValidationStrategy {
    override fun isValid(info: Map<PassportField, String>): Boolean {
        val mandatoryFields = PassportField.values().asList().minusElement(PassportField.COUNTRY_ID)
        return info.keys.containsAll(mandatoryFields)
    }
}