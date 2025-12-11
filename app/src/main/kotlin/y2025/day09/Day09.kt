package y2025.day09

import kotlin.collections.flatten
import kotlin.math.abs
import y2025.day09.Day09.Companion.area


class Day09 {
    companion object {

        var input: List<String> = this::class.java.getResourceAsStream("./input.txt")?.bufferedReader()?.readLines()!!

        data class PointTwoD(val x: Long, val y: Long)

        fun PointTwoD.area(other: PointTwoD): Long {
            val xdif = abs(other.x - this.x) + 1
            val ydif = abs(other.y - this.y) + 1
            val output = xdif * ydif
            return output
        }

        data class Area(val a: PointTwoD, val b: PointTwoD)

        fun Area.contains(point: PointTwoD): Boolean {
            val (minx, maxx) = listOf(this.a.x, this.b.x).let { Pair(it.min(), it.max()) }
            if (point.x < minx + 1 || point.x > maxx - 1) {
                return false
            } else {
                val (miny, maxy) = listOf(this.a.y, this.b.y).let { Pair(it.min(), it.max()) }
                if (point.y < miny + 1 || point.y > maxy - 1) {
                    return false
                }
            }
            return true
        }

        val points = input.map { line ->
            line.split(',').let {
                PointTwoD(
                    it[0].toLong(),
                    it[1].toLong()
                )
            }
        }

        fun List<PointTwoD>.getMaxArea(): Long {
            val areas = this.map { p1 -> this.map { p2 -> p2.area(p1) } }
            val maxAreas = areas.map { it.max() }
            return maxAreas.max()
        }

        fun part1() = points.getMaxArea()

        fun findPart2(): Long {
            // create some interpolated points between distant points that should not be contained in any area
            val virtual = points.makeVirtualPoints(2500L)
            val dontOverlap = points + virtual

            val areas = points.map { p1 -> points.map { p2 -> Area(p1, p2) } }.flatten()

            val filteredAreas = areas.filter { area -> dontOverlap.none { px -> area.contains(px) } }

            val maxNonOverlap = filteredAreas.maxBy { it.a.area(it.b) }

            return maxNonOverlap.a.area(maxNonOverlap.b)
        }

        fun part2() = findPart2()
    }
}

fun List<Day09.Companion.PointTwoD>.makeVirtualPoints(stepsize: Long) : List<Day09.Companion.PointTwoD> {
    return mapIndexed { index, p2 ->
        val p1 = if (index != 0) { this[index-1] } else { this[this.size-1] }
        val dist = p1.area(p2)
        if (dist > stepsize) {
            val xpoints = (p1.x..<p2.x step stepsize).map { Day09.Companion.PointTwoD(it, p2.y) }
            val ypoints = (p1.y..<p2.y step stepsize).map { Day09.Companion.PointTwoD(p2.x, it) }
            xpoints + ypoints
        } else emptyList()
    }.flatten()
}
