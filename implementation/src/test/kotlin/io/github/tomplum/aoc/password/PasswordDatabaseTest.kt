package io.github.tomplum.aoc.password

import assertk.assertThat
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class PasswordDatabaseTest {
    @Nested
    inner class Import {
        private val database = PasswordDatabase()

        @Test
        fun exampleData() {
            val data = TestInputReader().readInputAsString("password/example-database-list.txt")
            database.import(data.value)
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
}
