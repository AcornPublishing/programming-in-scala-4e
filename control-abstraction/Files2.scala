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

object Files2 {
  def filesHere = (new java.io.File(".")).listFiles

  def filesMatching(query: String,
      matcher: (String, String) => Boolean) = {
  
    for (file <- filesHere; if matcher(file.getName, query))
      yield file
  }

  def filesEnding(query: String) =
    filesMatching(query, _.endsWith(_))
  
  def filesContaining(query: String) =
    filesMatching(query, _.contains(_))
  
  def filesRegex(query: String) =
    filesMatching(query, _.matches(_))

  def main(args: Array[String]): Unit = {
    println("filesEnding(\"scala\").toList [" +
            filesEnding("scala").toList + "]")
    println("filesContaining(\"Files1\").toList [" + 
            filesContaining("Files1").toList + "]")
    println("filesRegex(\".*Re.ex.*\").toList [" + 
            filesRegex(".*Re.ex.*").toList + "]")
  }
}

