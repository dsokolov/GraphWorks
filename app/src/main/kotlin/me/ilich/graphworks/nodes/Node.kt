package me.ilich.graphworks.nodes

/**
 * Created by disokolov on 13.07.16.
 */
abstract class Node {

    fun calc(vararg arg: Double): Double = onCalc(*arg)

    protected abstract fun onCalc(vararg arg: Double): Double

    fun asString(vararg arg: Double): String = onAsString(*arg)

    protected abstract fun onAsString(vararg arg: Double): String

}