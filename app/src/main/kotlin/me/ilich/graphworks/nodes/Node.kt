package me.ilich.graphworks.nodes

abstract class Node(val argCount: Int) {

    fun calc(vararg arg: Double, paramSource: ParamSource? = null): Double {
        assert(arg.size == argCount)
        return onCalc(*arg, paramSource = paramSource)
    }

    protected abstract fun onCalc(vararg arg: Double, paramSource: ParamSource? = null): Double

    fun asString(vararg arg: String): String {
        assert(arg.size == argCount)
        return onAsString(*arg)
    }

    protected abstract fun onAsString(vararg arg: String): String

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Node

        if (argCount != other.argCount) return false

        return true
    }

    override fun hashCode(): Int {
        return argCount
    }

}