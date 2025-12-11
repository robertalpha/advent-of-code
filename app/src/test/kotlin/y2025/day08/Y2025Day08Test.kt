package y2025.day08

import io.kotest.matchers.shouldBe
import jdk.internal.org.jline.keymap.KeyMap.key
import kotlin.collections.toMutableMap
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.test.Test

class Y2025Day08Test {

    val exampleInput =
"""
162,817,812
57,618,57
906,360,560
592,479,940
352,342,300
466,668,158
542,29,236
431,825,988
739,650,466
52,470,668
216,146,977
819,987,18
117,168,530
805,96,715
346,949,466
970,615,88
941,993,340
862,61,35
984,92,344
425,690,689
"""

    data class Point3D(val x: Int, val y: Int, val z: Int)
    data class PointPair(val p1 : Point3D, val p2: Point3D, val distance: Double)

    @Test
    fun day8Test() {



        val input = exampleInput.lines().filter { it.isNotBlank() }
            .map { line -> line.split(',').let {  Point3D(
                it[0].toInt(),
                it[1].toInt(),
                it[2].toInt()
                ) }}

        // example p1
        input.connect(10) shouldBe 21

        // example p2
        input.size shouldBe 40
    }
}

private fun List<Y2025Day08Test.Point3D>.connect(i: Int): MutableMap<Y2025Day08Test.Point3D, Int> {
    val output: MutableMap<Y2025Day08Test.Point3D, Int> = this.mapIndexed { ind, point -> Pair(point,ind) }
        .associate { it.first to it.second }.toMutableMap()

    val distances = this.calculateDistances()
    val shortestDistances = distances.map{ Pair(it.key, it.value.filter { s-> it.key != s.key }.minBy { it.value }) }.associate { it.first to it.second }

    var counter = i
    do {

//        output.minBy { p1 -> distances[p1].filter { p2 -> output[p2.key] != output[p1] }  }.
        val unconnected = distances.map { p1 -> p1.key to p1.value.filter { p2 -> output[p1.key] != output[p2.key] } }
        val shortest = unconnected.associate { it to it.second.minBy { it.value } }
        val next =  shortest.minBy { it.value.value }.let { Pair(it.key.first, it.value.key) }
        output[next.second] = output[next.first]!!
        counter --
    } while (counter >= 0)

    println(distances.size)

    return output
}

fun Y2025Day08Test.Point3D.distance(other: Y2025Day08Test.Point3D) = sqrt(
    (this.x - other.x).toDouble().pow(2.0) +
            (this.y - other.y).toDouble().pow(2.0) +
            (this.z - other.z).toDouble().pow(2.0)
)

private fun List<Y2025Day08Test.Point3D>.calculateDistances() : Map<Y2025Day08Test.Point3D, Map<Y2025Day08Test.Point3D, Double>>{
    val calculated = mutableMapOf<String, Double>()
    val out = this.map {
        p1 ->
        Pair(p1,this.map {
            p2 ->
            val distance = calculated.getOrPut("$p2-$p1") {
                val dist = p1.distance(p2)
//                println("putting: $p2-$p1 = $dist")
                dist
            }
            calculated["$p1-$p2"] = distance
            Pair(p2, distance)
        })
    }
    val m1 = out.associate {p1 ->  p1.first to p1.second.associate { it.first to it.second } }
    return m1
}
