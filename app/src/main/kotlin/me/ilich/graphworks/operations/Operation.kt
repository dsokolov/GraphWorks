package me.ilich.graphworks.operations

import me.ilich.graphworks.Copyable
import me.ilich.graphworks.Node

abstract class Operation(val minArgCount: Int?, val maxArgCount: Int?) : Copyable {

    init {
        if (minArgCount != null && maxArgCount != null) {
            if (maxArgCount < minArgCount) {
                throw IllegalArgumentException("maxArgCount (${maxArgCount}) shuld be greather that minArgCount (${minArgCount})")
            }
        }
    }

    fun calc(vararg arg: Double, paramSource: (String) -> Double = { 0.0 }): Double {
        checkArgCount(arg.size)
        return onCalc(*arg, paramSource = paramSource)
    }

    protected abstract fun onCalc(vararg arg: Double, paramSource: (String) -> Double): Double

    fun asString(vararg arg: String): String {
        checkArgCount(arg.size)
        return onAsString(*arg)
    }

    protected abstract fun onAsString(vararg arg: String): String

    private fun checkArgCount(size: Int) {
        if (minArgCount != null) {
            if (size < minArgCount) {
                throw IndexOutOfBoundsException("${this} should have at last $minArgCount arguments, but found ${size}")
            }
        }
        if (maxArgCount != null) {
            if (size > maxArgCount) {
                throw IndexOutOfBoundsException("${this} should have not more that $maxArgCount arguments, but found ${size}")
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Operation

        if (minArgCount != other.minArgCount) return false
        if (maxArgCount != other.maxArgCount) return false

        return true
    }

    override fun hashCode(): Int {
        var result = minArgCount ?: 0
        result = 31 * result + (maxArgCount ?: 0)
        return result
    }

}

fun <T : Copyable> node(v: T, init: Node<T>.() -> Unit = {}): Node<T> {
    val result = Node(v)
    result.init()
    return result
}

fun <T : Copyable> Node<T>.node(v: T, init: Node<T>.() -> Unit = {}) {
    val result = Node<T>(v)
    result.init()
    this.children.add(result)
}