package me.ilich.graphworks.nodes

class ParamNode(val name: String) : NoArgNode() {

    override fun onCalc(paramSource: ParamSource?): Double {
        if (paramSource == null) {
            return 0.0
        } else {
            return paramSource.onParams(name)
        }
    }

    override fun onAsString(): String = "$name"

    override fun toString(): String {
        return "Node param $name"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false
        if (!super.equals(other)) return false

        other as ParamNode

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }

}