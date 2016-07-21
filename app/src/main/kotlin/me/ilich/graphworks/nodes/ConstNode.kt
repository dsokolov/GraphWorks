package me.ilich.graphworks.nodes

/**
 * Created by disokolov on 13.07.16.
 */
class ConstNode(var value: Double) : NoArgNode() {

    override fun onCalc(paramSource: ParamSource?): Double = value

    override fun onAsString(): String = value.toString()

    override fun equals(other: Any?): Boolean{
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as ConstNode

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int{
        return value.hashCode()
    }

    override fun toString(): String{
        return "Node $value"
    }

}