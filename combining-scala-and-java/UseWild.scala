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

import java.util.Collection
import scala.collection.mutable.Set

/** Accessing wildcard types from Scala */
object UseWild {
  import scala.collection.mutable
  import java.util.Collection
  
  abstract class SetAndType {
    type Elem
    val set: mutable.Set[Elem]
  }
  
  def javaSet2ScalaSet[T](jset: Collection[T]): SetAndType = {
    val sset = mutable.Set.empty[T]  // now T can be named!
  
    val iter = jset.iterator
    while (iter.hasNext)
      sset += iter.next()
  
    new SetAndType {
      type Elem = T
      val set = sset
    }
  }

  val setAndType = javaSet2ScalaSet((new Wild).contents)
  val set: Set[setAndType.Elem] =
    setAndType.set
}
