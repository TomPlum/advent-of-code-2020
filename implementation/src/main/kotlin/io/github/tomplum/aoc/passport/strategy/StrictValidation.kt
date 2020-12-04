package io.github.tomplum.aoc.passport.strategy

import io.github.tomplum.aoc.passport.PassportField

class StrictValidation : PassportValidationStrategy {
    override fun isValid(info: Map<PassportField, String>): Boolean {
        val mandatoryFields = PassportField.values().asList().minusElement(PassportField.COUNTRY_ID)
        val hasMandatoryFields = info.keys.containsAll(mandatoryFields)
        val hasValidFieldValues = info.all { (field, value) -> field.isValid(value) }
        return hasMandatoryFields && hasValidFieldValues
    }
}