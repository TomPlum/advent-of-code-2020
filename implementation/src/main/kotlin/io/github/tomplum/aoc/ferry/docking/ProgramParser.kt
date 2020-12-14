package io.github.tomplum.aoc.ferry.docking

class ProgramParser private constructor() {
    companion object {
        fun parse(data: List<String>): List<InitialisationProgram> {
            val programs = mutableListOf<InitialisationProgram>()
            val instructions = mutableListOf<Instruction>()
            var mask = Mask()
            data.forEachIndexed { i, it ->
                if (it.take(4) == "mask") {
                    it.takeLast(36).forEachIndexed { index, bit ->
                        if (bit.isDigit()) mask.put(index, bit.toString().toInt())
                    }
                    if (i != 1) {
                        val program = InitialisationProgram(mask, instructions)
                        programs.add(program)
                        mask = Mask()
                        instructions.clear()
                    }
                } else {
                    val info = it.split(" = ")
                    val address = info[0].removePrefix("mem[").removeSuffix("]").toInt()
                    val value = info[1].toInt()
                    instructions.add(Instruction(address, value))
                }
            }
            return programs
        }
    }
}