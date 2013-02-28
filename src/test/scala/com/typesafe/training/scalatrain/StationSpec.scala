/*
 * Copyright © 2012 Typesafe, Inc. All rights reserved.
 */

package com.typesafe.training.scalatrain

import org.scalatest.WordSpec
import org.scalatest.matchers.MustMatchers

class StationSpec extends WordSpec with MustMatchers {

  "A Station" should {
    "be createable from a string" in {
      val s: Station = "Munich"
      s.getClass must be === classOf[Station]
    }
  }

}
