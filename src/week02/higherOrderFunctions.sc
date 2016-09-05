// FLs treat functions as FIRST CLASS values
// that means a function can be passed as a param
// and returned as a result.

// functions that take other functions as params
// or return functions are HIGHER ORDER FUNCTIONS.

// function to sum all ints between a and b
def sumInts(a: Int, b: Int): Int =
  if (a > b) 0 else a + sumInts(a + 1, b)

// sumInts(0, 5)
// 0 + sumInts(1, 5)
// 0 + 1 + sumInts(2, 5)
// 0 + 1 + 2 + sumInts(3, 5)
// 0 + 1 + 2 + 3 + sumInts(4,5)
// 0 + 1 + 2 + 3 + 4 + sumInts(5,5)
// 0 + 1 + 2 + 3 + 4 + 0

// function to sum cubes of all ints between a and b

def cube(x: Int): Int = x * x * x

def sumCubes(a: Int, b: Int): Int = {
  if (a > b) 0 else cube(a) + sumCubes(a + 1, b)
}

// function for factorials:

def fact(x: Int): Int = if (x == 0) 1 else x + fact(x-1)

def sumFactorials(a: Int, b: Int): Int = {
  if (a > b) 0 else fact(a) + sumFactorials(a + 1, b)
}

fact(0) + fact(1) + fact(2) + fact(3)
sumFactorials(0, 3)

// these are all special cases of summation(n=a, b) f(n)
// for different values of f. CAN WE FACTOR OUT A COMMON PATTERN?

def sum(f: Int => Int, a: Int, b: Int): Int =
  if (a > b) 0
  else f(a) + sum(f, a + 1, b)

// sumInts(a: Int, b: Int) == sum(id, a, b) where id(x: Int): Int = x
// sumCubes(a: Int, b: Int) == sum(cube, a, b) where cube(x: Int): Int = x * x * x
// sumFactorials(a: Int, b: Int) == sum(fact, a, b) where fact(x: Int): Int = if (x == 0) 1 else x + fact(x - 1)

// THE NOTATION f: Int => Int MEANS the sum function takes a function as an argument,
// and that function argument MUST BE onne that maps integers to integers

// Another name for an anonymous function is a FUNCTION LITERAL
// use anonymous functions in scala a lot, rather than defining a million little ones

// USING ANONYMOUS FUNCTIONS INSTEAD - think of X => X like "lambda x: x"
def sumIntAnon(a: Int, b: Int): Int = sum(x => x, a, b)
def sumCubesAnon(a: Int, b: Int): Int = sum(x => x*x*x, a, b)
def sumFactAnon(a: Int, b: Int): Int = sum(x => fact(x), a, b)

// REWRITE WITH TAIL RECURSION

def sumTail(f: Int => Int)(a: Int, b: Int): Int = {
  def loop(a: Int, acc: Int): Int = {
    if (a > b) acc
    else loop(a + 1, acc + f(a))
  }
  loop(a, 0)
}