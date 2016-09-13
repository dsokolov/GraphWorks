package me.ilich.graphworks.operations

import me.ilich.graphworks.Node

class Param(val name: String) : NoArg() {

    override fun onCalc(paramSource: ((String) -> (Double))?): Double {
        if (paramSource == null) {
            throw NullPointerException("param source")
        } else {
            return paramSource.invoke(name)
        }
    }

    override fun onAsString() = "$name"

    override fun onToConst(): Const? = null

    override fun toString() = "Operation param $name"

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

    override fun copy() = Param(name)

}

fun param(name: String, init: Node<Operation>.() -> Unit = {}): Node<Operation> = node(Param(name) as Operation, init)

fun Node<Operation>.param(name: String, init: Node<Operation>.() -> Unit = {}) = this.node(Param(name) as Operation, init)