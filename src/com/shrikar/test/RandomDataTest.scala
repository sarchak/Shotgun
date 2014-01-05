package com.shrikar.test

import org.scalatest.FlatSpec
import com.shrikar.shotgun.RandomData
/**
 * Created with IntelliJ IDEA.
 * User: shrikar
 * Date: 1/4/14
 * Time: 5:47 PM
 * To change this template use File | Settings | File Templates.
 */
class RandomDataTest extends FlatSpec{
  val cols = (for(i <- (1 until 100)) yield("C"+i)).toArray
  val possibles = (for(i <- (1 until 100)) yield("hello"+i)).toArray

  "Test1" should "generate some data" in {
    val rd = new RandomData(cols,possibles)
    val tdata = rd.getSubscriberData()
    println(tdata)
  }


  "Test2" should "generate some query data" in {
    val rd = new RandomData(cols,possibles)
    val t = rd.getSubscriberData()
    val tdata = rd.queryEvent()
    println(tdata)
  }
}
