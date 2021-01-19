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

object Files {
  val filesHere = (new java.io.File(".")).listFiles
  

  def printFiles(): Unit = {
    for (file <- filesHere)
      println(file)
  }

  def printFilesIter(): Unit = {
    // Not common in Scala...
    for (i <- 0 to filesHere.length - 1)
      println(filesHere(i))
  }

  def printScalaFiles(): Unit = {
    val filesHere = (new java.io.File(".")).listFiles
    
    for (file <- filesHere if file.getName.endsWith(".scala"))
      println(file)
  }

  def printScalaFiles2(): Unit = {
    for (file <- filesHere)
      if (file.getName.endsWith(".scala"))
        println(file)
  }

  def printScalaFiles3(): Unit = {
    for (
      file <- filesHere
      if file.isFile
      if file.getName.endsWith(".scala")
    ) println(file)
  }

  def fileLines(file: java.io.File) = 
    scala.io.Source.fromFile(file).getLines().toArray
  

  def grepParens(pattern: String): Unit = {
    def grep(pattern: String) =
      for (
        file <- filesHere
        if file.getName.endsWith(".scala");
        line <- fileLines(file)
        if line.trim.matches(pattern) 
      ) println(s"$file: ${line.trim}")
    
    grep(pattern)
  }

  def grepGcd(): Unit = {
    def grep(pattern: String) = grepParens(pattern)
    grep(".*gcd.*")
  }

  def grepGcd2(): Unit = {
    def grep(pattern: String) =
      for {
        file <- filesHere
        if file.getName.endsWith(".scala")
        line <- fileLines(file)
        trimmed = line.trim
        if trimmed.matches(pattern)  
      } println(s"$file: $trimmed")
    
    grep(".*gcd.*")
  }

  def scalaFiles =
    for {
      file <- filesHere
      if file.getName.endsWith(".scala")
    } yield file

  val forLineLengths =
    for {
      file <- filesHere
      if file.getName.endsWith(".scala")
      line <- fileLines(file)
      trimmed = line.trim
      if trimmed.matches(".*for.*")  
    } yield trimmed.length
}

Files.printFiles()
Files.printFilesIter()
Files.printScalaFiles()
Files.printScalaFiles2()
Files.printScalaFiles3()
Files.grepParens(".*asdf.*")
Files.grepGcd()
Files.grepGcd2()
println("Files.scalaFiles.toList [" + Files.scalaFiles.toList + "]")
println("Files.forLineLengths.toList [" + Files.forLineLengths.toList + "]")
