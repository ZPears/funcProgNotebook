// last lecture, numeric and boolean types implemented like classes.
// what about functions?

// function A => B is just abbreviation for class scala.Function1[A, B], which is defined:

package scala
trait Function1[A, B] {
  def apply(x: A): B
}

// function values - expanding an anonymous function:

(x: Int) => x * x

// expands

{ class AnonFun extends Function1[Int, Int] {
    def apply(x: Int) = x * x
  }
  new AnonFun
}

// with shorter syntax:

new Function1[Int, Int] {
  def apply(x: Int) = x * x
}

// full OO translation of:

val f = (x: Int) => x * x
f(7)

// is...

val f = new Function1[Int, Int] {
  def apply(x: Int) = x * x
}
f.apply(7)

// ETA-EXPANSION

object List {
  // List(1,2) = List.apply(1, 2), so
  def apply[T](x1: T, x2: T): List[T] = New Cons(x1, new Cons(x2, new Nil))
  def apply[T]() = new Nil
}