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
  val fruit = List("apples", "oranges", "pears")
  val nums = List(1, 2, 3, 4)
  val diag3 =
    List(
      List(1, 0, 0),
      List(0, 1, 0),
      List(0, 0, 1)
    )
  val empty = List()

  object ExplicitTypes {
    val fruit: List[String] = List("apples", "oranges", "pears")
    val nums: List[Int] = List(1, 2, 3, 4)
    val diag3: List[List[Int]] =
      List(
        List(1, 0, 0),
        List(0, 1, 0),
        List(0, 0, 1)
      )
    val empty: List[Nothing] = List()
  }

  // List() is also of type List[String]!
  val xs: List[String] = List()  

  object ExplicitCons {
    val fruit = "apples" :: ("oranges" :: ("pears" :: Nil))
    val nums  = 1 :: (2 :: (3 :: (4 :: Nil)))
    val diag3 = (1 :: (0 :: (0 :: Nil))) ::
                (0 :: (1 :: (0 :: Nil))) ::
                (0 :: (0 :: (1 :: Nil))) :: Nil
    val empty = Nil
  }
 
  object NoParens {
    val nums = 1 :: 2 :: 3 :: 4 :: Nil
  }

  def append[T](xs: List[T], ys: List[T]): List[T] =
    xs match { 
      case List() => ys
      case x :: xs1 => x :: append(xs1, ys)
    }

  def msort[T](less: (T, T) => Boolean)
      (xs: List[T]): List[T] = {
  
    def merge(xs: List[T], ys: List[T]): List[T] =
      (xs, ys) match {
        case (Nil, _) => ys
        case (_, Nil) => xs
        case (x :: xs1, y :: ys1) =>
          if (less(x, y)) x :: merge(xs1, ys)
          else y :: merge(xs, ys1)
      }
  
    val n = xs.length / 2
    if (n == 0) xs
    else {
      val (ys, zs) = xs splitAt n
      merge(msort(less)(ys), msort(less)(zs))
    }
  }

  def assoc = {
    val xs = List("x")
    val ys = List("y")
    val zs = List("z")
    val l1 =
      xs ::: ys ::: zs
    val l2 =
      xs ::: (ys ::: zs)
    l1 == l2
  }

  def forList = {
    for (i <- List.range(1, 5); j <- List.range(1, i)) yield (i, j)
  }

  def main(args: Array[String]): Unit = {
    println("assoc [" + assoc + "]")
    println("msort((x: Int, y: Int) => x < y)(List(5, 7, 1, 3)) [" +
             msort((x: Int, y: Int) => x < y)(List(5, 7, 1, 3)) + "]")

    println("forList [" + forList + "]")
  }
}
