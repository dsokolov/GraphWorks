package me.ilich.graphworks.operations

/**
 * Created by disokolov on 13.07.16.
 */
class Const(var value: Double) : NoArg() {

    override fun onCalc(paramSource: ParamSource?): Double = value

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