package com.shrikar.test

import org.scalatest.FlatSpec
import com.shrikar.shotgun.ShotgunDispatcher

/**
 * Created with IntelliJ IDEA.
 * User: shrikar
 * Date: 1/3/14
 * Time: 7:05 PM
 * To change this template use File | Settings | File Templates.
 */
class ShotgunDispatcherTest extends FlatSpec{
  "Test1" should "add sids" in {
    val sd = new ShotgunDispatcher(4)
    sd.registerSubscriber("sid1","(c1==hello)")
    val ids = sd.findMatchingIds(List("c1==hello"))
    assert(ids.head == "sid1")
  }

  "Test2" should "add multiple sids" in {
    val sd = new ShotgunDispatcher(4)
    sd.registerSubscriber("sid1","(c1==hello)")
    sd.registerSubscriber("sid2","(c1==hello)")
    val ids = sd.findMatchingIds(List("c1==hello"))
    assert(ids.head == "sid1")
    assert(ids.tail.head == "sid2")
  }

  "Test3" should "should return sid2" in {
    val sd = new ShotgunDispatcher(4)
    sd.registerSubscriber("sid1","(c1==hello)")
    sd.registerSubscriber("sid2","(c1==hello)&&(c2==bye)")
    val ids = sd.findMatchingIds(List("c1==hello","c2==bye"))
    assert(ids.head == "sid2")
  }

  "Test4" should "should return sid1" in {
    val sd = new ShotgunDispatcher(4)
    sd.registerSubscriber("sid1","(c1==hello)")
    sd.registerSubscriber("sid2","(c1==hello)&&(c2==bye)")
    val ids = sd.findMatchingIds(List("c1==hello"))
    assert(ids.head == "sid1")
  }

  "Test5" should "should return sid1 when events have brackets" in {
    val sd = new ShotgunDispatcher(4)
    sd.registerSubscriber("sid1","(c1==hello)")
    sd.registerSubscriber("sid2","(c1==hello)&&(c2==bye)")
    val ids = sd.findMatchingIds(List("(c1==hello)"))
    assert(ids.head == "sid1")
  }

  "Test6" should "subscribers no brackets" in {
    val sd = new ShotgunDispatcher(4)
    sd.registerSubscriber("sid1","c1==hello")
    sd.registerSubscriber("sid2","c1==hello&&c2==bye")
    val ids = sd.findMatchingIds(List("(c1==hello)"))
    assert(ids.head == "sid1")
  }
}
