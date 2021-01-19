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

object Misc {
  case class Person(name: String, 
                    isMale: Boolean, 
                    children: Person*)

  val lara = Person("Lara", false)
  val bob = Person("Bob", true)
  val julie = Person("Julie", false, lara, bob)
  val persons = List(lara, bob, julie)

  def for1 = {
    for (p <- persons; n = p.name; if (n startsWith "To")) 
    yield n
  }

  def for2 = {
    for {
      p <- persons              // a generator
      n = p.name                // a definition
      if (n startsWith "To")    // a filter
    } yield n
  }

  def queens(n: Int): List[List[(Int, Int)]] = {
    def placeQueens(k: Int): List[List[(Int, Int)]] =
      if (k == 0) 
        List(List())
      else 
        for {
          queens <- placeQueens(k - 1)
          column <- 1 to n
          queen = (k, column)
          if isSafe(queen, queens) 
        } yield queen :: queens
  
    placeQueens(n)
  }

  def isSafe(queen: (Int, Int), queens: List[(Int, Int)]) = 
    queens forall (q => !inCheck(queen, q))
  
  def inCheck(q1: (Int, Int), q2: (Int, Int)) = 
    q1._1 == q2._1 ||  // same row
    q1._2 == q2._2 ||  // same column
    (q1._1 - q2._1).abs == (q1._2 - q2._2).abs // on diagonal

  def removeDuplicates[A](xs: List[A]): List[A] = {
    if (xs.isEmpty) xs
    else
       xs.head :: removeDuplicates(
         for (x <- xs.tail if x != xs.head) yield x
       )
  }

  def expensiveComputationNotInvolvingX = 3

  def for4 = {
    for (x <- 1 to 1000; y = expensiveComputationNotInvolvingX)
    yield x * y
  }

  def for5 = {
    val y = expensiveComputationNotInvolvingX
    for (x <- 1 to 1000) yield x * y
  }

  val xss = List(List(1, 2, 3), List(2, 3, 4), List(4, 5, 6))

  def sumXss1 = {
    var sum = 0
    for (xs <- xss; x <- xs) sum += x
    sum
  }

  def sumXss2 = {
    var sum = 0
    xss foreach (xs => 
      xs foreach (x =>
        sum += x))
    sum
  }

  object Demo {
    def map[A, B](xs: List[A], f: A => B): List[B] =
      for (x <- xs) yield f(x)
  
    def flatMap[A, B](xs: List[A], f: A => List[B]): List[B] =
      for (x <- xs; y <- f(x)) yield y
  
    def filter[A](xs: List[A], p: A => Boolean): List[A] =
      for (x <- xs if p(x)) yield x
  }

  abstract class C[A] {
    def map[B](f: A => B): C[B]
    def flatMap[B](f: A => C[B]): C[B]
    def withFilter(p: A => Boolean): C[A]
    def foreach(b: A => Unit): Unit
  }

  def main(args: Array[String]): Unit = {
    println("for1 [" + for1 + "]")
    println("for2 [" + for2 + "]")
    println("queens(4) [" + queens(4) + "]")
    println("removeDuplicates(List(1, 2, 3, 1, 2)) [" + removeDuplicates(List(1, 2, 3, 1, 2)) + "]")
    println("for4 [" + for4 + "]")
    println("for5 [" + for5 + "]")
    println("sumXss1 [" + sumXss1 + "]")
    println("sumXss2 [" + sumXss2 + "]")

    println("Demo.map(List(1, 2), (a: Int) => a + 1) [" + Demo.map(List(1, 2), (a: Int) => a + 1) + "]")
  }
}
