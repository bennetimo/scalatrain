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
