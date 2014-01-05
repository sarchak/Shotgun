package com.shrikar.shotgun
import scala.collection.mutable.ListBuffer
import scala.util.Random
import scala.math._

/**
 * Created with IntelliJ IDEA.
 * User: shrikar
 * Date: 1/4/14
 * Time: 4:49 PM
 * To change this template use File | Settings | File Templates.
 */
  class RandomData(columns: Array[String], values : Array[String]) {
  val data = new ListBuffer[String]
  val random = Random
  var cnt = 0
  def getSubscriberData(): String = {
    val predicates = for( i <- (0 until max(1,random.nextInt(20)))) yield(columns(random.nextInt(99))+"=="+values(random.nextInt(99)))
    data += predicates.mkString("&&")
    data += predicates.mkString("&&")
    data += predicates.mkString("&&")
    cnt += 1
    predicates.mkString("&&")
  }

  def queryEvent(): List[String] = {
    data(random.nextInt(cnt)).split("&&").toList
  }

}
