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

object Ex4 {
  
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
  trait FoodCategories {
    case class FoodCategory(name: String, foods: List[Food])
    def allCategories: List[FoodCategory]
  }
  abstract class Browser {
    val database: Database
  
    def recipesUsing(food: Food) =
      database.allRecipes.filter(recipe =>
        recipe.ingredients.contains(food))
  
    def displayCategory(category: database.FoodCategory) = { 
      println(category)
    }
  }
  abstract class Database extends FoodCategories {
    def allFoods: List[Food]
    def allRecipes: List[Recipe]
    def foodNamed(name: String) =
      allFoods.find(f => f.name == name)
  }
  object SimpleDatabase extends Database {
    def allFoods = List(Apple, Orange, Cream, Sugar)
  
    def allRecipes: List[Recipe] = List(FruitSalad)
  
    private var categories = List(
      FoodCategory("fruits", List(Apple, Orange)),
      FoodCategory("misc", List(Cream, Sugar)))
  
    def allCategories = categories
  }
  object SimpleBrowser extends Browser {
    val database = SimpleDatabase
  }

  def main(args: Array[String]): Unit = {
    val apple = SimpleDatabase.foodNamed("Apple").get
    val appleRecipes = SimpleBrowser.recipesUsing(apple)
    val allCategories = SimpleDatabase.allCategories

    println("apple [" + apple + "]")
    println("appleRecipes [" + appleRecipes + "]")
    println("allCategories [" + allCategories + "]")
  }
}
