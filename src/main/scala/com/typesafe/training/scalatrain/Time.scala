/*
 * Copyright © 2012 Typesafe, Inc. All rights reserved.
 */

package com.typesafe.training.scalatrain

import scala.util.control.Exception
import scala.util.parsing.json.{ JSONObject }
import misc.{ JSONFormat, JSONSerializable }

object Time extends JSONSerializable[Time] {

  implicit val timeJSONFormat: JSONFormat[Time] = new JSONFormat[Time] {

    def fromJSON(json: JSONObject): Option[Time] =
      for {
        hoursAny <- json.obj get "hours"
        hours <- Exception.allCatch opt hoursAny.toString.toInt
        minutesAny <- json.obj get "minutes"
        minutes <- Exception.allCatch opt minutesAny.toString.toInt
      } yield Time(hours, minutes)

    def toJSON(time: Time): JSONObject = JSONObject(Map("hours" -> time.hours, "minutes" -> time.minutes))

  }

  def fromMinutes(minutes: Int): Time =
    Time(minutes / 60, minutes % 60)

  implicit def fromString(time: String): Time = {
    val regex = """(\d{1,2}):(\d{1,2})""".r
    val regex(hours, mins) = time
    Time(hours.toInt, mins.toInt)
  }

}

case class Time(hours: Int = 0, minutes: Int = 0) extends Ordered[Time] {
  require(hours >= 0 && hours < 24, "hours must be within 0 and 23")
  require(minutes >= 0 && minutes < 60, "minutes must be within 0 and 59")

  type Closeable = { def close(): Unit }
  val c1: Closeable = new java.io.StringWriter

  def test(c: Closeable) = {}

  val asMinutes: Int =
    hours * 60 + minutes

  override lazy val toString: String =
    f"$hours%02d:$minutes%02d"

  def minus(that: Time): Int =
    this.asMinutes - that.asMinutes

  def -(that: Time): Int =
    minus(that)

  override def compare(that: Time): Int =
    this - that
}
