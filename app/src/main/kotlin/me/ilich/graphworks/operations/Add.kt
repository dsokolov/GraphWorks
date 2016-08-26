package me.ilich.graphworks.operations

import me.ilich.graphworks.Node2

class Add : TwoArg() {

    override fun onCalc(a: Double, b: Double, paramSource: (String) -> Double): Double = a + b

    override fun onAsString(a: String, b: String) = "( $a + $b )"

    override fun toString(): String {
        return "Operation +"
    }

}

fun add(init: Node2<Operation>.() -> Unit = {}): Node2<Operation> = node2(Add() as Operation, init)

fun Node2<Operation>.add(init: Node2<Operation>.() -> Unit = {}) = this.node2(Add() as Operation, init)
