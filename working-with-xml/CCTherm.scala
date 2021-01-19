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

abstract class CCTherm {
  val description: String
  val yearMade: Int
  val dateObtained: String
  val bookPrice: Int      // in US cents
  val purchasePrice: Int  // in US cents
  val condition: Int      // 1 to 10

  override def toString = description
  def toXML =
    <cctherm>
      <description>{description}</description>
      <yearMade>{yearMade}</yearMade>
      <dateObtained>{dateObtained}</dateObtained>
      <bookPrice>{bookPrice}</bookPrice>
      <purchasePrice>{purchasePrice}</purchasePrice>
      <condition>{condition}</condition>
    </cctherm>
}


object CCTherm {
  /** Convert back a thermometer instance created
   *  by CCTherm.
   */
  def fromXML(node: scala.xml.Node): CCTherm =
    new CCTherm {
      val description   = (node \ "description").text
      val yearMade      = (node \ "yearMade").text.toInt
      val dateObtained  = (node \ "dateObtained").text
      val bookPrice     = (node \ "bookPrice").text.toInt
      val purchasePrice = (node \ "purchasePrice").text.toInt
      val condition     = (node \ "condition").text.toInt
    }

  /** An example catalog, in XML format */
  val catalog =
      <catalog>
        <cctherm>
          <description>hot dog #5</description>
          <yearMade>1952</yearMade>
          <dateObtained>March 14, 2006</dateObtained>
          <bookPrice>2199</bookPrice>
          <purchasePrice>500</purchasePrice>
          <condition>9</condition>
        </cctherm>
        <cctherm>
          <description>Sprite Boy</description>
          <yearMade>1964</yearMade>
          <dateObtained>April 28, 2003</dateObtained>
          <bookPrice>1695</bookPrice>
          <purchasePrice>595</purchasePrice>
          <condition>5</condition>
        </cctherm>
      </catalog>
}

