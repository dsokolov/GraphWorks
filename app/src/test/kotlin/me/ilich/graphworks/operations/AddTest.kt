package me.ilich.graphworks.operations

import me.ilich.graphworks.Node
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class AddTest {

    val operation = Add()

    @Test fun calc() {
        assertIndexOutOfBoundsException { operation.calc() }
        assertIndexOutOfBoundsException { operation.calc(1.0) }
        assertEquals(3.0, operation.calc(1.0, 2.0), 0.1)
        assertEquals(6.0, operation.calc(1.0, 2.0, 3.0), 0.1)
    }

    @Test fun asString() {
        assertIndexOutOfBoundsException { operation.asString() }
        assertIndexOutOfBoundsException { operation.asString("1.0") }
        assertEquals(operation.asString("1.0", "2.0"), "1.0 + 2.0")
        assertEquals(operation.asString("1.0", "2.0", "3.0"), "1.0 + 2.0 + 3.0")
    }

    @Test fun toConst() {
        assertIndexOutOfBoundsException { operation.toConst() }
        assertIndexOutOfBoundsException { operation.toConst(Const(1.0)) }
        assertEquals(operation.toConst(Const(1.0)), Const(1.0))
        assertNull(operation.toConst(Param("x")))
        assertIndexOutOfBoundsException { operation.toConst(Const(1.0), Const(2.0)) }
        assertIndexOutOfBoundsException { operation.toConst(Const(1.0), Const(2.0), Const(3.0)) }
    }

    @Test fun equalsTest() {
        assertEquals(Abs(), operation)
        assertEquals(abs { }, Node<Operation>(operation))
    }

    @Test fun toStringTest() {
        assertEquals("Operation abs()", operation.toString())
    }

}