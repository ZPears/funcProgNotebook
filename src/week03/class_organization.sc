// place class or obj inside package by adding package clause to top of file:
package week03.class_org

// import statements syntax:
// import week3.Rational // named import: Rational class from week3 package.
// import week3.{Rational, Hello} // named import: Rational and Hello classes.
// import week3._ // wildcard import: all classes and objects from week3 package.

// autoimported in all Scala programs:
// packages scala, java.lang, singleton object scala.Predef

// for example (name and fully qualified name):
// Int == scala.Int
// Boolean == scala.Boolean
// Object == java.lang.Object
// require == scala.Predef.require
// assert == scala.Predef.assert

// SCALA IS SINGLE INHERITANCE - ONLY ONE SUPERCLASS ALLOWED.
// Therefore, traits.

// TRAIT is like an abstract class, but does not impose the same restriction. EXAMPLE:

trait Planar {
  def height: Int
  def width: Int
  def surface = height * width
}

// So a square might EXTEND a shape class, but also take on infinite traits...

// class SQUARE extends SHAPE with planar with movable ...

// in short, traits are like interfaces in Java, but more because they can also
// contain fields and concrete methods ( but NOT value params)

// Scala objects (scala.Any) have two immediate subclasses - scala.AnyVal and scala.AnyRef
// scala.AnyVal contains all the immutable primitives - Boolean, Int, Short, Byte, etc.
// scala.AnyRef is the same as java.lang.Object, but also with scala.ScalaObject trait

// scala.Nothing is at the bottom of the class hierarchy. It is subtype of every other type.
// NO VALUE HAS TYPE NOTHING.
// NOTHING is useful for 1) signalling abnormal termination and 2) as an element type of empty collections

// scala.Null is a subtype of every class that inherits from Object.
// it is incompatible with subtypes of AnyVal.
// for ex: val x = null
// y: String = x // OK, because string can be null
// z: Int = null // NOT OK, because an Int is required and null is not a subtype of Int
