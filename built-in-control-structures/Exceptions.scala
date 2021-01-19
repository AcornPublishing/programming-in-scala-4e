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

object Exceptions {
  def throws1: Unit = {
    throw new IllegalArgumentException
  }

  def throws2(n: Int) = {
    val half =
      if (n % 2 == 0)
        n / 2
      else
        throw new RuntimeException("n must be even")
    half
  }

  def throws3: Unit = {
    import java.io.FileReader
    import java.io.FileNotFoundException
    import java.io.IOException
    
    try {
      val f = new FileReader("input.txt")
      // Use and close file
      println("f [" + f + "]")
    } catch {
      case ex: FileNotFoundException => // Handle missing file
        println("ex [" + ex + "]")
      case ex: IOException => // Handle other I/O error
        println("ex [" + ex + "]")
    }
  }

  def finally1: Unit = {
    import java.io.FileReader
    
    val file = new FileReader("input.txt")
    try {
      // Use the file
    } finally {
      file.close()  // Be sure to close the file
    }
  }

  import java.net.URL
  import java.net.MalformedURLException
  
  def urlFor(path: String) =
    try {
      new URL(path)
    } catch {
      case e: MalformedURLException =>
        new URL("http://www.scala-lang.org")
    }
  def f(): Int = try return 1 finally return 2
  def g(): Int = try 1 finally 2
}

try {
  Exceptions.throws1
} catch {
  case ex: Throwable =>
    println("ex [" + ex + "]")
}

try {
  println("Exceptions.throws2(2) [" + Exceptions.throws2(2) + "]")
  Exceptions.throws2(3)
} catch {
  case ex: Throwable =>
    println("ex [" + ex + "]")
}

Exceptions.throws3
println("Exceptions.urlFor(\"blah\") [" + Exceptions.urlFor("blah") + "]")
println("Exceptions.f [" + Exceptions.f + "]")
println("Exceptions.g [" + Exceptions.g + "]")
