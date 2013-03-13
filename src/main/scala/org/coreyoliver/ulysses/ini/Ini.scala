package org.coreyoliver.ulysses.ini

import scala.io.Source
import scalaz._
import Scalaz._

object Ini {

  case class Section(name: String, keyValues: Map[String, String]) {

    def getValue(key: String): Option[String] = {
      keyValues.get(key)
    }
  }

  case class Ini(sections: Map[String, Section]) {

    def prettyPrint = Printer.print(this)
      
    def getSection(name: String): Option[Section] = {
      sections.get(name)
    }
  }

  def loadString(string: String): ValidationNEL[String, Ini] = {
    for {
      ast <- Parser.parse(string)
    } yield Generator.mkIni(ast)
  }

  def loadSource(source: Source) = {
    loadString(source.mkString)
  }

  def loadFile(name: String) = {
    loadSource(Source.fromFile(name))
  }
}