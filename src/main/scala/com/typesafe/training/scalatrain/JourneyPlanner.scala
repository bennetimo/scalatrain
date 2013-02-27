/*
 * Copyright © 2012 Typesafe, Inc. All rights reserved.
 */

package com.typesafe.training.scalatrain

class JourneyPlanner(trains: Set[Train]) {

  val stations: Set[Station] =
    trains flatMap (_.stations)

  def trainsAt(station: Station): Set[Train] =
    trains filter (_.stations contains station)

  def stopsAt(station: Station): Set[(Time, Train)] =
    for {
      train <- trains
      (time, s) <- train.schedule if s == station
    } yield (time, train)

  def isShortTrip(from: Station, to: Station): Boolean =
    trains exists (
      _.stations dropWhile (_ != from) match {
        case `from` +: `to` +: _ => true
        case `from` +: _ +: `to` +: _ => true
        case _ => false
      }
    )

  //def connections()
}
