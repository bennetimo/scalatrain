

import com.typesafe.training.scalatrain.{TestData, Time}

import misc.Queue

Time.isIncreasing(Vector(Time(3,3), Time(5,5)))

Time.isIncreasing(Seq(Time(3,3), Time(5,5)))

Time.isIncreasing(List(Time(3,3), Time(5,5)))

def mapFold[A,B](elems: Seq[A])(f: (A) => B): Seq[B] = {
  elems.foldLeft(Seq.empty[B])((e1, e2) => e1 :+ f(e2))
}


import TestData._

abstract class Animal
case class Bird() extends Animal
val qb = Queue(Bird(), Bird(), Bird())
val qa: Queue[Animal] = qb




































