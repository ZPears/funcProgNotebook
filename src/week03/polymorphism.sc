import com.sun.corba.se.impl.interceptors.InterceptorList

// POLYMORPHISM

// Motiviating example: CONS-LISTS

// AKA immutable linked list

// two building blocks:
//    Nil: the empty list
//    Cons: a cell containing an element and a pointer to the next element

// VERSION 1

trait IntList

class Cons(val head: Int, val tail: IntList) extends IntList

class Nil extends IntList

// a list is either...
//    an empty list new Nil, or
//    a list new Cons(x, xs) consisting of a head x and tail list xs

// but VERSION 1 should be more general - what about lists of other types?
// VERSION 2 uses type parameters to solve:

trait List[T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  def isEmpty = false
  // defining head and tail as val counts as a valid implementation of the method with the same name
  // THIS SHOWS THAT VAL AND DEF ARE REALLY THE SAME, EXCEPT VAL IS EVALUATED WHEN
  // IMPLEMENTED AND DEF IS EVALUATED WHEN CALLED
}

class Nil[T] extends List[T]{
  def isEmpty = true
  def head: Nothing = throw new NoSuchElementException("Nil.head")
  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
}

// like classes, functions can have type parameters.
// for example, here is a function that creates a list of a single element

def singleton[T](elem: T) = new Cons[T](elem, new Nil[T])

// type parameters do not affect evaluation in Scala.
// they are ONLY IMPORTANT FOR THE COMPILER TO CHECK THAT THE PROGRAM SATISFIES CERTAIN CORRECTNESS PROPERTIES
// this is called TYPE ERASURE

// POLYMORPHISM means that a type can have instances of many different forms,
// or in the case of a function, that it can be applied to many different types.

// two prinicpal forms of polymorphism:
// subtyping: instances of a subclass can be passed to a base class (for ex, List has subclasses nil and cons)
// generics: create many instances of a function or class by type parameterization

def nth[T](n: Int, l: List[T]): T =
  if (l.isEmpty) throw new IndexOutOfBoundsException
  else if (n == 0) l.head
  else nth(n-1, l.tail)