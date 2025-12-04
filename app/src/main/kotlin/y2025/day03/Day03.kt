package y2025.day03

class Day03 {
    companion object {
        var input: List<String> = this::class.java.getResourceAsStream("./input.txt")?.bufferedReader()?.readLines()!!

        fun part1() = input.sumOf { it.getJoltage(2) }
        fun part1Alt() = input.sumOf { it.getPivots().maxOf { it.max() } }
        fun part2() = input.sumOf { it.getJoltage(12) }

        fun String.getPivots(): List<Pivot> {
             return (0..<this.length).map{
                 val txt = this.drop(it)
                 Pivot(txt.take(1), txt.drop(1))
             }
        }

        data class JoltageStack(val cursor: String, val leftover: String, val length: Int)

        fun JoltageStack.max() : Long {
            if(this.leftover.isEmpty())
                return cursor.toLong()
            if(cursor.length + leftover.length == length)
                return (cursor + leftover).toLong()

            val nextDigit = leftover.take(1).toInt()

            var nextCursor = cursor
            do {
                val nextLast = nextCursor.drop(nextCursor.length - 1).toInt()
                if(nextDigit <= nextLast)
                    break

                nextCursor = nextCursor.take(nextCursor.length - 1)
            } while (leftover.length + nextCursor.length > length && nextCursor.isNotEmpty())

            if (nextCursor.length < length) nextCursor += nextDigit

            return JoltageStack(nextCursor, leftover.drop(1), length).max()
        }

        fun String.getJoltage(length: Int) =
             JoltageStack(this.take(1), this.drop(1), length).max()


        fun Pivot.max() : Int {
            if (rest.isBlank()) return first.toInt()
            val unique = rest.toList().toSet()
            return unique.maxOf { (first + it).toInt() }
        }

        data class Pivot(val first: String, val rest: String)
    }
}