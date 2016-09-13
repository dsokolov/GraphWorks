package me.ilich.graphworks

import me.ilich.graphworks.operations.Const
import me.ilich.graphworks.operations.const
import org.junit.Assert.*
import org.junit.Test


class NodeTest {

    @Test
    fun testEquals() {
        assertEquals(const(5.0), const(5.0))
        assertEquals(Const(5.0), const(5.0).value)
    }

    @Test
    fun testClone() {
        val a = const(5.0)
        val b = a.copy()
        assertTrue(a == b)
        assertFalse(a === b)
    }

}