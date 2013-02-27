package com.typesafe.training.scalatrain

case class Hop(from: Station, to: Station, train: Train) {
  require(from != to, "from and to stations can't be equal")
  require(train.backToBackStations.contains(from -> to))

  val arrivalTime: Time = train.departureTimes(to)
  val departureTime: Time = train.departureTimes(from)

}
