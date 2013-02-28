package misc

sealed class =!=[A, B]

trait LowerPriorityImplicits {
  /** do not call explicitly! */
  implicit def equal[A]: =!=[A, A] = sys.error("should not be called")
}
object =!= extends LowerPriorityImplicits {
  /** do not call explicitly! */
  implicit def nequal[A, B]: =!=[A, B] = new =!=[A, B]
}