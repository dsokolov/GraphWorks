package me.ilich.graphworks.operations

import me.ilich.graphworks.Node

class Add : TwoArg() {

    override fun onCalc(a: Double, b: Double, paramSource: (String) -> Double): Double = a + b

    override fun onAsString(a: String, b: String) = "( $a + $b )"

    override fun toString(): String {
        return "Operation +"
    }

}

fun add(init: Node<Operation>.() -> Unit = {}): Node<Operation> = node(Add() as Operation, init)

fun Node<Operation>.add(init: Node<Operation>.() -> Unit = {}) = this.node(Add() as Operation, init)
