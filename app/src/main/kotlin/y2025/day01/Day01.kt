package y2025.day01


import kotlin.math.abs

class Day01 {

    data class Move(val direction: Direction, val amount: Int)
    enum class Direction { L, R }

    data class Aggregate(val dialPosition: Int, val zeroCounts: Int, val zeroSkipped: Int)


    companion object {
        private var result : Aggregate

        init {
            val input = this::class.java.getResourceAsStream("./input.txt")?.bufferedReader()?.readLines()!!

            result = solution(input)
        }

        fun part1() = result.zeroCounts
        fun part2() = result.zeroCounts + result.zeroSkipped


        fun solution(input: List<String>): Aggregate {
            val moves = input.map { it.trim() }.filter { it.isNotEmpty() }.map {
                val dir = if (it[0] == 'L') Direction.L else Direction.R
                Move(dir, it.drop(1).toInt())
            }

            val move = moves.fold(Aggregate(50, 0,0)) { acc, next ->
                sumup(acc, next)
            }

            return move
        }

        fun sumup(acc: Aggregate, nextmove: Move): Aggregate {
            val zeroMods = nextmove.amount / 100
            if (nextmove.direction == Direction.L) {
                val neg = acc.dialPosition - nextmove.amount
                if (neg < 0) {
                    val newDial = (100 - (abs(neg) % 100))%100
                    return acc.add(newDial, zeroMods)
                }
                return acc.add(neg,0)
            }

            val newDial = (acc.dialPosition + nextmove.amount) % 100
            return acc.add(newDial, zeroMods)
        }

        fun Aggregate.add(dial: Int, zeroMods: Int) =
            Aggregate(dialPosition = dial, zeroCounts = this.zeroCounts + (if (dial == 0) 1 else 0), zeroSkipped =  zeroSkipped+ zeroMods )
    }


}