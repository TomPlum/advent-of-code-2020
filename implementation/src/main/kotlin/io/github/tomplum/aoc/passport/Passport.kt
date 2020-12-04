package io.github.tomplum.aoc.passport

import io.github.tomplum.aoc.passport.PassportField.*

data class Passport(val info: Map<PassportField, String>)  {
    fun isValid(): Boolean = info.keys.containsAll(values().asList().minusElement(COUNTRY_ID))
}