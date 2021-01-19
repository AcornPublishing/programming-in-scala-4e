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


object Ex3 {
  def main(args: Array[String]): Unit = {
    val greetStrings = new Array[String](3)
    
    greetStrings.update(0, "Hello")
    greetStrings.update(1, ", ")
    greetStrings.update(2, "world!\n")
    
    for (i <- 0.to(2))
      print(greetStrings.apply(i))

    val numNames = Array("zero", "one", "two")
    println("numNames.toList [" + numNames.toList + "]")

    val numNames2 = Array.apply("zero", "one", "two")
    println("numNames2.toList [" + numNames2.toList + "]")

    val oneTwoThree = List(1, 2, 3)
    println("oneTwoThree [" + (oneTwoThree) + "]")

    {
      val oneTwo = List(1, 2)
      val threeFour = List(3, 4)
      val oneTwoThreeFour = oneTwo ::: threeFour
      println(oneTwo + " and " + threeFour + " were not mutated.")
      println("Thus, " + oneTwoThreeFour + " is a new list.")
    }

    {
      val twoThree = List(2, 3)
      val oneTwoThree = 1 :: twoThree
      println(oneTwoThree)
    }

    {
      val oneTwoThree = 1 :: 2 :: 3 :: Nil
      println(oneTwoThree)
    }

    val pair = (99, "Luftballons")
    println(pair._1)
    println(pair._2)

    var jetSet = Set("Boeing", "Airbus")
    jetSet += "Lear"
    println(jetSet.contains("Cessna"))

    jetSet = jetSet + "Lear"
    println("jetSet [" + (jetSet) + "]")

    import scala.collection.mutable
    
    val movieSet = mutable.Set("Hitch", "Poltergeist")
    movieSet += "Shrek"
    println(movieSet) 

    import scala.collection.immutable.HashSet
    
    val hashSet = HashSet("Tomatoes", "Chilies")
    println(hashSet + "Coriander")

    import scala.collection.mutable
    
    val treasureMap = mutable.Map[Int, String]()
    treasureMap += (1 -> "Go to island.")
    treasureMap += (2 -> "Find big X on ground.")
    treasureMap += (3 -> "Dig.")
    println(treasureMap(2))

    val romanNumeral = Map(
      1 -> "I", 2 -> "II", 3 -> "III", 4 -> "IV", 5 -> "V"
    )
    println(romanNumeral(4))

    def printArgs(args: Array[String]): Unit = {
      var i = 0
      while (i < args.length) {
        println(args(i))
        i += 1
      }
    }
    printArgs(Array("zero", "one", "two"))

    {
      def printArgs(args: Array[String]): Unit = {
        for (arg <- args)
          println(arg)
      }
      printArgs(Array("three", "four", "five"))
    }

    {
      def printArgs(args: Array[String]): Unit = {
        args.foreach(println)
      }
      printArgs(Array("six", "seven", "eight"))
    }

    def formatArgs(args: Array[String]) = args.mkString("\n")
    val args = Array("nine", "ten")

    println(formatArgs(args))

    val res = formatArgs(Array("zero", "one", "two"))
    assert(res == "zero\none\ntwo")
    println("whew")
  }
}
