package fp.kotlin.example.chapter10.exercise

import fp.kotlin.example.chapter10.Monad

/**
 *
 * 연습문제 10-1
 *
 * ``Monad`` 타입클래스의 인스턴스인 ``FunList``를 작성하여 리스트 모나드를 만들어보자.
 *
 * 힌트 : append 함수를 사용하자.
 */

fun main() {
    val funlist = Cons(1,Cons(2, Cons(3, Nil)))
    val result = funlist.flatMap { Cons(it, Cons(it*2, Cons(it*3, Nil))) }

    require(result == Cons(1, Cons(2, Cons(3, Cons(2, Cons(4, Cons(6, Cons(3, Cons(6, Cons(9, Nil))))))))))
}

sealed class FunList <out A>: Monad<A> {

    override infix fun <V> pure(value: V): Monad<V> = TODO()

    override infix fun <B> flatMap(f: (A) -> Monad<B>): Monad<B> = TODO()

    infix fun <A>FunList<A>.mappend(other: FunList<A>): FunList<A> = TODO()
}

data class Cons<out A>(val head: A, val tail: FunList<A>) : FunList<A>()

object Nil: FunList<Nothing>()