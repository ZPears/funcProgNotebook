// Lists are linear - access to first element is much faster than middle or end elements.
// Alternative sequence implementation is VECTOR,
// which is essentially a "very shallow tree" w/ more evenly balanced access patterns than List.

// VECTOR of <= 32 elements is a list;
// when elements becomes > 32, VECTOR becomes a vector of pointers (rather than elements)
// each of which points to another vector of 32 elements.

// depth of vector = log(32) of N, where N is elements of the vector.

val nums = Vector(1, 2, 3, -88)
val people = Vector("Bob", "James", "Peter")

// vectors have operators +: which adds element to left of list, and :+ which adds to right of list.

// NOTE: since vector is immutable, adding a new element means DUPLICATING THE ENTIRE TREE.



// HIERARCHY OF SEQUENCE CLASSES

// List, Vector < Sequence
// Sequence, Set, Map < Iterable


// Also, sequence-like structures, like Array and String

val xs = Array(1, 2, 3, 44)

xs map (x => x * 2)

val s = "Hello World"
s filter (c => c.isUpper)


// another common sequence is a RANGE.

val r: Range = 1 until 5
val r2: Range = 1 to 5
1 to 10 by 3
6 to 1 by -2


// MORE SEQUENCE OPERATIONS:

r exists (x => x == 3)
r forall (x => x % 1 == 0)

val pairs = r zip (5 until 10)
pairs.unzip

s flatMap (c => List('.', c))

r.sum
r.product
r.max
r.min

// returning all combinations from two different number sequences,
// 1..M and 1..N

(1 to 5) flatMap (x => (6 to 10) map ( y => (x, y)))

def scalarProduct(xs: Vector[Double], ys: Vector[Double]): Double = {
  (xs zip ys).map(xy => xy._1 * xy._2).sum
}

// OR, use pattern matching:

def scalarProd(xs: Vector[Double], ys: Vector[Double]): Double = {
  (xs zip ys).map{ case (x,y) => x * y }.sum
}

def isPrime(n: Int): Boolean = {
  (2 until n).forall(x => n % x > 0)
}