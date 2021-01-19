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

package org.stairwaybook.simulation


object Test extends CircuitSimulation with App {

  val InverterDelay = 1
  val AndGateDelay = 3
  val OrGateDelay = 5

  def readOut(w: Wire) = if (w.getSignal) 1 else 0
  def bool(x: Int) = if (x == 0) false else true
  
  def invertTest(): Unit = {
    val ain, cout = new Wire
    inverter(ain, cout)
    def result = readOut(cout)
    
    def test(a: Int): Unit = {
      ain setSignal bool(a)
      run()
      println("!" + a + " = " + result + "\n")
    }
    probe("out  ", cout)
    test(0)
    test(1)
  }

  def andTest(): Unit = {
    val ain, bin, cout = new Wire
    andGate(ain, bin, cout)
    def result = readOut(cout)

    def test(a: Int, b: Int): Unit = {
      ain setSignal bool(a)
      bin setSignal bool(b)
      run()
      println(a.toString + " & " + b + " = " + result + "\n")
    }
    probe("out  ", cout)
    test(0,0)
    test(0,1)
    test(1,0)
    test(1,1)
  }

  def orTest(): Unit = {
    val ain, bin, cout = new Wire
    orGate(ain, bin, cout)
    def result = readOut(cout)

    def test(a: Int, b: Int): Unit = {
      ain setSignal bool(a)
      bin setSignal bool(b)
      run()
      println(a.toString + " | " + b + " = " + result + "\n")
    }
    probe("out  ", cout)
    test(0,0)
    test(0,1)
    test(1,0)
    test(1,1)
  }

  def halfTest(): Unit = {
    val ain, bin, sout, cout = new Wire
    halfAdder(ain, bin, sout, cout)

    def result = readOut(sout) + readOut(cout) * 2

    def test(a: Int, b: Int): Unit = {
      ain setSignal bool(a)
      bin setSignal bool(b)
      run()
      println(a.toString + " + " + b + " = " + result + "\n")
    }

    probe("sum  ", sout)
    probe("carry", cout)
    test(0,0)
    test(0,1)
    test(1,0)
    test(1,1)
  }

  def fullTest(): Unit = {
    val ain, bin, cin, sout, cout = new Wire
    fullAdder(ain, bin, cin, sout, cout)

    def result = readOut(sout) + readOut(cout) * 2

    def test(a: Int, b: Int, c: Int): Unit = {
      ain setSignal bool(a)
      bin setSignal bool(b)
      cin setSignal bool(c)
      run()
      println(a.toString + " + " + b + " + " + c + " = " + result + "\n")
    }

    probe("sum  ", sout)
    probe("carry", cout)
    test(0,0,0)
    test(0,0,1)
    test(0,1,0)
    test(0,1,1)
    test(1,0,0)
    test(1,0,1)
    test(1,1,0)
    test(1,1,1)
  }

  invertTest()
  andTest()
  orTest()
  halfTest()
  fullTest()
}
