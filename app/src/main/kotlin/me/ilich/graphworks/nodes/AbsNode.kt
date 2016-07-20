package me.ilich.graphworks.nodes

class AbsNode : OneArgNode() {

    override fun onCalc(a: Double): Double = Math.abs(a)

    override fun onAsString(a: String): String = "| $a |"

}