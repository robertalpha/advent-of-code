package y2025.day06

class Day06 {
    companion object {

        var input: List<String> = this::class.java.getResourceAsStream("./input.txt")?.bufferedReader()?.readLines()!!

        fun List<String>.readHomeWork(): List<HomeworkColumn> {
            val whitespace = Regex("\\s+")
            val words = this.map { it.trim() }.filter { it.isNotBlank() }.map { line ->
                line.replace(whitespace, "|")
                    .split("|").toList()
                    .mapIndexed { ind, word ->
                        Pair(ind, word)
                    }
            }.flatten().fold(mutableMapOf<Int, List<String>>()) { acc, pair ->
                acc.compute(pair.first) { _, v ->
                    if (v == null) listOf(pair.second)
                    else v + (pair.second)
                }
                acc
            }

            val columns = words.map { entry ->
                    val (ind, v) = entry
                    val numbers = v.take(v.size - 1).map { it.toLong() }
                    val operatorChar = v.last()
                    HomeworkColumn(ind, numbers, operatorChar)
            }

            return columns
        }

        data class HomeworkColumn(val columnNumber: Int, val numbers: List<Long>, val operator: String)

        fun List<HomeworkColumn>.sumOfLines() = this.sumOf {
            it.numbers.reduce { a, b -> if (it.operator == "+") a + b else a * b }
        }

        fun List<HomeworkColumn>.sumOfVerticalColumns() = this.sumOf {
            val numberWords = it.numbers.map { num -> num.toString() }
            val maxLength = numberWords.maxOf { num -> num.length }
            val remapped = (1.. maxLength).map{ c ->
                numberWords.map { it.padStart(maxLength, ' ').getOrNull(maxLength - c)?:' ' }
            }

            val numbers = remapped.map { it.fold("") { acc, c -> acc+c }.trim().toLong() }

            numbers.reduce { a, b -> if (it.operator == "+") a + b else a * b }
        }

        infix fun Long.plus(x: Long) = this + x
        infix fun Long.times(x: Long) = this * x

        fun part1() = input.readHomeWork().sumOfLines()

        fun part2() = input.readHomeWork().sumOfVerticalColumns()
    }
}