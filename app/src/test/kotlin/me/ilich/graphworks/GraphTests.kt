package me.ilich.graphworks

import me.ilich.graphworks.operations.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class GraphTests {

    @Test
    fun calcA() {
        val g = Graph(
                add {
                    const(4.0)
                    const(5.0)
                }
        )
        assertEquals(9.0, g.calc(), 0.1)
        assertEquals("( 4.0 + 5.0 )", g.asString())
    }

    @Test
    fun calcB() {
        val g = Graph(
                const(10.0)
        )
        assertEquals(g.calc(), 10.0, 0.1)
        assertEquals("10.0", g.asString())
    }

    @Test
    fun calcC() {
        val g = Graph(
                add {
                    add {
                        const(2.0)
                        const(3.0)
                    }
                    const(1.0)
                }
        )
        assertEquals(g.calc(), 6.0, 0.1)
        assertEquals("( ( 2.0 + 3.0 ) + 1.0 )", g.asString())
    }

    @Test
    fun calcD() {
        val source: (String) -> Double = {
            if (it == "x") {
                2.0
            } else if (it == "y") {
                3.0
            } else {
                0.0
            }
        }

        val g = Graph(
                add() {
                    mult {
                        param("x")
                        param("y")
                    }
                    const(4.0)
                }
        )
        assertEquals(10.0, g.calc(paramSource = source), 0.1)
        assertEquals("( ( x * y ) + 4.0 )", g.asString())
    }


    @Test
    fun equalsTest() {
        assertEquals(
                Graph(
                        const(10.0)
                ),
                Graph(
                        const(10.0)
                )
        )
        assertNotEquals(
                Graph(
                        const(5.0)
                ),
                Graph(
                        const(10.0)
                )
        )

        assertEquals(
                Graph(
                        add {
                            const(10.0)
                            const(11.0)
                        }
                ),
                Graph(
                        add {
                            const(10.0)
                            const(11.0)
                        }
                )
        )
        assertNotEquals(
                Graph(
                        add {
                            const(10.0)
                            const(11.0)
                        }
                ),
                Graph(
                        add {
                            const(10.0)
                            const(12.0)
                        }
                )
        )
    }

    @Test
    fun subGraph() {
        val big = Graph(
                add { //0
                    add { //1
                        const(1.0) //3
                        const(2.0) //4
                    }
                    const(3.0) //2
                }
        )
        val sub0 = Graph(
                add {
                    add {
                        const(1.0)
                        const(2.0)
                    }
                    const(3.0)
                }
        )
        val sub1 = Graph(
                add {
                    const(1.0)
                    const(2.0)
                }
        )
        val sub2 = Graph(
                const(3.0)
        )
        val sub3 = Graph(
                const(1.0)
        )
        val sub4 = Graph(
                const(2.0)
        )

        assertEquals(sub0, big)
        assertEquals(sub0, big[0])
        assertEquals(sub1, big[1])
        assertEquals(sub2, big[2])
        assertEquals(sub3, big[3])
        assertEquals(sub4, big[4])
    }

    @Test fun replace() {
        val g0 = Graph(
                add { // 0
                    const(2.0) // 1
                    const(3.0) // 2
                }
        )
        val g1 = Graph(
                const(30.0)
        )
        val g3 = Graph(
                add {
                    const(30.0)
                    const(3.0)
                }
        )
        val g4 = Graph(
                add {
                    const(2.0)
                    const(30.0)
                }
        )
        assertEquals(g1, g0.replaceNode(0, g1))
        assertEquals(g0, g0.replaceNode(0, g0))
        assertEquals(g3, g0.replaceNode(1, g1))
        assertEquals(g4, g0.replaceNode(2, g1))
    }

    @Test fun replace2() {
        val g = Graph(
                add {
                    mult {
                        const(2.0)
                        const(3.0)
                    }
                    div {
                        const(16.0)
                        const(4.0)
                    }
                }
        )
        val paste = Graph(
                const(999.0)
        )

        assertEquals(Graph(
                const(999.0)
        ), g.replaceNode(0, paste))

        assertEquals(Graph(
                add {
                    const(999.0)
                    div {
                        const(16.0)
                        const(4.0)
                    }
                }
        ), g.replaceNode(1, paste))

        assertEquals(Graph(
                add {
                    mult {
                        const(999.0)
                        const(3.0)
                    }
                    div {
                        const(16.0)
                        const(4.0)
                    }
                }
        ), g.replaceNode(2, paste))

        assertEquals(Graph(
                add {
                    mult {
                        const(2.0)
                        const(999.0)
                    }
                    div {
                        const(16.0)
                        const(4.0)
                    }
                }
        ), g.replaceNode(3, paste))

        assertEquals(Graph(
                add {
                    mult {
                        const(2.0)
                        const(3.0)
                    }
                    const(999.0)
                }
        ), g.replaceNode(4, paste))

        assertEquals(Graph(
                add {
                    mult {
                        const(2.0)
                        const(3.0)
                    }
                    div {
                        const(999.0)
                        const(4.0)
                    }
                }
        ), g.replaceNode(5, paste))


        assertEquals(Graph(
                add {
                    mult {
                        const(2.0)
                        const(3.0)
                    }
                    div {
                        const(16.0)
                        const(999.0)
                    }
                }
        ), g.replaceNode(6, paste))

        assertEquals(Graph(
                add {
                    mult {
                        const(2.0)
                        const(3.0)
                    }
                    div {
                        const(16.0)
                        const(4.0)
                    }
                }
        ), g.replaceNode(7, paste))

    }

}
