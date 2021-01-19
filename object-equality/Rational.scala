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

  class Rational(n: Int, d: Int) {
  
    require(d != 0)
  
    private val g = gcd(n.abs, d.abs)
    val numer = (if (d < 0) -n else n) / g
    val denom = d.abs / g
  
    private def gcd(a: Int, b: Int): Int =
      if (b == 0) a else gcd(b, a % b)
  
    override def equals(other: Any): Boolean =
      other match {
  
        case that: Rational =>
          (that canEqual this) &&
          numer == that.numer &&
          denom == that.denom
  
        case _ => false
      }
  
    def canEqual(other: Any): Boolean =
      other.isInstanceOf[Rational]
  
    override def hashCode: Int = (numer, denom).##
  
    override def toString =
      if (denom == 1) numer.toString else s"$numer/$denom"
  }
