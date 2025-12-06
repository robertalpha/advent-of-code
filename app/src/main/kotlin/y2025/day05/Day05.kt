package y2025.day05

import kotlin.math.max
import kotlin.math.min

class Day05 {
    companion object {
        data class Inventory(val ranges: List<InventoryRange>, val items: Set<Long>)

        data class InventoryRange(val beginning: Long, val end: Long)

        var input: List<String> = this::class.java.getResourceAsStream("./input.txt")?.bufferedReader()?.readLines()!!

        fun Inventory.countFreshItems() =
            items.count { item ->
                ranges.any { it.beginning <= item && it.end >= item }
            }

        fun InventoryRange.overlapsWith(other: InventoryRange) =
            (this.beginning <= other.end && this.end >= other.beginning)

        fun Inventory.mergeRanges(): List<InventoryRange> {
            val initial = mutableMapOf(Pair(ranges.first().beginning, ranges.first()))
            val output = this.ranges.sortedBy { it.beginning }.fold(initial) { acc, next ->
                val overlaps = acc.filter { it.value.overlapsWith(next) }

                if (overlaps.isNotEmpty()) {
                    val mergedRange = overlaps.values.fold(next) { acc, newnext ->
                        InventoryRange(min(acc.beginning, newnext.beginning), end = max(acc.end, newnext.end))
                    }
                    acc[overlaps.values.minBy { it.beginning }.beginning] = mergedRange
                } else {
                    acc[next.beginning] = next
                }

                acc
            }.values
            return output.toList()
        }

        fun Inventory.countFreshItemNumbers(): Long {
            return this.mergeRanges().sumOf { it.end - it.beginning + 1 }
        }

        fun List<String>.readInventory(): Inventory {
            val ranges = this.filter { it.contains("-") }.map {
                it.split("-").let { rangeLine ->
                    InventoryRange(beginning = rangeLine[0].toLong(), end = rangeLine[1].toLong())
                }
            }
            val items = this.filter { it.isNotBlank() && !it.contains("-") }.map { it.toLong() }.toSet()
            return Inventory(ranges = ranges, items = items)
        }

        fun part1() = input.readInventory().countFreshItems()
        fun part2() = input.readInventory().countFreshItemNumbers()
    }
}