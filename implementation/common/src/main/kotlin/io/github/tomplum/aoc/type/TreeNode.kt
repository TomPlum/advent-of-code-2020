package io.github.tomplum.aoc.type

open class TreeNode<T>(val value: T) {
    var parents: MutableList<TreeNode<T>> = mutableListOf()

    var children: MutableList<TreeNode<T>> = mutableListOf()

    fun addChild(node: TreeNode<T>) {
        children.add(node)
        node.parents.add(this)
    }

    open fun isLeaf(): Boolean = children.isEmpty()

    //fun getNode(value: T): TreeNode<T>? = children.find { it.getNode(value) }

    override fun toString(): String {
        var s = "$value"
        if (children.isNotEmpty()) {
            s += " {" + children.map { it.toString() } + " }"
        }
        return s
    }
}