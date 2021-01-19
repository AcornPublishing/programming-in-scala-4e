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

class Rational(n: Int, d: Int) {
  require(d != 0)
  val numer: Int = n
  val denom: Int = d
  override def toString = s"$numer/$denom"
  def add(that: Rational): Rational =
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom
    )
}

object Main {
  def main(args: Array[String]): Unit = {
    val oneHalf = new Rational(1, 2)
    val twoThirds = new Rational(2, 3)
    val sum = oneHalf add twoThirds

    println("oneHalf [" + oneHalf + "]")
    println("twoThirds [" + twoThirds + "]")
    println("sum [" + sum + "]")
    println("sum.numer [" + sum.numer + "]")
    println("sum.denom [" + sum.denom + "]")
  }
}
