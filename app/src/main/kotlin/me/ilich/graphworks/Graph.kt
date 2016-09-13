package me.ilich.graphworks

import me.ilich.graphworks.operations.Operation
import java.util.concurrent.atomic.AtomicInteger

class Graph(val rootNode: Node<Operation>) {

    val nodesArray: Array<Node<Operation>>

    init {
        val nodes = mutableListOf<Node<Operation>>()
        addToList(nodes, rootNode)
        nodesArray = nodes.toTypedArray()
    }

    private fun addToList(list: MutableList<Node<Operation>>, node: Node<Operation>) {
        list.add(node)
        for (sub in node.children) {
            addToList(list, sub)
        }
    }

    val nodesCount: Int
        get() = nodesArray.size

    fun calc(paramSource: (String) -> Double = { 0.0 }): Double = calcFromNode(rootNode, paramSource)

    private fun calcFromNode(node: Node<Operation>, paramSource: (String) -> Double): Double {
        val args = mutableListOf<Double>()
        for (sub in node.children) {
            val arg = calcFromNode(sub, paramSource)
            args.add(arg)
        }
        return node.value.calc(*args.toDoubleArray(), paramSource = paramSource)
    }

    fun asString(): String = stringFromNode(rootNode)

    private fun stringFromNode(node: Node<Operation>): String {
        val args = mutableListOf<String>()
        for (sub in node.children) {
            val arg = stringFromNode(sub)
            args.add(arg)
        }
        return node.value.asString(*args.toTypedArray())
    }

    operator fun get(index: Int): Graph {
        if (index < 0 || index >= nodesCount) {
            throw ArrayIndexOutOfBoundsException()
        }
        var n: Node<Operation> = rootNode
        forEachNode { forIndex, depth, node ->
            if (index == forIndex) {
                n = node
            }
        }
        return Graph(n.copy())
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Graph

        if (rootNode != other.rootNode) return false

        return true
    }

    override fun hashCode(): Int {
        return rootNode.hashCode()
    }

    fun replaceNode(i: Int, graph: Graph): Graph {
        if (i < 0 || i >= nodesCount) {
            throw ArrayIndexOutOfBoundsException()
        }
        val newRootNode = rootNode.copy()
        if (i == 0) {
            return Graph(graph.rootNode.copy())
        } else {
            val ind = AtomicInteger(0)
            forEachRecursive(ind, newRootNode, null) {
                index, node, parentNode ->
                if (index.get() == i) {
                    if (parentNode != null) {
                        val childIndex = parentNode.children.indexOf(node)
                        parentNode.children.add(childIndex, graph.rootNode)
                        parentNode.children.remove(node)
                    }
                }
            }
        }
        val newGraph = Graph(newRootNode);
        return newGraph
    }

    override fun toString(): String {
        val sb = StringBuilder()
        var lastDepth = 0
        forEachNode {
            index, depth, node ->
            if (depth > lastDepth) {
                sb.append("\n")
                lastDepth = depth
            }
            sb.append(node)
        }
        return sb.toString()
    }

    fun forEachNode(iterator: (index: Int, depth: Int, currentNode: Node<Operation>) -> Unit) {
        var index = 0
        var depth = 0
        val nodes = mutableListOf(rootNode)
        val newList = mutableListOf<Node<Operation>>()
        while (nodes.size > 0) {
            for (node in nodes) {
                iterator(index, depth, node)
                index++
                newList.addAll(node.children)
            }
            depth++
            nodes.clear()
            nodes.addAll(newList)
            newList.clear()
        }
    }

    fun forEachRecursive(index: AtomicInteger, node: Node<Operation>, parentNode: Node<Operation>?, iterator: (index: AtomicInteger, node: Node<Operation>, parentNode: Node<Operation>?) -> Unit) {
        iterator(index, node, parentNode)
        index.set(index.get() + 1)
        node.children.forEach {
            forEachRecursive(index, it, node, iterator)
        }
    }

}