package org.coreyoliver.ulysses.ini

import org.scalatest.matchers.MustMatchers
import org.scalatest.PropSpec
import org.scalatest.prop.PropertyChecks
import org.scalacheck.Prop._
import scalaz._
import Scalaz._

class IniSpec extends PropSpec with PropertyChecks with MustMatchers {

  import Ini._
  import AstGen._

  property("The Ini singleton should be able to load an ini file") {
    forAll { (ast: Ast.Ini) =>
      lazy val minKeyValueLength = ast.sections.map { case Ast.Section(sn, kvs) => kvs.length } min;
      whenever(ast.sections.length > 0 && minKeyValueLength > 0) {
        val str = Printer.print(ast)
        val newAst = loadString(str)
        newAst.isSuccess must be(true)
      }
    }
  }

//  property("The Ini singleton should not be able to load an ini file with no sections") {
//    forAll { (ast: Ast.Ini) =>
//      whenever(ast.sections.length == 0) {
//        val str = Printer.pretty(ast)
//        val newAst = loadString(str)
//        newAst.isFailure must be(true)
//      }
//    }
//  }
//
//  property("The Ini singleton should not be able to load an ini file if there exists a section with zero key values") {
//    forAll { (ast: Ast.Ini) =>
//      lazy val minKeyValueLength = ast.sections.map { case Ast.Section(sn, kvs) => kvs.length } min;
//      whenever(ast.sections.length > 0 && minKeyValueLength == 0) {
//        val str = Printer.pretty(ast)
//        val newAst = loadString(str)
//        newAst.isFailure must be(true)
//      }
//    }
//  }
}