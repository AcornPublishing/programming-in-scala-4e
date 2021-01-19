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

package org.stairwaybook.scells
import swing._

class Model(val height: Int, val width: Int) 
extends Evaluator with Arithmetic { 
  case class Cell(row: Int, column: Int) extends Publisher {
    override def toString = formula match {
      case Textual(s) => s
      case _ => value.toString
    }
    private var v: Double = 0
    def value: Double = v
    def value_=(w: Double) = {
      if (!(v == w || v.isNaN && w.isNaN)) {
        v = w
        publish(ValueChanged(this))
      }
    }
    private var f: Formula = Empty
    def formula: Formula = f
    def formula_=(f: Formula) = {
      for (c <- references(formula)) deafTo(c)
      this.f = f
      for (c <- references(formula)) listenTo(c)
      value = evaluate(f)
    }
    reactions += {
      case ValueChanged(_) => value = evaluate(formula)
    }
  } // end class Cell
  case class ValueChanged(cell: Cell) extends event.Event
  val cells = Array.ofDim[Cell](height, width) 

  for (i <- 0 until height; j <- 0 until width)
    cells(i)(j) = new Cell(i, j)
} // end class Model
