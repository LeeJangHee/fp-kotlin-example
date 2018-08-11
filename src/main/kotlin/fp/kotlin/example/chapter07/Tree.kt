package fp.kotlin.example.chapter07

import kotlin.Nothing

fun main(args: Array<String>) {
    val tree = treeOf(1,
            treeOf(2,
                    treeOf(3), treeOf(4)),
            treeOf(5,
                    treeOf(6), treeOf(7)))

    println(tree)

    val transformedTree = tree.fmap { it + 1 }

    println(transformedTree)
}

private fun <T> treeOf(value: T, leftTree: Tree<T> = EmptyTree, rightTree: Tree<T> = EmptyTree): Tree<T> =
        Node(value, leftTree, rightTree)

sealed class Tree<out A> : Functor<A> {

    abstract override fun toString(): String

    abstract override fun <B> fmap(transform: (A) -> B): Tree<B>
}

data class Node<out A>(val value: A, val leftTree: Tree<A>, val rightTree: Tree<A>) : Tree<A>() {

    override fun toString(): String = "(N $value $leftTree $rightTree)"

    override fun <B> fmap(transform: (A) -> B): Tree<B> =
            Node(transform(value), leftTree.fmap(transform), rightTree.fmap(transform))
}

object EmptyTree : Tree<kotlin.Nothing>() {

    override fun toString(): String = "E"

    override fun <B> fmap(transform: (Nothing) -> B): Tree<B> = EmptyTree
}