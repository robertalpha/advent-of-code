package y2025.day04

class Day04 {
    companion object {
        data class MiniMatrix(val upper: String?, val middle: String?, val lower: String?, val center: Char)

        var input: List<String> = this::class.java.getResourceAsStream("./input.txt")?.bufferedReader()?.readLines()!!

        fun part1() = input.toMatrix().countProcessable()
        fun part2() = input.toMatrix().countUntillNotProcessable()

        fun List<String>.toMatrix() = this.map { line -> line.map { it } }

        fun List<List<Char>>.getMiniMatrixAt(x: Int, y: Int): MiniMatrix {
            val upper = listOf( this.getOrNull(y-1)?.getOrNull(x-1) , this.getOrNull(y-1)?.getOrNull(x) , this.getOrNull(y-1)?.getOrNull(x+1) )
            val middle = listOf( this.getOrNull(y)?.getOrNull(x-1) , this.getOrNull(y)?.getOrNull(x) , this.getOrNull(y)?.getOrNull(x+1) )
            val lower = listOf( this.getOrNull(y+1)?.getOrNull(x-1) , this.getOrNull(y+1)?.getOrNull(x) , this.getOrNull(y+1)?.getOrNull(x+1) )
            return MiniMatrix(upper.filterNotNull().joinToString(),middle.filterNotNull().joinToString(),lower.filterNotNull().joinToString(), this.getOrNull(y)?.getOrNull(x)!!)
        }

        fun MiniMatrix.isProcessable(): Boolean {
            val rows = listOfNotNull(upper, middle, lower)
            val txt = rows.joinToString()
            return center == '@' && txt.count { it == '@' } <= 4
        }

        fun List<List<Char>>.countProcessable() = processRolls(this).flatten().count { it == 'x' }

        fun List<List<Char>>.countUntillNotProcessable(): Int {
            var output = 0
            var matrix = this
            do {
                val processed = processRolls(matrix)
                val removed = processed.flatten().count { it == 'x' }
                output += removed
                matrix = processed
            } while (removed > 0)
            return output
        }

        fun processRolls(matrix: List<List<Char>>) =
            (0..<matrix.size).map { y ->
                (0..<matrix[0].size).map { x ->
                    val matrixMini = matrix.getMiniMatrixAt(x, y)
                    matrixMini.center.let {
                        if (it == '@') {
                            if (matrixMini.isProcessable()) 'x' else '@'
                        } else
                            if (it == 'x') '.' else
                                matrixMini.center
                    }
                }
            }
    }
}