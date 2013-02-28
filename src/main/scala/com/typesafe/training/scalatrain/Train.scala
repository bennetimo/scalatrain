/*
 * Copyright © 2012 Typesafe, Inc. All rights reserved.
 */

package com.typesafe.training.scalatrain

import scala.collection.immutable.Seq

case class Train(info: TrainInfo, schedule: Seq[(Time, Station)]) {
  require(schedule.size >= 2, "schedule must contain at least two elements")
  require(isIncreasing(schedule.map(_._1)), "schedule must contain increasing times")

  val stations: Seq[Station] =
    schedule map (_._2)

  val backToBackStations: Seq[(Station, Station)] = {
    val stations = schedule.map(_._2)
    stations.zip(stations.tail)
  }

  /**
   * In both of the commented out cases we will traverse the sequence twice, once to do the map, and again when
   * we call toMap. To be more efficient we can use the uncommented case, to provide the breakout and do it in
   * one pass. See http://stackoverflow.com/questions/1715681/scala-2-8-breakout
   */
  //val departureTimes: Map[Station, Time] = schedule.map { case (time, station) => station -> time }.toMap
  //val departureTimes: Map[Station, Time] = schedule.map(_.swap).toMap
  val departureTimes: Map[Station, Time] = schedule.map { case (time, station) => station -> time }(scala.collection.breakOut)
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
