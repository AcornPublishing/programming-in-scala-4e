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

object Ex9  {
  abstract class Element {
    def contents: Array[String]
    def height: Int = contents.length
    def width: Int = if (height == 0) 0 else contents(0).length
  }

  class ArrayElement(
    val contents: Array[String]
  ) extends Element

  class LineElement(s: String) extends ArrayElement(Array(s)) {
    override def width = s.length
    override def height = 1
  }

  class UniformElement(
    ch: Char, 
    override val width: Int,
    override val height: Int 
  ) extends Element {
    private val line = ch.toString * width
    def contents = Array.fill(height)(line)
  }

  def main(args: Array[String]): Unit = {
    val e1: Element = new ArrayElement(Array("hello", "world"))
    val ae: ArrayElement = new LineElement("hello")
    val e2: Element = ae
    val e3: Element = new UniformElement('x', 2, 3)

    println("e1 [" + e1 + "]")
    println("ae [" + ae + "]")
    println("e2 [" + e2 + "]")
    println("e3 [" + e3 + "]")
  }
}
