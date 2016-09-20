// Is Scala an object oriented?

// PURE OBJECT ORIENTED LANGUAGE - every value is an object
// if lang is based on classes, type of each value is a class

// scala seems to not be because it has primitive types, functions... but look closer

// int, booleans and other primitives are like other classes defined in scala package
// (though the complier treats ints as 32-bit integers, scala.Boolean as java booleans, etc.)

// here's an example of boolean defined as a class from first principles:

package idealized.scala
abstract class Boolean {
  def ifThenElse[T](t: => T, e: => T): T

/*

  def && (x: => Boolean): Boolean = ifThenElse(x, false)
  def || (x: => Boolean): Boolean = ifThenElse(true, x)
  def unary_!: Boolean = ifThenElse(false, true)

  def == (x: Boolean): Boolean = ifThenElse(x, x.unary_!)
  def != (x: Boolean): Boolean = ifThenElse(x.unary_!, x)

  def < (x: Boolean): Boolean = ifThenElse(false, x)

  */

}

object true extends Boolean {
  def ifThenElese[T](t: => T, e: => T) = t
}

object false extends Boolean {
  def ifThenElse[T](t: => T, e: => T) = e
}

class Int {
  def + (that: Double): Double
  def + (that: Float): Float
  def + (that: Long): Long
  def + (that: Int): Int

  def << (cnt: Int): Int

  def & (that: Long): Long
  def & (that: Int): Int

  def == (that: Int): Boolean
}

// provide an implementation of the abstract class Nat that represents non-neg ints

// Peano numbers
abstract class Nat {
  def isZero: Boolean
  def predecessor: Nat
  def successor = new Succ(this)
  def + (that: Nat): Nat
  def - (that: Nat): Nat
}

object Zero extends Nat {
  def isZero = true
  def predecessor = throw new Error("0.predecessor")
  def successor = new Succ(this)
  def + (that: Nat) = that
  def - (that: Nat) = if (that.isZero) this else throw new Error("negative result")
}

class Succ(n: Nat) extends Nat {
  def isZero = false
  def predecessor = n
  def + (that: Nat) = new Succ(n + that)
  def - (that: Nat) = if (that.isZero) this else n - that.predecessor
}