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

object Ex12  {
  import Element.elem
  
  abstract class Element {
  
    def contents: Array[String]
  
    def width: Int =
      if (height == 0) 0 else contents(0).length
  
    def height: Int = contents.length
  
    def above(that: Element): Element =
      elem(this.contents ++ that.contents)
  
    def beside(that: Element): Element =
      elem(
        for (
          (line1, line2) <- this.contents zip that.contents
        ) yield line1 + line2
      )
  
    override def toString = contents mkString "\n"
  }

  object Element {
  
    def elem(contents: Array[String]): Element = 
      new ArrayElement(contents)
  
    def elem(chr: Char, width: Int, height: Int): Element = 
      new UniformElement(chr, width, height)
  
    def elem(line: String): Element = 
      new LineElement(line)
  }

  class ArrayElement(conts: Array[String]) extends Element {
    def contents: Array[String] = conts
  }

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
    val lineElem = new LineElement("foo")
    println("lineElem [" + lineElem + "]")
  }
}
