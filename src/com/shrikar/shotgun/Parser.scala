/**
 * Created with IntelliJ IDEA.
 * User: shrikar
 * Date: 1/2/14
 * Time: 5:26 PM
 * To change this template use File | Settings | File Templates.
 */
package com.shrikar.shotgun

class Parser {

  def parse(line:String): Array[String] =  {
    val tpredicates = line.replaceAll(" ","").replace("(","").replace(")","").split("&&")
    val predicates = for (predicate <- tpredicates ; if predicate.contains("==")) yield predicate
    predicates
  }
}
