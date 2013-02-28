/*
 * Copyright © 2012 Typesafe, Inc. All rights reserved.
 */

package com.typesafe.training.scalatrain

import java.lang.{ IllegalArgumentException => IAE }
import org.scalatest.WordSpec
import org.scalatest.matchers.MustMatchers
import scala.util.parsing.json.JSONObject

class TimeSpec extends WordSpec with MustMatchers {

  "Calling fromJSON" should {
    "return None for an invalid JSONOject" in {
      Time fromJSON JSONObject(Map()) must be(None)
    }
    "return Some wrapping a properly initialized Time for a valid JSONOject" in {
      (Time fromJSON JSONObject(Map("hours" -> 9, "minutes" -> 30))) must be === Some(Time(9, 30))
    }
  }

  "Calling fromJSON after toJSON" should {
    "return Some wrapping the original Time" in {
      val time = Time(9, 30)
      (Time fromJSON (time.toJSON)) must be === Some(time)
    }
  }

  "Creating a Time" should {
    "throw an IllegalArgumentException for hours not within 0 and 23" in {
      evaluating(Time(-1)) must produce[IAE]
      evaluating(Time(24)) must produce[IAE]
    }
    "throw an IllegalArgumentException for minutes not within 0 and 59" in {
      evaluating(Time(minutes = -1)) must produce[IAE]
      evaluating(Time(minutes = 60)) must produce[IAE]
    }
  }

  "The default arguments for hours and minutes" should {
    "be equal to 0" in {
      val time = Time()
      time.hours must be === 0
      time.minutes must be === 0
    }
  }

  "asMinutes" should {
    "be initialized correctly" in {
      Time(1, 40).asMinutes must be === 100
    }
  }

  "Calling minus or -" should {
    "return the correct difference in minutes" in {
      (Time(1, 40) minus Time(1, 10)) must be === 30
      Time(1, 40) - Time(1, 10) must be === 30
    }
  }

  "Calling toString" should {
    "return a properly formatted string representation" in {
      Time(9, 30).toString must be === "09:30"
    }
  }

  "Calling Ordered operators" should {
    "work as expected" in {
      Time() < Time(1) must be(true)
      Time() >= Time(1) must be(false)
    }
  }

}
