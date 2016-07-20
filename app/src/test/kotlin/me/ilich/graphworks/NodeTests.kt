package me.ilich.graphworks

import me.ilich.graphworks.nodes.AbsNode
import me.ilich.graphworks.nodes.ConstNode
import me.ilich.graphworks.nodes.MinuseNode
import me.ilich.graphworks.nodes.PluseNode
import org.junit.Assert.assertEquals
import org.junit.Test

class NodeTests {

    @Test
    fun constNode() {
        val node = ConstNode(10.0)
        assertEquals(10.0, node.calc(), 0.1)
        assertEquals(node.asString(), "10.0")
    }

    @Test
    fun absNode() {
        val node = AbsNode()
        assertEquals(10.0, node.calc(-10.0), 0.1)
        assertEquals(10.0, node.calc(10.0), 0.1)
        assertEquals(0.0, node.calc(0.0), 0.1)
        assertEquals(node.asString("10.0"), "| 10.0 |")
    }

    @Test
    fun pluseNode() {
        val node = PluseNode()
        assertEquals(30.0, node.calc(10.0, 20.0), 0.1)
        assertEquals(node.asString("10.0", "20.0"), "( 10.0 + 20.0 )")
    }

    @Test
    fun minuseNode() {
        val node = MinuseNode()
        assertEquals(-10.0, node.calc(10.0, 20.0), 0.1)
        assertEquals(node.asString("10.0", "20.0"), "( 10.0 - 20.0 )")
    }


}
