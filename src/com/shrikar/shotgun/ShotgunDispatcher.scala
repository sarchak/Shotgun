package com.shrikar.shotgun

/**
 * Created with IntelliJ IDEA.
 * User: shrikar
 * Date: 1/2/14
 * Time: 6:00 PM
 * To change this template use File | Settings | File Templates.
 */
import scala.collection.mutable._
import scala.math._

class ShotgunDispatcher(n : Int) {

  val lookup = new HashMap[String,HashSet[String]]
  val idtable = new HashMap[String,BitSet]

  val p = new Parser

  def getBitSet(predicates : List[String]) : BitSet = {

//    val elems = predicates.map(x => x.replaceAll(")","").replaceAll("(","").split("==").head.tail.toInt)
    val elems = predicates.map(x => x.replace(")","").replace("(","").split("==").head.tail.toInt)
    val b = BitSet(elems.head)
    b ++= elems.tail
    b
  }


  def registerSubscriber(sid:String, predicate:String) = {
        val members = p.parse(predicate)
        idtable.put(sid,getBitSet(members.toList))

        for(member <- members){
            lookup.get(member) match {
              case Some(x) => lookup.put(member,x+sid)
              case _ => lookup.put(member, HashSet(sid))
            }
        }
  }
  def printlookup() = {
    for(ent <- lookup.keySet) {
      println(ent + " -> " + lookup.get(ent))
    }
  }

  def findMatchingIds(evs : List[String]): Set[String] = {
    val events = evs.map(x => x.replace(")","").replace("(",""))
    val curr = getBitSet(events)

    /* Get the possible candidate subscriber id */

    val tmp = events.filter(x => lookup.get(x) != None).toSet
    val tcor = tmp.map(x => lookup.get(x) match {
      case Some(a) => a
    })

    /* Intersect all the sets */

    var res:Set[String] = tcor.head
    for(t <- tcor.tail){
      res = res.intersect(t)
    }

    /* Filter unwanted subscriber ids */

    val fin = res.filter(x => idtable.get(x) match{
      case Some(elem)  => (curr&elem).size == max(curr.size,elem.size)
      case _ => false
    })

    fin
  }
}
