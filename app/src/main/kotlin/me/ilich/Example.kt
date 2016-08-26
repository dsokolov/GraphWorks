package me.ilich

import me.ilich.graphworks.Node
import me.ilich.graphworks.genetic.Genetic
import me.ilich.graphworks.operations.Operation
import me.ilich.graphworks.operations.add
import me.ilich.graphworks.operations.mult
import me.ilich.graphworks.operations.param
import java.util.*

fun main(args: Array<String>) {

    val c = add() {
        mult {
            param("x")
            param("x")
        }
        param("x")
    }
    val paramSource: (String) -> Double = {
        when (it) {
            "x" -> 2.0
            else -> 0.0
        }
    }
    val d = calc(c, paramSource)
    println(d)

    val r = Random()

/*    val genetic = Genetic<Int>(
            cross = { x: Int, y: Int ->
                x + y
            },
            errorMetric = {
                Math.abs(it).toDouble()
            },
            mutate = {
                it + r.nextInt(3) - 1
            }
    )
    val l = genetic.generate(listOf(100, -50, 37, -23))
    println(l)*/

    val s = "privet"

    val genetic = Genetic<String>(
            cross = { x: String, y: String ->
                val indexX = r.nextInt(x.length)
                val indexY = r.nextInt(y.length)
                x.substring(0, indexX) + y.substring(indexY, y.length)
            },
            errorMetric = {
                var error = 0.0
                for (i in 0..s.length - 1) {
                    if (i < it.length) {
                        val c1 = s[i]
                        if (it.contains(c1)) {
                            error -= 5;
                        } else {
                            error += 150;
                        }
                    }
                }
                for (i in 0..Math.max(it.length, s.length) - 1) {
                    if (i >= it.length || i >= s.length) {
                        error += 255
                    } else {
                        val e = it[i] - s[i]
                        error += e * e
                        if(it[i] == s[i]){
                            error -= 10
                        }
                    }
                }
                error
            },
            mutate = {
                val chars = listOf("q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "a", "s", "d", "f", "g", "h", "j", "k", "l", "z", "x", "c", "v", "b", "n", "m", " ")
                val ch = chars[r.nextInt(chars.size)]
                val pos = r.nextInt(it.length)
                when (r.nextInt(12)) {
                    0, 1 -> it + ch;
                    2, 3 -> ch + it;
                    4, 5 -> it.drop(1)
                    6, 7 -> it.dropLast(1)
                    8, 9, 10, 11 -> it.replaceRange(pos, pos, ch)
                    else -> it
                }
            }
    )
    val l = genetic.generate(listOf("p", "r", "i", "v", "e", "t"))
    println(l)

}

fun calc(node: Node<out Operation>, paramSource: (String) -> Double): Double {
    val args = mutableListOf<Double>()
    for (sub in node.children) {
        args.add(calc(sub, paramSource))
    }
    return node.value.calc(*args.toDoubleArray(), paramSource = paramSource)
}

fun test(a: Int) {

}