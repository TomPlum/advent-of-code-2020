package io.github.tomplum.aoc.password

import assertk.assertThat
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class PasswordDatabaseTest {
    private val exampleData = TestInputReader().readInputAsString("password/example-database-list.txt")

    @Nested
    inner class Import {
        private val database = PasswordDatabase()

        @Test
        fun exampleData() {
            database.import(exampleData.value)
            assertThat(database.passwords).isEqualTo(listOf(
                Pair("abcde", CorporatePolicy("1-3 a")),
                Pair("cdefg", CorporatePolicy("1-3 b")),
                Pair("ccccccccc", CorporatePolicy("2-9 c"))
            ))
        }

        @Test
        fun emptyData() {
            database.import(emptyList())
            assertThat(database.passwords).isEmpty()
        }
    }

    @Nested
    inner class Validate {
        @Test
        fun exampleData() {
            val database = PasswordDatabase()
            database.import(exampleData.value)
            assertThat(database.validate()).isEqualTo(2)
        }
    }
}
