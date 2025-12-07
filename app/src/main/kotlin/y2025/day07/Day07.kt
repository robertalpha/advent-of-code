package y2025.day07


class Day07 {
    companion object {

        var input: List<String> = this::class.java.getResourceAsStream("./input.txt")?.bufferedReader()?.readLines()!!

        fun Char?.isOn() = listOf('S', '|').contains(this)
        fun Char.isDot() = this == '.'
        fun Char?.isBranch() = this == '^'

        fun List<String>.topDown(): List<String> {
            val out = this.drop(1).fold(listOf(this.first())) { acc, next ->
                val added = (0..<next.length).map {
                    if (acc.last()[it].isOn() && next[it].isDot()) '|'
                    else if (acc.last().getOrNull(it-1).isOn() && next[it-1].isBranch()) '|'
                    else if (acc.last().getOrNull(it+1).isOn() && next[it+1].isBranch()) '|'
                    else next[it]
                }.joinToString("")
                acc + added
            }
            return out
        }

        fun List<String>.countBranches() = this.fold(listOf<String>()) { acc, next ->
            acc + (0..<next.length).map { ind ->
                if(next[ind] == '^' && '|' == acc.last()[ind] ) '#' else next[ind]
            }.joinToString("")
        }.joinToString("").count { it == '#' }

        fun List<String>.bottomUp(): Long {
            val start = this.last().map { if(it=='|') "1" else it.toString() }
            val countedTree = this.reversed().drop(1).fold(mutableListOf(start)){ acc, next ->
                val newline = (0..<next.length).map {
                    if(next[it].isBranch()) summed(acc.last()[it-1] , acc.last()[it+1])
                    else if(next[it].isOn()) acc.last()[it]
                    else next[it].toString()
                }
                acc.add(newline)
                acc
            }

            val result = countedTree.last().first { it != "." }.toLong()

            return result
        }

        fun summed(left: String, right: String): String {
            return listOf(left,right).sumOf { it.toLong() }.toString()
        }

        fun part1() = input.topDown().countBranches()

        fun part2() = input.topDown().bottomUp()
    }
}