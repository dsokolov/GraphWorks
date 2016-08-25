package me.ilich

import me.ilich.graphworks.Node2
import me.ilich.graphworks.operations.Add
import me.ilich.graphworks.operations.Const
import me.ilich.graphworks.operations.Operation

fun <T> node2(v: T, init: Node2<T>.() -> Unit = {}): Node2<T> {
    val result = Node2(v)
    result.init()
    return result
}

fun <T> Node2<T>.node2(v: T, init: Node2<T>.() -> Unit = {}) {
    val result = Node2<T>(v)
    result.init()
    this.children.add(result)
}


fun main(args: Array<String>) {

/*    val n = node2("a") {
        node2("b") {
            node2("d")
        }
        node2("c") {
            node2("e") {
                node2("g")
            }
            node2("f")
        }
    }
    println(n.size)

    show(n)*/

    val c = node2(Add() as Operation) {
        node2(Const(10.0) as Operation)
        node2(Const(25.0) as Operation)
    }
    val d = calc(c)
    println(d)

}

fun show(node: Node2<out String>) {
    println(node.value)
    for (sub in node.children) {
        show(sub)
    }
}

fun calc(node: Node2<out Operation>): Double {
    val args = mutableListOf<Double>()
    for (sub in node.children) {
        args.add(calc(sub))
    }
    return node.value.calc(*args.toDoubleArray())
}