/*
 * Copyright © 2012 Typesafe, Inc. All rights reserved.
 */

package com.typesafe.training.scalatrain

import org.scalatest.WordSpec
import org.scalatest.matchers.MustMatchers

import com.typesafe.training.scalatrain._

class PackageSpec extends WordSpec with MustMatchers {

  "Calling isIncreasing" should {
    "return true for an empty sequence" in {
      isIncreasing(Seq()) must be(true)
    }
    "return false for a non increasing seq" in {
      isIncreasing(Seq(Time(12, 10), Time(12, 5))) must be(false)
    }
    "return true for an increasing seq" in {
      isIncreasing(Seq(Time(12, 10), Time(12, 15))) must be(true)
    }
    "return true for a single time" in {
      isIncreasing(Seq(Time(12, 10))) must be(true)
    }
    "say 9:30, 10:30, 11:30 is increasing" in {
      isIncreasing(Seq(Time(9, 30), Time(10, 30), Time(11, 30))) must be(true)
    }
    "say 9:30, 11:30, 10:30 is NOT increasing" in {
      isIncreasing(Seq(Time(9, 30), Time(11, 30), Time(10, 30))) must be(false)
    }
    "say is NOT increasing for 11:30, 10:30, 9:30" in {
      isIncreasing(List(Time(11, 30), Time(10, 30), Time(9, 30))) must be(false)
    }
    "say is increasing if 0 or 1 element in sequence" in {
      isIncreasing(Seq()) must be(true)
      isIncreasing(Seq(Time(9, 30))) must be(true)
    }
    "say is increasing for 9:30, 10:30" in {
      isIncreasing(Seq(Time(9, 30), Time(10, 30))) must be(true)
    }
    "say is NOT increasing for 10:30, 9:30" in {
      isIncreasing(Seq(Time(10, 30), Time(9, 30))) must be(false)
    }
  }

  "Calling isIncreasing" should {
    "should work on something that is Ordered when true" in {
      isIncreasing(Vector(O(1), O(2))) must be(true)
    }
    "should work on something that is Ordered when false" in {
      isIncreasing(Vector(O(2), O(1))) must be(false)
    }
  }

  private case class O(n: Int) extends Ordered[O] {
    override def compare(that: O): Int =
      this.n - that.n
  }

  "Calling isIncreasingSliding" should {
    "return true for an empty sequence" in {
      isIncreasingSliding(Seq()) must be(true)
    }
    "return false for a non increasing seq" in {
      isIncreasingSliding(Seq(Time(12, 10), Time(12, 5))) must be(false)
    }
    "return true for an increasing seq" in {
      isIncreasingSliding(Seq(Time(12, 10), Time(12, 15))) must be(true)
    }
    "return true for a single time" in {
      isIncreasingSliding(Seq(Time(12, 10))) must be(true)
    }
    "say 9:30, 10:30, 11:30 is increasing" in {
      isIncreasingSliding(Seq(Time(9, 30), Time(10, 30), Time(11, 30))) must be(true)
    }
    "say 9:30, 11:30, 10:30 is NOT increasing" in {
      isIncreasingSliding(Seq(Time(9, 30), Time(11, 30), Time(10, 30))) must be(false)
    }
    "say is NOT increasing for 11:30, 10:30, 9:30" in {
      isIncreasingSliding(List(Time(11, 30), Time(10, 30), Time(9, 30))) must be(false)
    }
    "say is increasing if 0 or 1 element in sequence" in {
      isIncreasingSliding(Seq()) must be(true)
      isIncreasingSliding(Seq(Time(9, 30))) must be(true)
    }
    "say is increasing for 9:30, 10:30" in {
      isIncreasingSliding(Seq(Time(9, 30), Time(10, 30))) must be(true)
    }
    "say is NOT increasing for 10:30, 9:30" in {
      isIncreasingSliding(Seq(Time(10, 30), Time(9, 30))) must be(false)
    }
  }
}
