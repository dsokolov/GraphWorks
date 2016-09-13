package me.ilich.graphworks.operations

import me.ilich.graphworks.Node
import org.junit.Assert.assertEquals
import org.junit.Test

class ConstTest {

    val operation = Const(1.0)

    @Test fun calc() {
        assertEquals(1.0, operation.calc(), 0.1)
        assertIndexOutOfBoundsException { operation.calc(1.0) }
        assertIndexOutOfBoundsException { operation.calc(1.0, 2.0) }
        assertIndexOutOfBoundsException { operation.calc(1.0, 2.0, 3.0) }
    }

    @Test fun asString() {
        assertEquals(operation.asString(), "1.0")
        assertIndexOutOfBoundsException { operation.asString("1.0") }
        assertIndexOutOfBoundsException { operation.asString("1.0", "2.0") }
        assertIndexOutOfBoundsException { operation.asString("1.0", "2.0", "3.0") }
    }

    @Test fun toConst() {
        assertEquals(operation.toConst(), Const(1.0))
        assertIndexOutOfBoundsException { operation.toConst(Const(1.0)) }
        assertIndexOutOfBoundsException { operation.toConst(Const(1.0), Const(2.0)) }
        assertIndexOutOfBoundsException { operation.toConst(Const(1.0), Const(2.0), Const(3.0)) }
    }

    @Test fun equalsTest() {
        assertEquals(Const(1.0), operation)
        assertEquals(const(1.0) { }, Node<Operation>(operation))
    }

    @Test fun toStringTest() {
        assertEquals("Operation 1.0", operation.toString())
    }

}