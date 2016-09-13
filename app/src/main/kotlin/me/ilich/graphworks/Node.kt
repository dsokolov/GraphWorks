package me.ilich.graphworks

import java.util.concurrent.CopyOnWriteArrayList

class Node<T>(val value: T) : Copyable
where T : Copyable {

    val children = CopyOnWriteArrayList<Node<T>>()

    val size: Int
        get() = children.sumBy { it.size } + 1

    override fun copy(): Node<T> {
        val newChildren = mutableListOf<Node<T>>()
        children.forEach {
            newChildren.add(it.copy())
        }
        @Suppress("UNCHECKED_CAST")
        val newValue = value.copy() as T
        val newNode = Node<T>(newValue)
        newNode.children.addAll(newChildren)
        return newNode
    }

    override fun toString(): String = "$value (${children.size})"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Node<*>

        if (value != other.value) return false
        if (children != other.children) return false

        return true
    }

    override fun hashCode(): Int {
        var result = value?.hashCode() ?: 0
        result = 31 * result + children.hashCode()
        return result
    }

}