package me.ilich.graphworks

import me.ilich.graphworks.operations.*
import org.junit.Assert.*
import org.junit.Test

class OperationTest {

    @Test
    fun paramNode() {
        val source: (String) -> Double = {
            when (it) {
                "x" -> 5.0
                else -> 0.0
            }
        }
        val node = Param("x")
        assertEquals(5.0, node.calc(paramSource = source), 0.1)
        try {
            node.calc(10.0, paramSource = source)
        } catch (e: Exception) {
            assertTrue(e is IndexOutOfBoundsException)
        }
        assertEquals(node.asString(), "x")
        assertEquals(Param("x"), node)
        assertEquals("Operation param x", node.toString())
    }

    @Test
    fun addNode() {
        val node = Add()
        try {
            node.calc()
        } catch (e: Exception) {
            assertTrue(e is IndexOutOfBoundsException)
        }
        try {
            node.calc(1.0)
        } catch (e: Exception) {
            assertTrue(e is IndexOutOfBoundsException)
        }
        assertEquals(30.0, node.calc(10.0, 20.0), 0.1)
        assertEquals(6.0, node.calc(1.0, 2.0, 3.0), 0.1)
        assertEquals(node.asString("10.0", "20.0"), "( 10.0 + 20.0 )")
        assertEquals(node.asString("10.0", "20.0", "30.0"), "( 10.0 + 20.0 + 30.0 )")
        assertEquals(Add(), node)
        assertEquals("Operation +", node.toString())
    }

    @Test
    fun subNode() {
        val node = Sub()
        assertEquals(-10.0, node.calc(10.0, 20.0), 0.1)
        assertEquals(node.asString("10.0", "20.0"), "( 10.0 - 20.0 )")
        assertEquals(Sub(), node)
        assertEquals("Operation -", node.toString())
    }

    @Test
    fun multNode() {
        val node = Mult()
        try {
            node.calc()
        } catch (e: Exception) {
            assertTrue(e is IndexOutOfBoundsException)
        }
        try {
            node.calc(1.0)
        } catch (e: Exception) {
            assertTrue(e is IndexOutOfBoundsException)
        }
        assertEquals(200.0, node.calc(10.0, 20.0), 0.1)
        assertEquals(6000.0, node.calc(10.0, 20.0, 30.0), 0.1)
        assertEquals(node.asString("10.0", "20.0"), "( 10.0 * 20.0 )")
        assertEquals(node.asString("10.0", "20.0", "30.0"), "( 10.0 * 20.0 * 30.0 )")
        assertEquals(Mult(), node)
        assertEquals("Operation *", node.toString())
    }

    @Test
    fun divNode() {
        val node = Div()
        assertEquals(5.0, node.calc(20.0, 4.0), 0.1)
        assertEquals(node.asString("10.0", "20.0"), "( 10.0 / 20.0 )")
        assertEquals(Div(), node)
        assertEquals("Operation /", node.toString())
    }

}
