/**
 * Created with IntelliJ IDEA.
 * User: shrikar
 * Date: 1/2/14
 * Time: 5:33 PM                          R
 * To change this template use File | Settings | File Templates.
 */
package com.shrikar.shotgun

import scala.collection.mutable
import java.util.Date
object Shotgun {
  def main(args : Array[String]){
    val sd = new ShotgunDispatcher(2)
    val cols = (for(i <- (1 until 100)) yield("C"+i)).toArray
    val possibles = (for(i <- (1 until 100)) yield("hello"+i)).toArray
    val rd = new RandomData(cols,possibles)
    var id = 1
    for(i <- (1 until 1000)){
      val d = rd.getSubscriberData()
      sd.registerSubscriber("sid"+id,d)
      sd.registerSubscriber("sid"+id+1,d)
      id += 2
    }

    var cnt = 0
    while(true){
      val ids = sd.findMatchingIds(rd.queryEvent())
      cnt+= 1
      if(cnt %500000 == 0){
        println(cnt + " " + new Date())
      }
    }


  }

}
