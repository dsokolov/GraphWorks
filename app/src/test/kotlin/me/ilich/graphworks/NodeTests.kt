package me.ilich.graphworks

import me.ilich.graphworks.nodes.ConstNode
import me.ilich.graphworks.nodes.SumNode
import org.junit.Assert.assertEquals
import org.junit.Test

class NodeTests {

    @Test
    fun constNode() {
        val node = ConstNode(10.0)
        assertEquals(10.0, node.calc(), 0.1)
    }

    @Test
    fun sumNode() {
        val node = SumNode()
        assertEquals(30.0, node.calc(10.0, 20.0), 0.1)
    }


}
