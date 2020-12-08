package io.github.tomplum.aoc.bootcode

class BootCodeParser {
    companion object {
        fun parse(data: List<String>): BootCodeProgram = data.map {
            val info = it.split(" ")
            val code = BootCode.fromString(info[0])
            val argument = info[1].toInt()
            BootCodeInstruction(code, argument)
        }.let { instructions -> BootCodeProgram(instructions) }
    }
}