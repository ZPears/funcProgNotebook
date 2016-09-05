// CURRYING

// motivating question - can we avoid passing a, b all the time, from
// the previous example? Rewrite sum as follows:

// define sum as a function that takes an Int => Int function as an arg,
// and returns a function that maps 2 Int args to Int
def sum(f: Int => Int): (Int, Int) => Int = {
  def sumFunc(a: Int, b: Int): Int =
    if (a > b) 0
    else f(a) + sumFunc(a + 1, b)
  sumFunc
}

// then you can do:
def sumInts = sum(x => x)
sumInts(1, 10)
def sumCubes = sum(x => x*x*x)
sumCubes(1, 10)
def fact(x: Int): Int = if (x == 0) 1 else x + fact(x -1)
def sumFactorials = sum(fact)
sumFactorials(1, 10)

// OR, DO IT WITHOUT NAMING THE OTHER FUNCTIONS:
sum (x => x*x*x) (1, 10)

// sum (x => x*x*x) applies sum to cube and returns the sumCubes function
// therefore, sum (x => x*x*x) == sumCubes

// This means FUNCTION APPLICATION ASSOCIATES LEFT:
// sum(cube)(1, 10) == (sum (cube))(1, 10)

// you can use this special syntax to make it even shorter.
// CHAIN MULTIPLE PARAMETER LISTS TOGETHER

def sumShort(f: Int => Int)(a: Int, b: Int): Int = {
  if (a > b) 0 else f(a) + sumShort(f)(a + 1, b)
}

// def f(args1)...(argsn-1)(argsn) = E
// is the same as
// def f = args1 => (args2 => ...(argsn => E)...))

// When a function is mapped to an expression that consists of n nested
// anonymous functions that each take one param, it's called CURRYING,
// after Haskell Brooks Curry (1900-1982)



// What is the type of SUM in sum(f: Int => Int)(a: Int, b: Int): Int = ...

// It's (Int => Int) => (Int, Int) => Int

// NOTE THAT FUNCTIONAL TYPES ASSOCIATE TO THE RIGHT -
// Int => Int => Int === Int => ( Int => Int )

object exercise {
  def product(f: Int => Int)(a: Int, b: Int): Int = {
    if (a > b) 1 else f(a) * product(f)(a + 1, b)
  }

  def factorial(x: Int): Int = {
    product(x => x)(1, x)
  }

  // CHECK OUT MAP REDUCE!
  def mapReduce(f: Int => Int, combine: (Int, Int) => Int, unitValue: Int)(a: Int, b: Int): Int = {
    if (a > b) unitValue
    else combine(f(a), mapReduce(f, combine, unitValue)(a + 1, b))
  }

  def newProduct(f: Int => Int)(a: Int, b: Int): Int = {
    mapReduce(f, (x, y) => x * y, 1)(a, b)
  }
}