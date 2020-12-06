package io.github.tomplum.aoc.airport.passport.strategy

import io.github.tomplum.aoc.airport.passport.PassportField
import io.github.tomplum.aoc.airport.passport.PassportScanner

/**
 * Airport security discover that passports are getting through the [PassportScanner] with invalid data.
 * This strategy checks both the existence of the mandatory fields and their respective value validity.
 * @see RelaxedValidation
 */
class StrictValidation : PassportValidationStrategy {
    override fun isValid(info: Map<PassportField, String>): Boolean {
        val mandatoryFields = PassportField.values().asList().minusElement(PassportField.COUNTRY_ID)
        val hasMandatoryFields = info.keys.containsAll(mandatoryFields)
        val hasValidFieldValues = info.all { (field, value) -> field.isValid(value) }
        return hasMandatoryFields && hasValidFieldValues
    }
}