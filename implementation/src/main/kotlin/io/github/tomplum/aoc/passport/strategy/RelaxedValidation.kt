package io.github.tomplum.aoc.passport.strategy

import io.github.tomplum.aoc.passport.PassportField

class RelaxedValidation : PassportValidationStrategy {
    override fun isValid(info: Map<PassportField, String>): Boolean {
        val mandatoryFields = PassportField.values().asList().minusElement(PassportField.COUNTRY_ID)
        return info.keys.containsAll(mandatoryFields)
    }
}