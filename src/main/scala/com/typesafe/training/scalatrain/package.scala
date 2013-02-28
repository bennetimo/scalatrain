package com.typesafe.training

import annotation.tailrec

package object scalatrain {

  @tailrec
  def isIncreasing[A <% Ordered[A]](times: Seq[A]): Boolean = times match {
    //case t1 :: t2 :: rest => (t1 < t2) && isIncreasing(t2 :: rest)
    /**
     * The first case example above will pass all the unit tests, as they are written to pass in Seqs and Lists. However,
     * it uses cons (::) to match, which is only defined on list, but not for example on Vector. That means if we pass in
     * a Vector(Time()) then it won't match the first case as the type can't match, so it will hit the catch all. We could
     * use case Seq() instead of case _ for the empty case to force a runtime match error if we were using the cons approach
     */
    case t1 +: t2 +: rest => (t1 < t2) && isIncreasing(t2 +: rest)
    case _ => true
  }

  def isIncreasingSliding[A <: Ordered[A]](times: Seq[A]): Boolean = times.sliding(2).forall {
    case Seq(first, second) => first < second
    case Seq(_) => true
  }

}
