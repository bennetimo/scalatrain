/*
 * Copyright © 2012 Typesafe, Inc. All rights reserved.
 */

package com.typesafe.training.scalatrain

import TestData._
import java.lang.{ IllegalArgumentException => IAE }
import org.scalatest.WordSpec
import org.scalatest.matchers.MustMatchers

class TrainSpec extends WordSpec with MustMatchers {

  "Creating a Train" should {
    "throw an IllegalArgumentException for a schedule with 0 or 1 elements" in {
      evaluating(Train(TrainInfo.InterCityExpress(724), Vector())) must produce[IAE]
      evaluating(Train(TrainInfo.InterCityExpress(724), Vector(Ice724MunichTime -> Munich))) must produce[IAE]
    }
    "throw an IllegalArgumentException for times not increasing" in {
      evaluating(Train(TrainInfo.InterCityExpress(724), Vector(Time(8, 50) -> Munich, Time(7, 49) -> Munich))) must produce[IAE]
    }
  }

  "stations" should {
    "be initialized correctly" in {
      Ice724.stations must be === Vector(Munich, Nuremberg, Frankfurt, Cologne)
    }
  }
}
