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

class Publication(val title: String)
class Book(title: String) extends Publication(title)

object Library {
val books: Set[Book] =
 Set(
   new Book("Programming in Scala"),
   new Book("Walden")
 )
def printBookList(info: Book => AnyRef) = {
 for (book <- books) println(info(book))
}
}

object Customer extends App {
def getTitle(p: Publication): String = p.title
Library.printBookList(getTitle)
}
