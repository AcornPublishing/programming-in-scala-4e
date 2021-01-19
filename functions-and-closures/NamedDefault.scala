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

object NamedDefault {
  def printTime(out: java.io.PrintStream = Console.out) =
    out.println("time = " + System.currentTimeMillis())

  def printTime2(out: java.io.PrintStream = Console.out,
                 divisor: Int = 1) =
    out.println("time = " + System.currentTimeMillis()/divisor)

  def main(args: Array[String]): Unit = {
    // Calling printTime with no arguments
    printTime()

    // Calling printTime with an argument specified
    printTime(Console.err)

    // printTime2 can be called many ways
    printTime2()
    printTime2(out=Console.err)
    printTime2(divisor=1000)
    printTime2(out=Console.err, divisor=1000)
  }
}
