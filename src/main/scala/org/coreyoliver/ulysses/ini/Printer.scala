package org.coreyoliver.ulysses.ini

import scalaz._
import Scalaz._

object Printer {
  import Ini._
  
  private def printKeyValue(kv: Ast.KeyValue): String = {
    val Ast.KeyValue(k, v) = kv
    k + "=" + v
  }

  private def printSection(s: Ast.Section): String = {
    val Ast.Section(sn, kvs) = s;
    ("[" + sn + "]\n" + (kvs.map(kv => printKeyValue(kv)) mkString "\n"))
  }

  def print(ast: Ast.Ini): String = {
    ast.sections.map(s => printSection(s)) mkString "\n"
  }
  
  def print(ini: Ini): String = {
    print(Generator.mkAst(ini))
  }
}