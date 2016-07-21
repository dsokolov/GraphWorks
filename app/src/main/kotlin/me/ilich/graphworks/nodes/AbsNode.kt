package me.ilich.graphworks.nodes

class AbsNode : OneArgNode() {

    override fun onCalc(a: Double, paramSource: ParamSource?): Double = Math.abs(a)

    override fun onAsString(a: String): String = "| $a |"

    override fun toString(): String{
        return "Node abs()"
    }

}