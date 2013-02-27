/*
 * Copyright © 2012 Typesafe, Inc. All rights reserved.
 */

package com.typesafe.training.scalatrain

object TestData {

  val Munich = Station("Munich")

  val Nuremberg = Station("Nuremberg")

  val Frankfurt = Station("Frankfurt")

  val Cologne = Station("Cologne")

  val Essen = Station("Essen")

  val Ice724MunichTime = Time(8, 50)

  val Ice724NurembergTime = Time(10)

  val Ice724FrankfurtTime = Time(12, 10)

  val Ice724CologneTime = Time(13, 39)

  val Ice726MunichTime = Time(7, 50)

  val Ice726NurembergTime = Time(9)

  val Ice726FrankfurtTime = Time(11, 10)

  val Ice726CologneTime = Time(13, 2)

  val Ice724 = Train(
    TrainInfo.InterCityExpress(724),
    Vector(
      Ice724MunichTime -> Munich,
      Ice724NurembergTime -> Nuremberg,
      Ice724FrankfurtTime -> Frankfurt,
      Ice724CologneTime -> Cologne
    )
  )

  val Ice726 = Train(
    TrainInfo.InterCityExpress(726),
    Vector(
      Ice726MunichTime -> Munich,
      Ice726NurembergTime -> Nuremberg,
      Ice726FrankfurtTime -> Frankfurt,
      Ice726CologneTime -> Essen
    )
  )

  val Planner = new JourneyPlanner(Set(Ice724, Ice726))
}
