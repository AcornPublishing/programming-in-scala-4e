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

object AbstractTime {
  trait AbstractTime1 {
    var hour: Int
    var minute: Int
  }


  trait AbstractTime2 {
    def hour: Int              // getter for `hour'
    def hour_=(x: Int): Unit   // setter for `hour'
    def minute: Int            // getter for `minute'
    def minute_=(x: Int): Unit // setter for `minute'
  }


  // these traits combine with no conflict
  class AbstractTime extends AbstractTime1 with AbstractTime2 {
    var hour: Int = 10
    var minute: Int = 10
  }
}
