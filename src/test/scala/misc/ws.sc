

import com.typesafe.training.scalatrain.{TestData, Time}

import misc.Queue

import com.typesafe.training.scalatrain._

isIncreasing(Vector(Time(3,3), Time(5,5)))

isIncreasing(Seq(Time(3,3), Time(5,5)))

isIncreasing(List(Time(3,3), Time(5,5)))

def mapFold[A,B](elems: Seq[A])(f: (A) => B): Seq[B] = {
  elems.foldLeft(Seq.empty[B])((e1, e2) => e1 :+ f(e2))
}

abstract class Animal
case class Bird() extends Animal
val qb = Queue(Bird(), Bird(), Bird())
val qa: Queue[Animal] = qb

val l1: List[Nothing] = List()  //List nothing
val l2 = 1 :: l1            //Widened to List[Int]
val l3 = "hello" :: l2      //Widened to List[Any]

import misc.Equal._

//1 === "1" //Doesn't compile
1 === 1
"1" === "1"






























