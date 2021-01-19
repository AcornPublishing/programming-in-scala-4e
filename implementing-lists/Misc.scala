/*
 * Copyright (C) 2007-2019 Artima, Inc. All rights reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Example code from:
 *
 * Programming in Scala, Fourth Edition
 * by Martin Odersky, Lex Spoon, Bill Venners
 *
 * http://booksites.artima.com/programming_in_scala_4ed
 */

object Misc {
  object Obj1 {
    sealed abstract class List[+A] {
        def isEmpty: Boolean
        def head: A
        def tail: List[A]

      def length: Int = 
        if (isEmpty) 0 else 1 + tail.length

      def drop(n: Int): List[A] = 
        if (isEmpty) Nil
        else if (n <= 0) this
        else tail.drop(n - 1)

      def ::[B >: A](x: B): List[B] = new ::(x, this)

        def :::[B >: A](prefix: List[B]): List[B] = 
          if (prefix.isEmpty) this
          else prefix.head :: prefix.tail ::: this

      def map[B](f: A => B): List[B] =
        if (isEmpty) Nil
        else f(head) :: tail.map(f)
    }

    case object Nil extends List[Nothing] {
      override def isEmpty = true
      def head: Nothing =
        throw new NoSuchElementException("head of empty list")
      def tail: List[Nothing] =
        throw new NoSuchElementException("tail of empty list")
    }

    final case class ::[A](hd: A, tl: List[A]) extends List[A] {
      def head = hd
      def tail = tl
      override def isEmpty: Boolean = false
    }
  }

  object Obj2 {
    sealed abstract class List[+A] {
        def isEmpty: Boolean
        def head: A
        def tail: List[A]
    }

    final case class ::[A](head: A, tail: List[A])
        extends List[A] {
    
      override def isEmpty: Boolean = false
    }
  }

  object Obj3 {
    def incAll(xs: List[Int]): List[Int] = xs match {
      case List() => List()
      case x :: xs1 => x + 1 :: incAll(xs1)
    }

    def incAll2(xs: List[Int]): List[Int] = {
      var result = List[Int]()    // a very inefficient approach
      for (x <- xs) result = result ::: List(x + 1)
      result
    }

    import scala.collection.mutable.ListBuffer

    def incAll3(xs: List[Int]): List[Int] = {
      val buf = new ListBuffer[Int]
      for (x <- xs) buf += x + 1
      buf.toList
    }
  }

  object Obj4 {
    abstract class Buffer[T] {
      def toList: List[T]
    }

    class ListBuffer[A] extends Buffer[A] {
      private var first: List[A] = Nil
      private var last0: ::[A] = null
      private var aliased: Boolean = false
    
      override def toList: List[A] = {
      aliased = nonEmpty
      first
      }
    }
  }

  def main(args: Array[String]): Unit = {
    println("Obj3.incAll(List(2, 3)) [" + Obj3.incAll(List(2, 3)) + "]")
    println("Obj3.incAll2(List(2, 3)) [" + Obj3.incAll2(List(2, 3)) + "]")
    println("Obj3.incAll3(List(2, 3)) [" + Obj3.incAll3(List(2, 3)) + "]")

    val xs = List(0)
    val ys = 1 :: xs
    val zs = 2 :: xs
    println("ys [" + ys + "]")
    println("zs [" + zs + "]")
  }
}

package scala {
  object Obj5 {
    sealed abstract class List[+A] {
        def isEmpty: Boolean
        def head: A
        def tail: List[A]
    }

    final case class :: [A](override val head: A,
      private[scala] var next: List[A]) extends List[A] {
    
    def isEmpty: Boolean = false
    def tail: List[A] = next
    }
  }
}

