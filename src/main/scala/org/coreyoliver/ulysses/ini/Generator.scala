package org.coreyoliver.ulysses.ini

object Generator {
  import Ini._
  
  private def mkKeyValues(kvs: List[Ast.KeyValue]): Map[String, String] = {
    kvs.map { case Ast.KeyValue(k, v) => (k, v) } toMap
  }

  private def mkSections(ss: List[Ast.Section]): Map[String, Section] = {
    ss.map { case Ast.Section(sn, kvs) => (sn, Section(sn, mkKeyValues(kvs))) } toMap
  }

  def toIni(ini: Ast.Ini): Ini = {
    Ini(mkSections(ini.sections))
  }
  
  private def mkKeyValues(kvs: Map[String, String]): List[Ast.KeyValue] = {
    kvs.map { case (k, v) => Ast.KeyValue(k, v) } toList
  }
  
  private def mkSections(ss: Map[String, Section]): List[Ast.Section] = {
    ss.map { case (sn, s) => Ast.Section(sn, mkKeyValues(s.keyValues)) } toList
  }
  
  def toAst(ini: Ini): Ast.Ini = {
    Ast.Ini(mkSections(ini.sections))
  }
}