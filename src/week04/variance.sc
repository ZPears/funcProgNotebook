// in broad strokes, immutables should be covariant, but mutables should not.
// what are the conditions that allow an immutable to be covariant?


// Say C[T] is a parameterized type and A, B are types such that A <: B
// So there can be C[A] and C[B]

// Then there are 3 possibilities, and scala lets you specify which to use:
// C[A] <: C[B] === C is COVARIANT === class C[+A]
// C[A] >: C[B] === C is CONTRAVARIANT === class C[-A]
// neither class relationship exists === C is NONVARIANT === class C[A]

// Imagine two function types:

type A = IntSet => NonEmpty
type B = NonEmpty => IntSet

// in the above case, A is a subtype of B.

// generalize to all function types:
// if A2 <: A1 and B1 <: B2, then
// A1 => B1 <: A2 => B2

// THIS MEANS THAT FUNCTIONS ARE CONTRAVARIANT IN ARGUMENT TYPE,
// BUT COVARIANT IN RESULT TYPE:

package scala
trait Function1[-T, +U] {
  def apply(X: T): U
}