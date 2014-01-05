package com.shrikar.test

import com.shrikar.shotgun.Parser
import org.scalatest.FlatSpec

/**
 * Created with IntelliJ IDEA.
 * User: shrikar
 * Date: 1/3/14
 * Time: 2:25 PM
 * To change this template use File | Settings | File Templates.
 */
class ParserTest extends FlatSpec {

  "Test1" should "parse properly" in {
    val p = new Parser()
    val results = p.parse("(c1==test)&&(c2==bye)")
    assert(results(0) == "c1==test")
    assert(results(1) == "c2==bye")
  }

  "Test2" should "properly parse spaces" in {
    val p = new Parser()
    val results = p.parse("(  c1==  test)  &&  (c2   ==bye )")
    assert(results(0) == "c1==test")
    assert(results(1) == "c2==bye")
  }


  "Test3" should "properly parse single entry" in {
    val p = new Parser()
    val results = p.parse("(c1==test)")
    assert(results(0) == "c1==test")
  }

  "Test4" should "validate failures" in {
    val p = new Parser()
    val results = p.parse("(c1!=test)")
    assert(results.size == 0)
  }
}
