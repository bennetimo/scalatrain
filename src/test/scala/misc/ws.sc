

import com.typesafe.training.scalatrain.{TestData, Time}

Time.isIncreasing(Vector(Time(3,3), Time(5,5)))

Time.isIncreasing(Seq(Time(3,3), Time(5,5)))

Time.isIncreasing(List(Time(3,3), Time(5,5)))

def mapFold[A,B](elems: Seq[A])(f: (A) => B): Seq[B] = {
  elems.foldLeft(Seq.empty[B])((e1, e2) => e1 :+ f(e2))
}


import TestData._

Planner.connections(Munich, Frankfurt, Time(1,1))











































