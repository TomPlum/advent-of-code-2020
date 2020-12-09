package io.github.tomplum.aoc.security

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Test

class XMASDecrypterTest {
     @Test
     fun example() {
         val data = TestInputReader().readInputAsInt("security/example.txt").value
         val decrypter = XMASDecrypter(data)
         assertThat(decrypter.decrypt(5)).isEqualTo(127)
     }
 }