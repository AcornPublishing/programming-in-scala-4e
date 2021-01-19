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

object SemiInference {

  /* This method has multiple statements on the same line,
   * so you need to write semicolons explicitly. */
  def printProd2(): Unit = {
    val s = "hello"; println(s)
  }

  /* This method has just one statement.  The compiler
   * will not insert semicolons in the middle of an if. */
  def check(x: Int): Unit = {
    if (x < 2)
      println("too small")
    else
      println("ok")
  }
}
