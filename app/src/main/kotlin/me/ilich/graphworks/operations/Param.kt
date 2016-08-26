package me.ilich.graphworks.operations

import me.ilich.graphworks.Node

class Param(val name: String) : NoArg() {

    override fun onCalc(paramSource: (String) -> Double): Double = paramSource.invoke(name)

    override fun onAsString(): String = "$name"

    override fun toString(): String {
        return "Operation param $name"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false
        if (!super.equals(other)) return false

        other as Param

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }

}

fun param(name: String, init: Node<Operation>.() -> Unit = {}): Node<Operation> = node(Param(name) as Operation, init)

fun Node<Operation>.param(name: String, init: Node<Operation>.() -> Unit = {}) = this.node(Param(name) as Operation, init)