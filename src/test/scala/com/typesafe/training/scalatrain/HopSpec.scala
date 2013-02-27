/*
 * Copyright © 2012 Typesafe, Inc. All rights reserved.
 */

package com.typesafe.training.scalatrain

import java.lang.{ IllegalArgumentException => IAE }
import org.scalatest.WordSpec
import org.scalatest.matchers.MustMatchers
import TestData._

class HopSpec extends WordSpec with MustMatchers {

  "Creating a Hop" should {
    "throw an IllegalArgumentException if both of the stations are equal" in {
      evaluating(Hop(Frankfurt, Frankfurt, Ice724)) must produce[IAE]
    }
    "throw an IllegalArgumentException if the two stations are not back to back" in {
      evaluating(Hop(Munich, Frankfurt, Ice724)) must produce[IAE]
    }
    "set the depatureTime time of the hop" in {
      Hop(Munich, Nuremberg, Ice724).departureTime must be === Ice724MunichTime
    }
    "set the arrival time of the hop" in {
      Hop(Munich, Nuremberg, Ice724).arrivalTime must be === Ice724NurembergTime
    }

  }
}
