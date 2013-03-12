package org.coreyoliver.ulysses.ini

object Ast {
  type Key = String
  type Value = String
  type SectionName = String

  case class KeyValue(key: Key, value: Value)
  case class Section(sectionName: SectionName, keyValues: List[KeyValue])
  case class Ini(sections: List[Section])
}