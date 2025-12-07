package y2025.day06


class Day06 {
    companion object {

        var input: List<String> = this::class.java.getResourceAsStream("./input.txt")?.bufferedReader()?.readLines()!!

        fun String.isOperator() = this.trim().let { it == "*" ||  it=="+" }

        fun List<String>.readHomeWork(): List<HomeworkColumn> {
            val operatorLine = this.first{ it.startsWith("*") || it.startsWith("+") }
            val columnStarts = operatorLine.mapIndexed { index,elem -> index.takeIf { elem=='*' || elem=='+' } }.filterNotNull() +operatorLine.length
            val remapped = (0..<columnStarts.size-1).map {
                Pair(columnStarts[it],columnStarts[it+1])
            }.mapIndexed { line, elem -> this.map { Pair(line,it.drop(elem.first).take(elem.second - elem.first)) } }
                .mapIndexed { c, elem -> HomeworkColumn(c, elem.map { it.second }.filter { !it.isOperator() },
                    elem.map { it.second }.first { it.isOperator() }.first()) }

            return remapped
        }

        data class HomeworkColumn(val columnNumber: Int, val numbers: List<String>, val operator: Char)

        fun List<HomeworkColumn>.sumOfLines() = this.sumOf {
            it.numbers.map{it.trim()}.filter { it.isNotBlank() }.map{it.toLong()}.reduce { a, b -> if (it.operator == '+') a + b else a * b }
        }

        data class SumList(var sum: Long, val group: MutableList<String>)


        fun List<String>.rightToLeft(): Long {
            val columns = (0..<this[0].length).reversed().map { x ->
                (0..<this.size).map { y ->
                    this[y][x]
                }
            }

            // moves column by column, right to left
            // if an operator is found the current number and group is folded with that operator
            // otherwise the number is added to the current group
            val folded = columns.reversed().foldRight(SumList(0L, mutableListOf())) { chars, acc ->
                if(chars.last().isDigit() || chars.last().isWhitespace()) {
                    val num = chars.joinToString("")
                    if(num.isNotEmpty()) {
                        acc.group.add(num)
                    }
                } else {
                    val operator = chars.last()
                    val trimmed = (acc.group + chars.joinToString("").takeWhile { it.isDigit() || it.isWhitespace() })
                        .map { it.trim() }
                    val sublist = trimmed.filter { it.isNotBlank() }
                        .map { it.toLong() }
                    acc.sum += sublist.reduce { a, b -> if (operator == '+') a + b else a * b }
                    acc.group.clear()
                }
                acc
            }
            return folded.sum
        }

        fun part1() = input.filter { it.isNotBlank() }.readHomeWork().sumOfLines()

        fun part2() = input.filter { it.trim().isNotBlank() }.rightToLeft()
    }
}