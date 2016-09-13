package me.ilich.graphworks

import me.ilich.graphworks.operations.*
import java.util.*

object GraphFactory {

    private val random = Random()

    fun randomOperation(): Operation {
        val index = random.nextInt(10)
        when (index) {
            0 -> return Abs()
            1 -> return Add()
            2 -> return Div()
            3 -> return Mult()
            4 -> return Param("x") //TODO param name
            5 -> return Sub()
            else -> {
                val const = random.nextDouble() * 20 - 10 //TODO const bound
                return Const(const)
            }
        }
    }

    fun randomGraph(): Graph {
        val rootNode = randomNode()
        return Graph(rootNode)
    }

    private fun randomNode(): Node<Operation> {
        val operation = randomOperation()
        val node = Node<Operation>(operation)
        var maxArgsCount = 2
        if (operation.maxArgCount != null) {
            maxArgsCount = operation.maxArgCount
        }
        var minArgsCount = 1
        if (operation.minArgCount != null) {
            minArgsCount = operation.minArgCount
        }
        val argsCount = random.nextInt(maxArgsCount - minArgsCount + 1) + minArgsCount
        if (argsCount > 0) {
            for (i in 0..argsCount - 1) {
                val subNode = randomNode()
                node.children.add(subNode)
            }
        }
        return node
    }

}
