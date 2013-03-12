package org.coreyoliver.ulysses.ini

import scala.util.parsing.combinator._
import scalaz._
import Scalaz._

object Parser extends JavaTokenParsers {
  
  import Ast._
  
  override val whiteSpace = """[ \t]+""".r

  lazy val iniFile = (eol *) ~> (section +) <~ (eol *) ^^ {
    case ss => Ini(ss)
  }

  lazy val section = ("[" ~> sectionName <~ "]") ~ (eol.+ ~> keyValue.+) ^^ {
    case (n ~ kvs) => Section(n, kvs)
  }

  lazy val keyValue = key ~ ("=" ~> value <~ eol.*) ^^ {
    case (k ~ v) => KeyValue(k, v)
  }
  
  lazy val eol = """(\r?\n)""".r
  lazy val comment = """;.*""".r
  
  lazy val sectionName = ident
  lazy val key = ident
  lazy val value = ident
  
  def parse(in: String): ValidationNEL[String, Ast.Ini] = {
    parseAll(iniFile, in) match {
      case Success(ini, _) => ini.successNel
      case NoSuccess(msg, _) => msg.failNel
    }
  }
}