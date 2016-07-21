package me.ilich.graphworks.nodes

abstract class TwoArgNode : Node(2) {

    override fun onCalc(vararg arg: Double, paramSource: ParamSource?): Double = onCalc(arg[0], arg[1], paramSource)

    protected abstract fun onCalc(a: Double, b: Double, paramSource: ParamSource?): Double

    override fun onAsString(vararg arg: String): String = onAsString(arg[0], arg[1])

    protected abstract fun onAsString(a: String, b: String): String

}