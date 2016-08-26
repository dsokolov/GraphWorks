package me.ilich.graphworks.operations

import me.ilich.graphworks.Node2

class Const(var value: Double) : NoArg() {

    override fun onCalc(paramSource: (String) -> Double): Double = value

    override fun onAsString(): String = value.toString()

    override fun equals(other: Any?): Boolean{
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Const

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int{
        return value.hashCode()
    }

    override fun toString(): String{
        return "Operation $value"
    }

}

fun const(c: Double, init: Node2<Operation>.() -> Unit = {}): Node2<Operation> = node2(Const(c) as Operation, init)

fun Node2<Operation>.const(c: Double, init: Node2<Operation>.() -> Unit = {}) = this.node2(Const(c) as Operation, init)