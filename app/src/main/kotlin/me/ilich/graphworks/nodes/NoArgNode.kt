package me.ilich.graphworks.nodes

/**
 * Created by disokolov on 13.07.16.
 */
abstract class NoArgNode : Node(0) {

    override fun onCalc(vararg arg: Double, paramSource: ParamSource?): Double = onCalc(paramSource)

    protected abstract fun onCalc(paramSource: ParamSource?): Double

    override fun onAsString(vararg arg: String): String = onAsString()

    protected abstract fun onAsString(): String

}