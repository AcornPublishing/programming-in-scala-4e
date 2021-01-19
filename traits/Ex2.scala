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

object Ex2 {
  class Animal
  
  class Frog extends Animal with Philosophical {
    override def toString = "green"
    override def philosophize() = {
      println("It ain't easy being " + toString + "!")
    }
  }

  class Point(x: Int, y: Int)
  trait CharSequence {
    def charAt(index: Int): Char
    def length: Int
    def subSequence(start: Int, end: Int): CharSequence
    def toString(): String
  }
  trait Ordered[T] {
    def compare(that: T): Int
  
    def <(that: T): Boolean = (this compare that) < 0
    def >(that: T): Boolean = (this compare that) > 0
    def <=(that: T): Boolean = (this compare that) <= 0
    def >=(that: T): Boolean = (this compare that) >= 0
  }

  import scala.collection.mutable.ArrayBuffer
  
  class BasicIntQueue extends IntQueue {
    private val buf = new ArrayBuffer[Int]
    def get() = buf.remove(0)
    def put(x: Int) = { buf += x }
  }
  trait Incrementing extends IntQueue {
    abstract override def put(x: Int) = { super.put(x + 1) }
  }
  trait Doubling extends IntQueue {
    abstract override def put(x: Int) = { super.put(2 * x) }
  }

  def main(args: Array[String]): Unit = {
    (new Frog).philosophize()

    // Multiple inheritance thought experiment
    val q = new BasicIntQueue with Incrementing with Doubling
    q.put(42)  // which put would be called?
    println("q [" + q + "]")
  }
}
