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
  object Obj1 {
    implicit def intToString(x: Int) = x.toString
    implicit def int2double(x: Int): Double = x.toDouble

    val aMap =
      Map(1 -> "one", 2 -> "two", 3 -> "three")

    def maxListOrdering[T](elements: List[T])
          (ordering: Ordering[T]): T = 
      elements match {
        case List() =>
          throw new IllegalArgumentException("empty list!")
        case List(x) => x
        case x :: rest =>
          val maxRest = maxListOrdering(rest)(ordering)
          if (ordering.gt(x, maxRest)) x
          else maxRest
      }

    def maxList[T](elements: List[T])
          (implicit ordering: Ordering[T]): T =
    
      elements match {
        case List() => 
          throw new IllegalArgumentException("empty list!")
        case List(x) => x
        case x :: rest =>
          val maxRest = maxList(rest)     // (ordering) is implicit
          if (ordering.gt(x, maxRest)) x  // this ordering is
          else maxRest                    // still explicit
      }
  }

  object ObjMaxList1 {
    def maxListImpParm[T](elements: List[T])
          (implicit ordering: Ordering[T]): T =
    
      elements match {
        case List() => 
          throw new IllegalArgumentException("empty list!")
        case List(x) => x
        case x :: rest =>
          val maxRest = maxListImpParm(rest)(ordering)
          if (ordering.gt(x, maxRest)) x
          else maxRest
      }
  }

  object ObjMaxList2a {
    def maxList[T](elements: List[T])
          (implicit ordering: Ordering[T]): T =
    
      elements match {
        case List() => 
          throw new IllegalArgumentException("empty list!")
        case List(x) => x
        case x :: rest =>
          val maxRest = maxList(rest)     // (ordering) is implicit
          if (ordering.gt(x, maxRest)) x  // this ordering is
          else maxRest                    // still explicit
      }
  }

  object ObjMaxList2 {
    def maxList[T](elements: List[T])
          (implicit ordering: Ordering[T]): T =
    
      elements match {
        case List() => 
          throw new IllegalArgumentException("empty list!")
        case List(x) => x
        case x :: rest =>
          val maxRest = maxList(rest)
          if (implicitly[Ordering[T]].gt(x, maxRest)) x
          else maxRest
      }
  }

  object ObjMaxList2b {
    def maxList[T](elements: List[T])
          (implicit comparator: Ordering[T]): T = // same body...
      elements match {
        case List() => 
          throw new IllegalArgumentException("empty list!")
        case List(x) => x
        case x :: rest =>
          val maxRest = maxList(rest)
          if (implicitly[Ordering[T]].gt(x, maxRest)) x
          else maxRest
      }
  }

  object ObjMaxList2c {
    def maxList[T](elements: List[T])
          (implicit iceCream: Ordering[T]): T = // same body...
      elements match {
        case List() => 
          throw new IllegalArgumentException("empty list!")
        case List(x) => x
        case x :: rest =>
          val maxRest = maxList(rest)
          if (implicitly[Ordering[T]].gt(x, maxRest)) x
          else maxRest
      }
  }

  object ObjMaxListContextBound {
    def maxList[T : Ordering](elements: List[T]): T =
      elements match {
        case List() => 
          throw new IllegalArgumentException("empty list!")
        case List(x) => x
        case x :: rest =>
          val maxRest = maxList(rest)
          if (implicitly[Ordering[T]].gt(x, maxRest)) x
          else maxRest
      }
  }
}
