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

object Ex3 {
  def divide(x: Int, y: Int): Int = 
    if (y != 0) x / y 
    else sys.error("can't divide by zero")

  def main(args: Array[String]): Unit = {
    val d1 = divide(4, 2)
    println("d1 [" + d1 + "]")

    try {
      val d2 = divide(4, 0)
      println("d2 [" + d2 + "]")
    } catch {
      case ex: RuntimeException => println("ex [" + ex + "]")
    }
  }
}
