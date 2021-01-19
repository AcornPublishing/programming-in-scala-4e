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

object InventoryAnalysis {
  def findMostExpensive(inventory: Inventory): InventoryItem = {
    val items = toScala(inventory.getItems())

    def totalCost(item: InventoryItem): Int =
      item.getCount() * item.getPriceInCents()

    def higherCosting(item1: InventoryItem, item2: InventoryItem) =
      if (totalCost(item1) > totalCost(item2))
        item1
      else
        item2

    items.reduceLeft(higherCosting)
  }

  def toScala[T](list: java.util.List[T]): List[T] = {
    import scala.collection.mutable.ListBuffer

    val buf = new ListBuffer[T]
    val iter = list.iterator()
    while (iter.hasNext())
      buf += iter.next()

    buf.toList
  }
}
