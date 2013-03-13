package org.coreyoliver.ulysses.ini

object Generator {
  import Ini._
  
  private def mkIniKeyValues(kvs: List[Ast.KeyValue]): Map[String, String] = {
    kvs.map { case Ast.KeyValue(k, v) => (k, v) } toMap
  }

  private def mkIniSections(ss: List[Ast.Section]): Map[String, Section] = {
    ss.map { case Ast.Section(sn, kvs) => (sn, Section(sn, mkIniKeyValues(kvs))) } toMap
  }

  def mkIni(ini: Ast.Ini): Ini = {
    Ini(mkIniSections(ini.sections))
  }
  
  private def mkAstKeyValues(kvs: Map[String, String]): List[Ast.KeyValue] = {
    kvs.map { case (k, v) => Ast.KeyValue(k, v) } toList
  }
  
  private def mkAstSections(ss: Map[String, Section]): List[Ast.Section] = {
    ss.map { case (sn, s) => Ast.Section(sn, mkAstKeyValues(s.keyValues)) } toList
  }
  
  def mkAst(ini: Ini): Ast.Ini = {
    Ast.Ini(mkAstSections(ini.sections))
  }
}