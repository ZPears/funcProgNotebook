package week03.hierarchies

// ABSTRACT CLASSES

// val t1 = new NonEmpty(3, new Empty, new Empty)
// val t2 = t1 incl 4

// an abstract class can contain members that are missing an implementation -
// for example, (incl and contains) in the following:

abstract class IntSet {
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
  def union(other: IntSet): IntSet
}

// method definitons that don't have a body are
// PERMISSIBLE IFF CLASS IS ABSTRACT

// abstract classes cannot be created with the NEW operator
// for example:

// val newSet = new IntSet

// is not permitted.

// IMPLEMENTING SETS AS BINARY TREES

// two types of possible trees:
// one for the empty set, one consisting of an integer and two sub trees

// start with an empty tree implementation:

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

// NOTE THAT THIS DATA STRUCTURE IS PERSISTENT. CREATING A NEW INTSET and adding it to a tree
// does NOT mutate the old data structure - it simply creates a new one that reuses
// the applicable parts of the old one.

// Types Empty and NonEmpty CONFORM to type IntSet, meaning they can be used wherever
// an object of type intset is required

// EVERY USER DEFINED CLASS extends another class. If no super is given, Object class from java.lang is assumed.

// SINGLETON OBJECTS

// if an instance of an abstract class only needs to exist once, it can be implemented as a
// SINGLETON OBJECT using "object" rather than "class". This is the case with the empty IntSet:
/*
object Empty extends IntSet {
  def contains(x: Int): Boolean = false
  def incl(x: Int): IntSet = new NonEmpty(x, Empty, Empty)
}*/

// note in the last line - singleton objects are values, so there's no evaluation that needs to be performed.

// example hello world using object
object Hello {
  def main(args: Array[String]) = println("Hello World!")
}

// CHECK OUT IMPLEMENTATION OF UNION THAT WAS ADDED LATER
// CHECK OUT DYNAMIC BINDING SECTION AT THE END

// Dynamic dispatch of methods is analogous to calls to higher-order functions - SO...
// can we implement one concept in terms of the other? i.e.:
// objects in terms of higher-order functions or higher-order functions in terms of objects?