import week03.hierarchies._

import scala.collection.immutable.Stream.Empty

// FROM WEEK03, hierarchies
abstract class IntSet {
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
  def union(other: IntSet): IntSet
}

class Empty extends IntSet {
  // always false, because the empty tree can't contain any value
  def contains(x: Int): Boolean = false
  // include returns a nonempty intset, with x as its value and
  // two new emptys
  def incl(x: Int): IntSet = new NonEmpty(x, new Empty, new Empty)
  override def toString = "."
  def union(other: IntSet): IntSet = other
}

class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {
  def contains(x: Int): Boolean =
    if (x < elem) left contains x
    else if (x > elem) right contains x
    else true

  def incl(x: Int): IntSet =
    if (x < elem) new NonEmpty(elem, left incl x, right)
    else if (x > elem) new NonEmpty(elem, left, right incl x)
    else this

  override def toString = "{" + left + "<-" + elem + "->" + right + "}"

  def union(other: IntSet): IntSet =
    ((left union right) union other) incl elem
}

// interactions between subtypes and generics

// for example - defining assertAllPos to check whether
// all members of an IntSet are positive

def assertAllPos(s: IntSet): IntSet

// Issue - doesn't handle empty sets, which needs to happen

// this [S <: IntSet] says that it takes some type S...
// <: operator says that S MUST BE A SUBTYPE OF INTSET
// This is called the "upper bound" of type parameter S

def assertAllPos[S <: IntSet](r: S): S = ...

// S >: T means the opposite - S is a supertype of T
// lower class bound: [S >: NonEmpty], meaning it can be any one of
// NonEmpty, IntSet, AnyRef, or Any

// Lower bounds can be mixed with upper bounds: [S >: NonEmpty <: IntSet]

// is Array[NonEmpty] <: Array[IntSet]?
// this is called covariance

// In Java, the answer is yes: NonEmpty[] <: IntSet[]

// This causes problems: consider the java below

NonEmpty[] a = new NonEmpty[]{ new NonEmpty(1, Empty, Empty) }
IntSet[] b = a
b[0] = Empty // would throw a arrayStoreException at runtime, because there's a NonEmpty array tag
NonEmpty s = a[0]

// THE LISKOV SUBSTITUTION PRINCIPLE

// "If A <: B, then everything one can do with a value of type B
// should also be able to do with a value of type A.

// problematic array from before expressed in Scala:

val a: Array[NonEmpty] = Array(new NonEmpty(1, Empty, Empty) )
val b: Array[IntSet] = a
b(0) = Empty
val s: NonEmpty = a(0)

// would throw type error at line 2, because arrays are NOT covariant in scala