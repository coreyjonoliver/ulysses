package org.coreyoliver.ulysses.ini

import org.scalacheck._
import Gen._

object AstGen {
  def genIni(): Gen[Ast.Ini] = for {
    sections <- genSectionList
  } yield Ast.Ini(sections)

  def genSectionList = Gen.containerOfN[List, Ast.Section](sectionsListSize, genSection)
  def genSection: Gen[Ast.Section] = for {
    name <- identifier
    keyValues <- genKeyValueList
  } yield Ast.Section(name, keyValues)

  def genKeyValueList = Gen.containerOfN[List, Ast.KeyValue](keyValuesListSize, genKeyValue)
  def genKeyValue = for {
    key <- identifier
    value <- identifier
  } yield Ast.KeyValue(key, value)

  def sectionsListSize = choose(1, 5).sample.get
  def keyValuesListSize = choose(1, 5).sample.get
  
  implicit val arbAst: Arbitrary[Ast.Ini] = Arbitrary(genIni)
}