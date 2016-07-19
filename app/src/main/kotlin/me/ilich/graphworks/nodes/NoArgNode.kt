package me.ilich.graphworks.nodes

/**
 * Created by disokolov on 13.07.16.
 */
abstract class NoArgNode : Node() {

    override fun onCalc(vararg arg: Double): Double = onCalc()

    protected abstract fun onCalc(): Double

    override fun onAsString(vararg arg: Double): String = onAsString()

    protected abstract fun onAsString(): String

}