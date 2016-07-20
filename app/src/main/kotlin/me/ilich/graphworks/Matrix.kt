package me.ilich.graphworks

class Matrix<T>(val size: Int, val defaultCell: T? = null) {

    private val cells: MutableList<T?> = mutableListOf()

    init {
        fill()
    }

    private fun fill() {
        for (i in 0..size * size) {
            cells.add(i, defaultCell)
        }
    }

    operator fun set(x: Int, y: Int, v: T?) {
        checkPos(x, y)
        cells[linearSize(x, y)] = v
    }

    operator fun get(x: Int, y: Int): T? {
        checkPos(x, y)
        return cells[linearSize(x, y)]
    }

    private fun linearSize(x: Int, y: Int): Int = size * x + y

    private fun checkPos(x: Int, y: Int) {
        if (x !in 0..size - 1 || y !in 0..size - 1)
            throw ArrayIndexOutOfBoundsException("Try to access ($x,$y) but matrix size is ($size,$size).")
    }

    fun clear() {
        cells.clear()
        fill()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Matrix<*>

        if (size != other.size) return false
        if (cells != other.cells) return false

        return true
    }

    override fun hashCode(): Int {
        var result = size
        result = 31 * result + cells.hashCode()
        return result
    }

    override fun toString(): String {
        val sb = StringBuilder();
        if (size == 0) {
            sb.append("()")
        } else {
            for (i in 0..size - 1) {
                sb.append("( ")
                for (j in 0..size - 1) {
                    sb.append("${this[i, j]}")
                    if (j != size - 1) {
                        sb.append(", ")
                    }
                }
                sb.append(" )")
                if (i != size - 1) {
                    sb.append("\n")
                }
            }
        }
        return sb.toString()
    }

}