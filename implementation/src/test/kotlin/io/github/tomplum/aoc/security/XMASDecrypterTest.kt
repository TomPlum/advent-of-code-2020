package io.github.tomplum.aoc.security

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class XMASDecrypterTest {
     @Nested
     inner class Decrypt {
         @Test
         fun example() {
             val data = TestInputReader().read<Long>("security/example.txt").value
             val decrypter = XMASDecrypter(data)
             assertThat(decrypter.decrypt(5)).isEqualTo(127)
         }
     }

    @Nested
    inner class FindWeakness {
        @Test
        fun example() {
            val data = TestInputReader().read<Long>("security/example.txt").value
            val decrypter = XMASDecrypter(data)
            assertThat(decrypter.discoverWeakness(127)).isEqualTo(62)
        }
    }
 }