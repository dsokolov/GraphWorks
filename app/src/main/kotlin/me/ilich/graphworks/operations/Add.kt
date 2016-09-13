package me.ilich.graphworks.operations

import me.ilich.graphworks.Node

class Add : TwoAndMoreArg() {

    override fun onCalc(vararg arg: Double, paramSource: (String) -> Double): Double = arg.sum()

    override fun onAsString(vararg arg: String) = arg.joinToString(separator = " + ", prefix = "( ", postfix = " )")

    override fun toString() = "Operation +"

    override fun copy(): Add = Add()

}

fun add(init: Node<Operation>.() -> Unit = {}): Node<Operation> = node(Add() as Operation, init)

fun Node<Operation>.add(init: Node<Operation>.() -> Unit = {}) = this.node(Add() as Operation, init)
