package misc

object Equal {
  implicit class EqualOps[A](val obj: A) extends AnyVal {
    def ===(other: A)(implicit eq: Equal[A]) = eq.equals(obj, other)
  }

  implicit val intEqual = new Equal[Int] {
    def equals(a: Int, b: Int) = a == b
  }

  implicit val stringEqual = new Equal[String] {
    def equals(a: String, b: String) = a != b
  }

  implicit val anyEqual = new Equal[Any] {
    def equals(a: Any, b: Any) = a == b
  }

  trait Equal[A] {
    def equals(a: A, b: A): Boolean
  }
}
