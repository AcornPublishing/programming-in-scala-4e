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

package org.stairwaybook.scells
import scala.util.parsing.combinator._

object FormulaParsers extends RegexParsers { 
  def ident: Parser[String] = """[a-zA-Z_]\w*""".r
  def decimal: Parser[String] = """-?\d+(\.\d*)?""".r
  def cell: Parser[Coord] = 
    """[A-Za-z]\d+""".r ^^ { s => 
      val column = s.charAt(0).toUpper - 'A'
      val row = s.substring(1).toInt
      Coord(row, column)
    }
  def range: Parser[Range] = 
    cell~":"~cell ^^ { 
      case c1~":"~c2 => Range(c1, c2) 
    }
def number: Parser[Number] =
  decimal ^^ (d => Number(d.toDouble))
  def application: Parser[Application] = 
    ident~"("~repsep(expr, ",")~")" ^^ { 
      case f~"("~ps~")" => Application(f, ps) 
    }
  def expr: Parser[Formula] =
    range | cell | number | application
  def textual: Parser[Textual] = 
    """[^=].*""".r ^^ Textual
  def formula: Parser[Formula] = 
    number | textual | "="~>expr
  def parse(input: String): Formula =
    parseAll(formula, input) match {
      case Success(e, _) => e
      case f: NoSuccess => Textual("[" + f.msg + "]")
    }
} //end FormulaParsers
