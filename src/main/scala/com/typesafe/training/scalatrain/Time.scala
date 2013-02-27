/*
 * Copyright © 2012 Typesafe, Inc. All rights reserved.
 */

package com.typesafe.training.scalatrain

import scala.util.control.Exception
import scala.util.parsing.json.{ JSON, JSONObject }
import annotation.tailrec

object Time {

  def fromMinutes(minutes: Int): Time =
    Time(minutes / 60, minutes % 60)

  def fromJSON(json: JSONObject): Option[Time] =
    for {
      hoursAny <- json.obj get "hours"
      hours <- Exception.allCatch opt hoursAny.toString.toInt
      minutesAny <- json.obj get "minutes"
      minutes <- Exception.allCatch opt minutesAny.toString.toInt
    } yield Time(hours, minutes)

  @tailrec
  def isIncreasing(times: Seq[Time]): Boolean = times match {
    //case t1 :: t2 :: rest => (t1 < t2) && isIncreasing(t2 :: rest)
    /**
     * The first case example above will pass all the unit tests, as they are written to pass in Seqs and Lists. However,
     * it uses cons (::) to match, which is only defined on list, but not for example on Vector. That means if we pass in
     * a Vector(Time()) then it won't match the first case as the type can't match, so it will hit the catch all. We could
     * use case Seq() instead of case _ for the empty case to force a runtime match error if we were using the cons approach
     */
    case t1 +: t2 +: rest => (t1 < t2) && isIncreasing(t2 +: rest)
    case _ => true
  }

  def isIncreasingSliding(times: Seq[Time]): Boolean = times.sliding(2).forall {
    case Seq(first, second) => first < second
    case Seq(_) => true
  }
}

case class Time(hours: Int = 0, minutes: Int = 0) extends Ordered[Time] {
  require(hours >= 0 && hours < 24, "hours must be within 0 and 23")
  require(minutes >= 0 && minutes < 60, "minutes must be within 0 and 59")

  val asMinutes: Int =
    hours * 60 + minutes

  override lazy val toString: String =
    f"$hours%02d:$minutes%02d"

  def minus(that: Time): Int =
    this.asMinutes - that.asMinutes

  def -(that: Time): Int =
    minus(that)

  // TODO This "pollutes" the API; in the Advanced Scala course we learn a better solution
  def toJSON: JSONObject =
    JSONObject(Map("hours" -> hours, "minutes" -> minutes))

  override def compare(that: Time): Int =
    this - that
}
