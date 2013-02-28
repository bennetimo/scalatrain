/*
 * Copyright © 2012 Typesafe, Inc. All rights reserved.
 */

package misc

import org.specs2.mutable.Specification

class QueueSpec extends Specification {

  "Calling equals" should {
    "be true for identical objects" in {
      val queue = Queue(1, 2, 3)
      queue == queue must beTrue
    }
    "be true for equal objects" in {
      Queue(1, 2, 3) == Queue(1, 2, 3) must beTrue
    }
    "be true for nonequal objects" in {
      Queue(1, 2) == Queue(1, 2, 3) must beFalse
    }
  }

  "Calling hashCode" should {
    "return the same value for equal objects" in {
      Queue(1, 2, 3).## ==== Queue(1, 2, 3).##
    }
  }

  "Calling toString" should {
    "return the class name and the elements in parentheses" in {
      Queue(1, 2, 3).toString ==== "Queue(1, 2, 3)"
    }
  }

  "Calling dequeue" should {
    "throw an NoSuchElementException for an empty queue" in {
      Queue().dequeue must throwAn[NoSuchElementException]
    }
    "return the first element and a new Queue without the dequeued element" in {
      Queue(1, 2, 3).dequeue ==== (1, Queue(2, 3))
    }
  }

  "Calling enqueue" should {
    "return a queue of one on an empty queue" in {
      Queue().enqueue(3) ==== Queue(3)
    }
    "put new element on the queue" in {
      Queue(2, 1).enqueue(3) ==== Queue(3, 2, 1)
    }
    "after a dequeue results in the expected queue" in {
      Queue(1, 2, 3).dequeue._2.enqueue(3) ==== Queue(3, 2, 3)
    }
  }
}
