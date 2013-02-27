/*
 * Copyright © 2012 Typesafe, Inc. All rights reserved.
 */

package com.typesafe.training.scalatrain

import scala.collection.immutable.Seq

case class Train(info: TrainInfo, schedule: Seq[(Time, Station)]) {
  require(schedule.size >= 2, "schedule must contain at least two elements")
  require(Time.isIncreasing(schedule.map(_._1)), "schedule must contain increasing times")

  val stations: Seq[Station] =
    schedule map (_._2)
}

object TrainInfo {

  case class InterCityExpress(number: Int, hasWifi: Boolean = false) extends TrainInfo

  case class RegionalExpress(number: Int) extends TrainInfo

  case class BavarianRegional(number: Int) extends TrainInfo
}

sealed abstract class TrainInfo {

  def number: Int
}

case class Station(name: String)