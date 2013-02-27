/*
 * Copyright © 2012 Typesafe, Inc. All rights reserved.
 */

package misc

import org.scalatest.WordSpec
import org.scalatest.matchers.MustMatchers

class PhoneMnemonicsSpec extends WordSpec with MustMatchers {

  "charCode" should {
    "be correctly initialized" in {
      phoneMnemonics.charCode must be === Map(
        'A' -> '2', 'B' -> '2', 'C' -> '2', 'D' -> '3', 'E' -> '3', 'F' -> '3',
        'G' -> '4', 'H' -> '4', 'I' -> '4', 'J' -> '5', 'K' -> '5', 'L' -> '5',
        'M' -> '6', 'N' -> '6', 'O' -> '6', 'P' -> '7', 'Q' -> '7', 'R' -> '7', 'S' -> '7',
        'T' -> '8', 'U' -> '8', 'V' -> '8', 'W' -> '9', 'X' -> '9', 'Y' -> '9', 'Z' -> '9'
      )
    }
  }

  "Calling wordCode" should {
    "return the correct word code" in {
      (phoneMnemonics wordCode Java) must be === "5282"
    }
  }

  "wordsByNumber" should {
    "be correctly initialized" in {
      phoneMnemonics.wordsByNumber must be === Map("5282" -> Set(Java, Kata))
    }
  }

  "Calling encode" should {
    "return the correct set of words" in {
      (phoneMnemonics encode "5282") must be === Set(Seq(Java), Seq(Kata))
      (phoneMnemonics encode "52825282") must be === Set(
        Seq(Java, Kata), Seq(Java, Java), Seq(Kata, Java), Seq(Kata, Kata)
      )
      (phoneMnemonics encode "123") must be === Set.empty
    }
  }

  private val Java = "Java"

  private val Kata = "Kata"

  private val phoneMnemonics = new PhoneMnemonics(Set(Java, Kata))
}
