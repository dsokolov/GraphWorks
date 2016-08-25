package me.ilich.graphworks.operations

class Div : TwoArg() {

    override fun onCalc(a: Double, b: Double, paramSource: ParamSource?): Double = a / b

    override fun onAsString(a: String, b: String) = "( $a / $b )"

    override fun toString(): String{
        return "Operation /"
    }

}