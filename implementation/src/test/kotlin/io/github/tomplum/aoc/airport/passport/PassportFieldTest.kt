package io.github.tomplum.aoc.airport.passport

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.ValueSource

class PassportFieldTest {
    @Nested
    inner class FieldValidity {
        @Nested
        inner class BirthYear {
            @ParameterizedTest
            @ValueSource(strings = ["2002", "1920", "1970"])
            fun valid(value: String) {
                assertThat(PassportField.BIRTH_YEAR.isValid(value)).isTrue()
            }

            @ParameterizedTest
            @EmptySource
            @ValueSource(strings = ["2003", "1919", "204"])
            fun invalid(value: String) {
                assertThat(PassportField.BIRTH_YEAR.isValid(value)).isFalse()
            }
        }

        @Nested
        inner class IssueYear {
            @ParameterizedTest
            @ValueSource(strings = ["2010", "2020", "2015"])
            fun valid(value: String) {
                assertThat(PassportField.ISSUE_YEAR.isValid(value)).isTrue()
            }

            @ParameterizedTest
            @EmptySource
            @ValueSource(strings = ["2009", "2021", "210"])
            fun invalid(value: String) {
                assertThat(PassportField.ISSUE_YEAR.isValid(value)).isFalse()
            }
        }

        @Nested
        inner class ExpirationYear {
            @ParameterizedTest
            @ValueSource(strings = ["2020", "2030", "2025"])
            fun valid(value: String) {
                assertThat(PassportField.EXPIRATION_YEAR.isValid(value)).isTrue()
            }

            @ParameterizedTest
            @EmptySource
            @ValueSource(strings = ["2019", "2031", "202"])
            fun invalid(value: String) {
                assertThat(PassportField.EXPIRATION_YEAR.isValid(value)).isFalse()
            }
        }

        @Nested
        inner class Height {
            @ParameterizedTest
            @ValueSource(strings = ["150cm", "167cm", "193cm", "59in", "65in", "76in"])
            fun valid(value: String) {
                assertThat(PassportField.HEIGHT.isValid(value)).isTrue()
            }

            @ParameterizedTest
            @EmptySource
            @ValueSource(strings = ["149cm", "194cm", "176", "58in", "77in", "65"])
            fun invalid(value: String) {
                assertThat(PassportField.HEIGHT.isValid(value)).isFalse()
            }
        }

        @Nested
        inner class HairColour {
            @ParameterizedTest
            @ValueSource(strings = ["#000000", "#ffffff", "#f1f1f1"])
            fun valid(value: String) {
                assertThat(PassportField.HAIR_COLOUR.isValid(value)).isTrue()
            }

            @ParameterizedTest
            @EmptySource
            @ValueSource(strings = ["#", "ffffff", "#1f1f1", "#12345g", "#1-3a5b"])
            fun invalid(value: String) {
                assertThat(PassportField.HAIR_COLOUR.isValid(value)).isFalse()
            }
        }

        @Nested
        inner class EyeColour {
            @ParameterizedTest
            @ValueSource(strings = ["amb", "blu", "brn", "gry", "grn", "hzl", "oth"])
            fun valid(value: String) {
                assertThat(PassportField.EYE_COLOUR.isValid(value)).isTrue()
            }

            @ParameterizedTest
            @EmptySource
            @ValueSource(strings = ["ghj", "ambd", "wat"])
            fun invalid(value: String) {
                assertThat(PassportField.EYE_COLOUR.isValid(value)).isFalse()
            }
        }

        @Nested
        inner class PassportID {
            @ParameterizedTest
            @ValueSource(strings = ["000000001", "123456789", "010101010"])
            fun valid(value: String) {
                assertThat(PassportField.PASSPORT_ID.isValid(value)).isTrue()
            }

            @ParameterizedTest
            @EmptySource
            @ValueSource(strings = ["12345678", "12345678f", "1234567890"])
            fun invalid(value: String) {
                assertThat(PassportField.PASSPORT_ID.isValid(value)).isFalse()
            }
        }

        @Nested
        inner class CountryID {
            @ParameterizedTest
            @EmptySource
            @ValueSource(strings = ["100","56234"])
            fun valid(value: String) {
                assertThat(PassportField.COUNTRY_ID.isValid(value)).isTrue()
            }
        }
    }

    @Nested
    inner class FromString {
        @Test
        fun birthYear() {
            assertThat(PassportField.fromString("byr")).isEqualTo(PassportField.BIRTH_YEAR)
        }

        @Test
        fun issueYear() {
            assertThat(PassportField.fromString("iyr")).isEqualTo(PassportField.ISSUE_YEAR)
        }

        @Test
        fun expirationYear() {
            assertThat(PassportField.fromString("eyr")).isEqualTo(PassportField.EXPIRATION_YEAR)
        }

        @Test
        fun height() {
            assertThat(PassportField.fromString("hgt")).isEqualTo(PassportField.HEIGHT)
        }

        @Test
        fun hairColour() {
            assertThat(PassportField.fromString("hcl")).isEqualTo(PassportField.HAIR_COLOUR)
        }

        @Test
        fun eyeColour() {
            assertThat(PassportField.fromString("ecl")).isEqualTo(PassportField.EYE_COLOUR)
        }

        @Test
        fun passportId() {
            assertThat(PassportField.fromString("pid")).isEqualTo(PassportField.PASSPORT_ID)
        }

        @Test
        fun countryId() {
            assertThat(PassportField.fromString("cid")).isEqualTo(PassportField.COUNTRY_ID)
        }

        @ParameterizedTest
        @EmptySource
        @ValueSource(strings = ["isd", "dfg", "p1d"])
        fun invalidString(value: String) {
            val e = assertThrows<IllegalArgumentException> { PassportField.fromString(value) }
            assertThat(e.message).isEqualTo("Invalid Field Code: $value")
        }
    }
}
