package y2025.day02

import java.util.Optional

class Day02 {
    companion object {

        var groups: List<Group>

        init {
            val input = this::class.java.getResourceAsStream("./input.txt")?.bufferedReader()?.readLines()!!
            groups = grouped(input)
        }

        fun part1() = groups.map { it.invalidsHalf() }.flatten().sum()
        fun part2() = groups.map { it.invalidsPattern() }.flatten().sum()

        fun grouped(input: List<String>) = input.joinToString(",").split(',').filter { it.isNotBlank() }
            .map { it.split('-').let { Group(it.first().toLong(), it.last().toLong()) } }

        fun solution(input: List<String>): List<Long> {
            val groups = input.joinToString(",").split(',').filter { it.isNotBlank() }
                .map { it.split('-').let { Group(it.first().toLong(), it.last().toLong()) } }
            return groups.map { it.invalidsHalf() }.flatten()
        }

        data class Group(val start: Long, val end: Long)

        fun Group.invalidsHalf() =
            (start..end).toList().map { num ->
                if (num.isPatternHalf()) Optional.of(num) else Optional.empty()
            }.filter { it.isPresent }.map { it.get() }

        fun Group.invalidsPattern() =
            (start..end).toList().map { num ->
                if (num.isPattern()) Optional.of(num) else Optional.empty()
            }.filter { it.isPresent }.map { it.get() }

        fun Long.isPatternHalf(): Boolean {
            val txt = this.toString()
            val length = txt.length
            if (length % 2 != 0) {
                return false
            }
            val half = length / 2
            return txt.take(half) == txt.drop(half)
        }

        fun Long.isPattern(): Boolean {
            if (this < 10) return false
            val txt = this.toString()
            val length = txt.length
            val half = length / 2

            var take = 1
            do {
                val segment = txt.take(take)
                if ((txt.replace(segment, "")).isBlank()) return true
                take++
            } while (take <= half)
            return false
        }
    }
}