package misc

object Equal {
  implicit class EqualOps[A](val obj: A) extends AnyVal {
    def ===(other: A) = obj == other
  }
}
