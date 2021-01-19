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
    trait RationalTrait { 
      val numerArg: Int 
      val denomArg: Int 
    } 

    new RationalTrait {
      val numerArg = 1
      val denomArg = 2
    }

    val expr1 = 1
    val expr2 = 2

    new RationalTrait {
      val numerArg = expr1
      val denomArg = expr2
    }

    object Color extends Enumeration {
      val Red = Value
      val Green = Value
      val Blue = Value
    }

    import Color._

    val color1 = Red

    object Direction extends Enumeration {
      val North, East, South, West = Value
    }

    // A first (faulty) design of the Currency class
    abstract class Currency {
      val amount: Long
      def designation: String 
      override def toString = s"$amount $designation"
    }

    new Currency {
      val amount = 79L
      def designation = "USD"
    }

    abstract class Dollar extends Currency {
      def designation = "USD"
    }
    abstract class Euro extends Currency {
      def designation = "Euro"
    }

    abstract class CurrencyZone {
      type Currency <: AbstractCurrency
      def make(x: Long): Currency
      abstract class AbstractCurrency {
        val amount: Long
        def designation: String 
        def + (that: Currency): Currency = 
          make(this.amount + that.amount)
        def * (x: Double): Currency = 
          make((this.amount * x).toLong)
      }
    }

    object US extends CurrencyZone {
      abstract class Dollar extends AbstractCurrency {
        def designation = "USD"
      }
      type Currency = Dollar
      def make(x: Long) = new Dollar { val amount = x }
    }
  }

  object Obj2 {
    trait RationalTrait { 
      val numerArg: Int 
      val denomArg: Int 
      require(denomArg != 0)
      private val g = gcd(numerArg, denomArg)
      val numer = numerArg / g
      val denom = denomArg / g
      private def gcd(a: Int, b: Int): Int = 
        if (b == 0) a else gcd(b, a % b)
      override def toString = s"$numer/$denom"
    }

    object twoThirds extends {
      val numerArg = 2
      val denomArg = 3
    } with RationalTrait

    class RationalClass(n: Int, d: Int) extends {
      val numerArg = n
      val denomArg = d
    } with RationalTrait {
      def + (that: RationalClass) = new RationalClass(
        numer * that.denom + that.numer * denom,
        denom * that.denom
      )
    }

    object Color extends Enumeration {
      val Red, Green, Blue = Value
    }

    abstract class CurrencyZone {
    
      type Currency <: AbstractCurrency
      def make(x: Long): Currency
    
      abstract class AbstractCurrency {
    
        val amount: Long
        def designation: String 
    
        def + (that: Currency): Currency = 
          make(this.amount + that.amount)
        def * (x: Double): Currency = 
          make((this.amount * x).toLong)
        def - (that: Currency): Currency = 
          make(this.amount - that.amount)
        def / (that: Double) = 
          make((this.amount / that).toLong)
        def / (that: Currency) = 
          this.amount.toDouble / that.amount
    
        def from(other: CurrencyZone#AbstractCurrency): Currency = 
          make(math.round(
            other.amount.toDouble * Converter.exchangeRate
              (other.designation)(this.designation)))
    
        private def decimals(n: Long): Int = 
          if (n == 1) 0 else 1 + decimals(n / 10)
    
        override def toString = 
          ((amount.toDouble / CurrencyUnit.amount.toDouble)
           formatted ("%." + decimals(CurrencyUnit.amount) + "f")
           + " " + designation)
      }
    
      val CurrencyUnit: Currency
    }

    object Converter {
      var exchangeRate = Map(
        "USD" -> Map("USD" -> 1.0   , "EUR" -> 0.7596, 
                     "JPY" -> 1.211 , "CHF" -> 1.223),
        "EUR" -> Map("USD" -> 1.316 , "EUR" -> 1.0   , 
                     "JPY" -> 1.594 , "CHF" -> 1.623),
        "JPY" -> Map("USD" -> 0.8257, "EUR" -> 0.6272, 
                     "JPY" -> 1.0   , "CHF" -> 1.018),
        "CHF" -> Map("USD" -> 0.8108, "EUR" -> 0.6160, 
                     "JPY" -> 0.982 , "CHF" -> 1.0  )
      )
    }
  }

  class Outer {
    class Inner
  }
}
