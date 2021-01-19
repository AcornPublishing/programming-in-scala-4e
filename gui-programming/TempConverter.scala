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

import swing._
import event._

object TempConverter extends SimpleSwingApplication {
def top = new MainFrame {
  title = "Celsius/Fahrenheit Converter"
  object celsius extends TextField { columns = 5 }
  object fahrenheit extends TextField { columns = 5 }
  contents = new FlowPanel {
    contents += celsius
    contents += new Label(" Celsius  =  ")
    contents += fahrenheit
    contents += new Label(" Fahrenheit")
    border = Swing.EmptyBorder(15, 10, 10, 10)
  }
  listenTo(celsius, fahrenheit)
  reactions += {
    case EditDone(`fahrenheit`) =>
      val f = fahrenheit.text.toInt
      val c = (f - 32) * 5 / 9
      celsius.text = c.toString
    case EditDone(`celsius`) =>
      val c = celsius.text.toInt
      val f = c * 9 / 5 + 32
      fahrenheit.text = f.toString
  }     
}
}
