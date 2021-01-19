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

object Files1 {
  object FileMatcher {
    private def filesHere = (new java.io.File(".")).listFiles
  
    def filesEnding(query: String) =
      for (file <- filesHere; if file.getName.endsWith(query))
        yield file

    def filesContaining(query: String) =
      for (file <- filesHere; if file.getName.contains(query))
        yield file

    def filesRegex(query: String) =
      for (file <- filesHere; if file.getName.matches(query))
        yield file
  }

  def main(args: Array[String]): Unit = {
    println("FileMatcher.filesEnding(\"scala\").toList [" +
            FileMatcher.filesEnding("scala").toList + "]")
    println("FileMatcher.filesContaining(\"Files1\").toList [" + 
            FileMatcher.filesContaining("Files1").toList + "]")
    println("FileMatcher.filesRegex(\".*Re.ex.*\").toList [" + 
            FileMatcher.filesRegex(".*Re.ex.*").toList + "]")
  }
}

