package me.ilich

abstract class NodeContainer() {

    abstract fun getGraph1(): Graph

    abstract fun getId(): Int?

}

class Node(val graph: Graph, val name: String, val id: Int) : NodeContainer() {

    override fun getId(): Int = id

    override fun getGraph1() = graph

}

class Pos(val index: Int, val parent: Int?, val node: Node)

class Graph() : NodeContainer() {

    override fun getId(): Int? = null

    val poses = mutableListOf<Pos>()

    fun print() {
        println("begin")
        for (pos in poses) {
            println("${pos.index}) ${pos.parent} ${pos.node.name}")
        }
        println("end")
    }

    override fun getGraph1(): Graph = this

}

fun graph(init: Graph.() -> Unit): Graph {
    val g = Graph()
    g.init()
    return g
}

fun NodeContainer.const(init: NodeContainer.() -> Unit): Node {
    val g = this.getGraph1()
    val id = g.poses.size
    val parentId = this.getId()
    val n = Node(g, "const", id)
    g.poses.add(Pos(id, parentId, n))
    n.init()
    return n
}

fun NodeContainer.add(init: NodeContainer.() -> Unit): Node {
    val g = this.getGraph1()
    val id = g.poses.size
    val parentId = this.getId()
    val n = Node(g, "add", id)
    g.poses.add(Pos(id, parentId, n))
    n.init()
    return n
}


fun main(args: Array<String>) {
    val g = graph() {
        add {
            add {
                const {  }
                const {  }
            }
            const {  }
        }
    }
    g.print()
}