package me.ilich.graphworks

import me.ilich.graphworks.operations.Const
import me.ilich.graphworks.operations.Operation
import me.ilich.graphworks.operations.ParamSource

class Graph(vararg pos: Pos) {

    class Pos(val node: Operation, val pos: Int? = null, val parent: Int? = null)

    private val nodesCount = pos.size
    private val nodes: List<Operation>;
    private val matrix = Matrix<Int>(nodesCount, 0)

    init {
        val l: MutableList<Operation> = mutableListOf()
        pos.forEach {
            l.add(it.node)
            if (it.parent != null) {
                val p = when (it.pos) {
                    null -> l.size
                    else -> it.pos
                }
                if (it.parent != p) {
                    matrix[it.parent, p] = 1
                    matrix[p, it.parent] = -1
                }
            }
        }
        nodes = l.toList()
    }

    fun calc(paramSource: ParamSource? = null): Double = calcFromIndex(0, paramSource)

    private fun calcFromIndex(fromIndex: Int, paramSource: ParamSource? = null): Double {
        val node = nodes[fromIndex];
        val args = mutableListOf<Double>()
        for (toIndex in 0..nodesCount - 1) {
            val link = matrix[fromIndex, toIndex]
            if (link == 1) {
                val v = calcFromIndex(toIndex, paramSource = paramSource)
                args.add(v)
            }
        }
        return node.calc(*args.toDoubleArray(), paramSource = paramSource);
    }

    fun asString(): String {
        if (nodesCount == 0) {
            return "()"
        } else {
            return stringFromIndex(0)
        }
    }

    private fun stringFromIndex(fromIndex: Int): String {
        val node = nodes[fromIndex];
        val args = mutableListOf<String>()
        for (toIndex in 0..nodesCount - 1) {
            val link = matrix[fromIndex, toIndex]
            if (link == 1) {
                val v = stringFromIndex(toIndex)
                args.add(v)
            }
        }
        return node.asString(*args.toTypedArray());
    }

    fun subGraph(fromIndex: Int): Graph {
        val subNodes = mutableListOf<Pos>()
        val rootNode = nodes[fromIndex]
        subNodes.add(Pos(rootNode, 0))
        collectSubNodes(this, subNodes, fromIndex)
        return Graph(*subNodes.toTypedArray())
    }

    fun outgoingLinksRecursive(fromIndex: Int): Int {
        var size = 0
        for (toIndex in 0..nodesCount - 1) {
            val link = matrix[fromIndex, toIndex]
            if (link == 1) {
                size++
                size += outgoingLinksRecursive(toIndex)
            }
        }
        return size
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Graph

        if (nodesCount != other.nodesCount) return false
        if (nodes != other.nodes) return false
        if (matrix != other.matrix) return false

        return true
    }

    override fun hashCode(): Int {
        var result = nodesCount
        result = 31 * result + nodes.hashCode()
        result = 31 * result + matrix.hashCode()
        return result
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("expr = ${asString()}\n")
        sb.append("matrix:\n")
        sb.append(matrix.toString())
        return sb.toString()
    }

    fun replaceNode(replaceNodeIndex: Int, graph: Graph): Graph {
        val newGraphNodes = mutableListOf<Pos>()
        if (replaceNodeIndex == 0) {
            collectSubNodes(graph, newGraphNodes, null)
        } else {
            collectSubNodesExcept(this, newGraphNodes, null, replaceNodeIndex, graph)
        }
        return Graph(*newGraphNodes.toTypedArray())
    }

    private fun collectSubNodes(graph: Graph, list: MutableList<Graph.Pos>, parentIndex: Int?) {
        val subIndexes = mutableListOf<Int>()
        if (parentIndex == null) {
            val node = graph.nodes[0]
            list.add(Graph.Pos(node, 0))
            subIndexes.add(0)
        } else {
            for (toIndex in 0..graph.nodesCount - 1) {
                if (toIndex != parentIndex) {
                    val link = graph.matrix[parentIndex, toIndex]
                    if (link == 1) {
                        val node = graph.nodes[toIndex]
                        val pos = list.size
                        list.add(Graph.Pos(node, pos, parentIndex))
                        subIndexes.add(toIndex)
                    }
                }
            }
        }
        for (subIndex in subIndexes) {
            collectSubNodes(graph, list, subIndex)
        }
    }

    private fun collectSubNodesExcept(graph: Graph, list: MutableList<Graph.Pos>, parentIndex: Int?, exceptIndex: Int, exceptGraph: Graph) {
        val subIndexes = mutableListOf<Int>()
        if (parentIndex == null) {
            val node = graph.nodes[0]
            list.add(Graph.Pos(node, 0))
            subIndexes.add(0)
        } else {
            for (toIndex in 0..graph.nodesCount - 1) {
                if (toIndex != parentIndex) {
                    val link = graph.matrix[parentIndex, toIndex]
                    if (link == 1) {
                        val node = graph.nodes[toIndex]
                        val pos = list.size
                        list.add(Graph.Pos(node, pos, parentIndex))
                        subIndexes.add(toIndex)
                    }
                }
            }
        }
        for (subIndex in subIndexes) {
            if (subIndex == exceptIndex) {
                //collectSubNodes(exceptGraph, list, null)
                list.add(Graph.Pos(Const(999.0), parent = parentIndex))
            } else {
                collectSubNodesExcept(graph, list, subIndex, exceptIndex, exceptGraph)
            }
        }
    }

}