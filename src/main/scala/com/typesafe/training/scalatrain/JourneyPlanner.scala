/*
 * Copyright © 2012 Typesafe, Inc. All rights reserved.
 */

package com.typesafe.training.scalatrain

class JourneyPlanner(trains: Set[Train]) {

  val stations: Set[Station] =
    trains flatMap (_.stations)

  val hops: Map[Station, Set[Hop]] = {
    //    val allHops = trains.flatMap(train => train.backToBackStations.map {
    //      case (from, to) => Hop(from, to, train)
    //    })

    val allHops = for {
      train <- trains
      (from, to) <- train.backToBackStations
    } yield Hop(from, to, train)
    allHops.groupBy(_.from)
  }

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

  def connections(from: Station, to: Station, departureTime: Time): Set[Seq[Hop]] = {
    require(from != to, "from must not be the same station as to")

    def connections(soFar: Vector[Hop]): Set[Seq[Hop]] = {
      if (soFar.last.to == to)
        Set(soFar)
      else {
        val soFarStations = soFar.head.from +: soFar.map(_.to)

        val possibleHops = hops.getOrElse(soFarStations.last, Set()) filter { hop => hop.departureTime >= soFar.last.arrivalTime && !(soFarStations contains hop.to) }

        possibleHops flatMap (hop => connections(soFar :+ hop))
      }
    }
    val possibleHops = hops.getOrElse(from, Set()) filter { hop => hop.departureTime >= departureTime }
    possibleHops.flatMap(hop => connections(Vector(hop)))
  }

}
