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

  "backToBackStations" should {
    "return (a,b),(b,c) for a->b->c" in {
      val train = Train(TrainInfo.InterCityExpress(724), Vector(Time(12, 0) -> Munich,
        Time(12, 30) -> Nuremberg,
        Time(12, 40) -> Frankfurt))
      train.backToBackStations must be === Vector(Munich -> Nuremberg, Nuremberg -> Frankfurt)
    }
    "return (a,b) for a->b" in {
      val train = Train(TrainInfo.InterCityExpress(724), Vector(Time(12, 0) -> Munich,
        Time(12, 30) -> Nuremberg))
      train.backToBackStations must be === Vector(Munich -> Nuremberg)
    }
    "return (a,b),(b,c),(c,d) for a->b->c->d" in {
      val train = Train(TrainInfo.InterCityExpress(724), Vector(Time(12, 0) -> Munich,
        Time(12, 30) -> Nuremberg,
        Time(12, 40) -> Frankfurt,
        Time(12, 50) -> Cologne))
      train.backToBackStations must be === Vector(Munich -> Nuremberg, Nuremberg -> Frankfurt, Frankfurt -> Cologne)
    }
  }
}
