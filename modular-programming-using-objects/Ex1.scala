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

object Ex1 {
  
  abstract class Food(val name: String) {
    override def toString = name
  }
  
  class Recipe(
    val name: String,
    val ingredients: List[Food],
    val instructions: String
  ) {
    override def toString = name
  }

  
  object Apple extends Food("Apple")
  object Orange extends Food("Orange")
  object Cream extends Food("Cream")
  object Sugar extends Food("Sugar")
  
  object FruitSalad extends Recipe(
    "fruit salad",
    List(Apple, Orange, Cream, Sugar),
    "Stir it all together."
  )
  
  object SimpleDatabase {
    def allFoods = List(Apple, Orange, Cream, Sugar)
  
    def foodNamed(name: String): Option[Food] =
      allFoods.find(_.name == name)
  
    def allRecipes: List[Recipe] = List(FruitSalad)
  }
  
  object SimpleBrowser {
    def recipesUsing(food: Food) =
      SimpleDatabase.allRecipes.filter(recipe =>
        recipe.ingredients.contains(food))
  }

  def main(args: Array[String]): Unit = {
    val apple = SimpleDatabase.foodNamed("Apple").get
    val appleRecipes = SimpleBrowser.recipesUsing(apple)

    println("apple [" + apple + "]")
    println("appleRecipes [" + appleRecipes + "]")
  }
}
