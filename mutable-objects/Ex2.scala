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


class BankAccount {

  private var bal: Int = 0

  def balance: Int = bal

  def deposit(amount: Int) = {
    require(amount > 0)
    bal += amount
  }

  def withdraw(amount: Int): Boolean = 
    if (amount > bal) false
    else {
      bal -= amount
      true
    }
} 

object Ex2 {
  def main(args: Array[String]): Unit = {
    val account = new BankAccount
    println("account deposit 100 [" + (account deposit 100) + "]")
    println("account withdraw 80 [" + (account withdraw 80) + "]")
    println("account withdraw 80 [" + (account withdraw 80) + "]")
  }
}
