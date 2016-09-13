package me.ilich.graphworks.operations

import me.ilich.graphworks.Node

class Const(var value: Double) : NoArg() {

    override fun onCalc(paramSource: ((String) -> (Double))?): Double = value

    override fun onAsString(): String = value.toString()

    override fun onToConst(): Const = Const(value)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Const

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    override fun toString() = "Operation $value"

    override fun copy(): Const = Const(value)

}

fun const(c: Double, init: Node<Operation>.() -> Unit = {}): Node<Operation> = node(Const(c) as Operation, init)

fun Node<Operation>.const(c: Double, init: Node<Operation>.() -> Unit = {}) = this.node(Const(c) as Operation, init)