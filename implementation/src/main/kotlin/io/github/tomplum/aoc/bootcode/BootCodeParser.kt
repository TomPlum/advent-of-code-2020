package io.github.tomplum.aoc.bootcode

class BootCodeParser {
    companion object {
        fun parse(instructions: List<String>): BootCodeProgram = instructions.map {
            val data = it.split(" ")
            val code = BootCode.fromString(data[0])
            val argument = data[1].toInt()
            BootCodeInstruction(code, argument)
        }.let { BootCodeProgram(it) }
    }
}