package me.ilich.graphworks.nodes

class AddNode : TwoArgNode() {

    override fun onCalc(a: Double, b: Double, paramSource: ParamSource?): Double = a + b

    override fun onAsString(a: String, b: String) = "( $a + $b )"

    override fun toString(): String{
        return "Node +"
    }

}