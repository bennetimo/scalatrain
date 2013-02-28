package misc

import scala.collection.immutable.Seq

object Queue {

  def apply[A](as: A*): Queue[A] =
    new Queue(as.toVector)
}

class Queue[+A] private (private val as: Seq[A]) {

  def dequeue(): Tuple2[A, Queue[A]] = as match {
    case Seq() => throw new NoSuchElementException
    case head +: rest => head -> Queue(rest: _*)
  }

  /**
   * We can't just take an A here, because A is defined to be covariant, and here it is in a contravariant position.
   * If we have a Queue of birds, then that is also a Queue[Animal] (because of the covariatiance). Therefore, we should
   * be able to put an Animal into it, but we wouldn't be able to normally. Here we introduce a new type paramater, B,
   * which is some supertype of A, and instead we return a Queue[B]. So if we have a Queue[Bird] and we add an Animal,
   * Animal >: Bird, and we add the animal and get back a Queue[Animal] = Queue(Bird, Animal).
   *
   * This is exactly the same as happens with Lists.
   */
  def enqueue[B >: A](elem: B): Queue[B] = new Queue(elem +: as)

  override def equals(other: Any): Boolean =
    other match {
      case that: Queue[_] => (this eq that) || (this.as == that.as)
      case _ => false
    }

  override def hashCode: Int =
    as.hashCode

  override def toString: String =
    s"Queue(${as mkString ", "})"
}
