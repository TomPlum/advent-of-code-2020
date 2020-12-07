package io.github.tomplum.aoc.type

open class TreeNode<T>(val value: T) {
    var parents: MutableList<TreeNode<T>> = mutableListOf()

    var children: MutableList<TreeNode<T>> = mutableListOf()

    var depth = 0

    fun addChild(node: TreeNode<T>) {
        node.depth = this.depth + 1
        children.add(node)
        node.parents.add(this)
    }

    fun allChildren(): Set<TreeNode<T>> = children.toSet() + children.flatMap { it.allChildren() }

    fun allParents(): Set<TreeNode<T>> = parents.toSet() + parents.flatMap { it.allParents() }

    fun isRoot(): Boolean = parents.size == 0

    open fun isLeaf(): Boolean = children.isEmpty()

    override fun toString(): String {
        var s = "$value"
        if (children.isNotEmpty()) {
            s += " {" + children.map { it.toString() } + " }"
        }
        return s
    }
}