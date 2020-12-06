package io.github.tomplum.aoc.benchmark.utility

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import io.github.tomplum.aoc.benchmark.data.BenchmarkResult
import java.io.File


class BenchmarkWriter {
    fun write(result: BenchmarkResult) {
        val mapper = XmlMapper()
        mapper.enable(SerializationFeature.INDENT_OUTPUT)
        mapper.writeValue(File("benchmark.xml"), result)
    }
}