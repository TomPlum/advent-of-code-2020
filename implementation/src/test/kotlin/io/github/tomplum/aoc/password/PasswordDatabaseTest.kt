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
        fun exampleDataSledPolicy() {
            database.import(exampleData.value, SledRentalPolicy::class.java)
            assertThat(database.passwords).isEqualTo(listOf(
                Pair("abcde", SledRentalPolicy("1-3 a")),
                Pair("cdefg", SledRentalPolicy("1-3 b")),
                Pair("ccccccccc", SledRentalPolicy("2-9 c"))
            ))
        }

        @Test
        fun emptyDataSledPolicy() {
            database.import(emptyList(), SledRentalPolicy::class.java)
            assertThat(database.passwords).isEmpty()
        }

        @Test
        fun exampleDataTobogganPolicy() {
            database.import(exampleData.value, TobogganPolicy::class.java)
            assertThat(database.passwords).isEqualTo(listOf(
                Pair("abcde", TobogganPolicy("1-3 a")),
                Pair("cdefg", TobogganPolicy("1-3 b")),
                Pair("ccccccccc", TobogganPolicy("2-9 c"))
            ))
        }

        @Test
        fun emptyDataTobogganPolicy() {
            database.import(emptyList(), TobogganPolicy::class.java)
            assertThat(database.passwords).isEmpty()
        }
    }

    @Nested
    inner class Validate {
        @Test
        fun exampleDataSledPolicy() {
            val database = PasswordDatabase()
            database.import(exampleData.value, SledRentalPolicy::class.java)
            assertThat(database.validate()).isEqualTo(2)
        }

        @Test
        fun exampleDataTobogganPolicy() {
            val database = PasswordDatabase()
            database.import(exampleData.value, TobogganPolicy::class.java)
            assertThat(database.validate()).isEqualTo(1)
        }
    }
}
