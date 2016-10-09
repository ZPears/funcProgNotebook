// FINDING ALL COMBINATIONS THAT MEET A CERTAIN CONDITION FROM TWO LISTS.

// in an imperative language, a nested loop (for i in (for j in ...) ...)
// would be a likely solution.

// HOW TO SOLVE IN FUNCTIONAL:

// Example problem: given a positive integer n, find all pairs of positive
// integers i and j, with j < i < n, such that i + j are prime.

// 1. Generate the sequence of all pairs of integers (i, j) such that i and j meet j < i < n.
// 2, Filter the pairs for which i + j is prime.

object pairs {

  def isPrime(n: Int) = (2 until n) forall (n % _ != 0)

  val n = 5
  val pairs = (1 until n) flatMap (i => (1 until i) map (j => (i,j) ) )
  // or use .flatten
  // or (xss foldRight Seq[Int]())(_ ++ _)

  val primers = pairs.filter(pair => isPrime(pair._1 + pair._2))

}

// map, flatMap, filter provide powerful constructs for manipulating lists.
// But level of abstraction can make them hard to understand.
// In this case, FOR EXPRESSION notation can help.

case class Person(name: String, age: Int)

val persons = List(Person("zach", 28), Person("jake", 5))

// for people over 20, you can write:

for ( p <- persons if p.age > 20) yield p.name
persons filter (p => p.age > 20) map (p => p.name)

// for expressions follow the form:
// FOR ( GENERATOR, FILTER) YIELD EXPRESSION

// a GENERATOR is of form p <- e, where p is a pattern and e an expression whose
// value is a collection.

// a FILTER is of the for if f where f is a boolean expression.

// sequence must START with a generator.


// looking for pairs:
def isPrime(n: Int) = (2 until n) forall (n % _ != 0)

val n = 10
for {
  i <- 1 until n
  j <- 1 until i
  if isPrime(i + j)
} yield (i, j)

def scalarProduct(xs: List[Double], ys: List[Double]): Double = {
  ( for ( (x, y) <- xs zip ys ) yield x * y ).sum
}