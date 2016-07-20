package me.ilich.graphworks.nodes

/**
 * Created by disokolov on 13.07.16.
 */
abstract class TwoArgNode : Node(2) {

    override fun onCalc(vararg arg: Double): Double = onCalc(arg[0], arg[1])

    protected abstract fun onCalc(a: Double, b: Double): Double

    override fun onAsString(vararg arg: String): String = onAsString(arg[0], arg[1])

    protected abstract fun onAsString(a: String, b: String): String

}