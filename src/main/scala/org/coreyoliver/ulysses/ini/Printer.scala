package org.coreyoliver.ulysses.ini

import scalaz._
import Scalaz._

object Printer {

  private def mkKeyValue(kv: Ast.KeyValue): String = {
    val Ast.KeyValue(k, v) = kv
    k + "=" + v
  }

  private def mkSection(s: Ast.Section): String = {
    val Ast.Section(sn, kvs) = s;
    ("[" + sn + "]\n" + (kvs.map(kv => mkKeyValue(kv)) mkString "\n"))
  }

  def pretty(ast: Ast.Ini): String = {
    ast.sections.map(s => mkSection(s)) mkString "\n"
  }
}