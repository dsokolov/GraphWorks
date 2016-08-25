package me.ilich.graphworks.operations

class Abs : OneArg() {

    override fun onCalc(a: Double, paramSource: ParamSource?): Double = Math.abs(a)

    override fun onAsString(a: String): String = "| $a |"

    override fun toString(): String{
        return "Operation abs()"
    }

}