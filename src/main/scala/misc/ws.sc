/**
 * Created with IntelliJ IDEA.
 * User: tim
 * Date: 27/02/2013
 * Time: 09:35
 * To change this template use File | Settings | File Templates.
 */

import com.typesafe.training.scalatrain.Time

Time.isIncreasing(Vector(Time(3,3), Time(5,5)))

Time.isIncreasing(Seq(Time(3,3), Time(5,5)))

Time.isIncreasing(List(Time(3,3), Time(5,5)))

def mapFold[A,B](elems: Seq[A])(f: (A) => B): Seq[B] = {
  elems.foldLeft(Seq.empty[B])((e1, e2) => e1 :+ f(e2))
}


