package io.github.tomplum.aoc.airport.passport.strategy

import io.github.tomplum.aoc.airport.passport.Passport
import io.github.tomplum.aoc.airport.passport.PassportField

/**
 * This strategy ensures that a given [Passport] contains all of the mandatory fields.
 * It is not concerned with the validity of the values.
 * @see StrictValidation
 */
class RelaxedValidation : PassportValidationStrategy {
    override fun isValid(info: Map<PassportField, String>): Boolean {
        val mandatoryFields = PassportField.values().asList().minusElement(PassportField.COUNTRY_ID)
        return info.keys.containsAll(mandatoryFields)
    }
}