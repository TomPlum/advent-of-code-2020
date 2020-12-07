package io.github.tomplum.aoc.type

open class TreeNode<T>(val value: T) {
    var parents: MutableList<TreeNode<T>> = mutableListOf()

    var children: MutableMap<TreeNode<T>, Int> = mutableMapOf()

    var depth = 0

    fun addChild(node: TreeNode<T>, quantity: Int) {
        node.depth = this.depth + 1
        children[node] = quantity
        node.parents.add(this)
    }

    fun allChildren(): Set<TreeNode<T>> = children.keys.toSet() + children.keys.flatMap { it.allChildren() } + this

    fun allParents(): Set<TreeNode<T>> = parents.toSet() + parents.flatMap { it.allParents() }

    fun getBagRequirement(): Int = children.map { (bag, quantity) -> bag.getBagRequirement() * quantity }.sum() + 1

    fun isRoot(): Boolean = parents.size == 0

    fun isLeaf(): Boolean = children.isEmpty()

    override fun toString(): String {
        var s = "$value"
        if (children.isNotEmpty()) {
            s += " {" + children.map { it.toString() } + " }"
        }
        return s
    }
}