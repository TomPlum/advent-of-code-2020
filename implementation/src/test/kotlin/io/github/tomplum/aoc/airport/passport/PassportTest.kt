package io.github.tomplum.aoc.airport.passport

import assertk.assertThat
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import io.github.tomplum.aoc.airport.passport.PassportField.*
import io.github.tomplum.aoc.airport.passport.strategy.RelaxedValidation
import org.junit.jupiter.api.Test

class PassportTest {
    @Test
    fun exampleOne() {
        val passport = Passport(mapOf(Pair(EYE_COLOUR, "gry"), Pair(PASSPORT_ID, "860033327"),
            Pair(EXPIRATION_YEAR, "2020"), Pair(HAIR_COLOUR, "#fffffd"), Pair(BIRTH_YEAR, "1937"),
            Pair(ISSUE_YEAR, "2017"), Pair(COUNTRY_ID, "147"), Pair(HEIGHT, "183cm")))
        assertThat(passport.isValid(RelaxedValidation())).isTrue()
    }

    @Test
    fun exampleTwo() {
        val passport = Passport(mapOf(Pair(EYE_COLOUR, "amb"), Pair(PASSPORT_ID, "028048884"),
            Pair(EXPIRATION_YEAR, "2023"), Pair(HAIR_COLOUR, "#cfa07d"), Pair(BIRTH_YEAR, "1929"),
            Pair(ISSUE_YEAR, "2013"), Pair(COUNTRY_ID, "350")))
        assertThat(passport.isValid(RelaxedValidation())).isFalse()
    }

    @Test
    fun exampleThree() {
        val passport =  Passport(mapOf(Pair(EYE_COLOUR, "brn"), Pair(PASSPORT_ID, "760753108"),
            Pair(EXPIRATION_YEAR, "2024"), Pair(HEIGHT, "179cm"), Pair(HAIR_COLOUR, "#ae17e1"),
            Pair(BIRTH_YEAR, "1931"), Pair(ISSUE_YEAR, "2013")))
        assertThat(passport.isValid(RelaxedValidation())).isTrue()
    }

    @Test
    fun exampleFour() {
        val passport =  Passport(mapOf(Pair(EYE_COLOUR, "brn"), Pair(PASSPORT_ID, "166559648"),
            Pair(EXPIRATION_YEAR, "2025"), Pair(HEIGHT, "59in"), Pair(HAIR_COLOUR, "#cfa07d"), Pair(ISSUE_YEAR, "2011")))
        assertThat(passport.isValid(RelaxedValidation())).isFalse()
    }
}